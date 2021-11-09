package zw.co.mynhaka.polad.api.operations;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.audit.Audit;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.comprehensive.BeneficiaryComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.CancelPolicyCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.claim.RefundPolicyCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveBeneficiaryDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.DeathBeneficiaryDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.enums.PolicyType;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.Agent;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneralProduct;
import zw.co.mynhaka.polad.domain.model.PolicyComprehensive;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.search.PolicyComprehensiveSearchService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.AgentService;
import zw.co.mynhaka.polad.service.iface.ComprehensiveProductService;
import zw.co.mynhaka.polad.service.iface.PolicyComprehensiveService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.util.Constants;
import zw.co.mynhaka.polad.service.util.OtherUtils;
import zw.co.mynhaka.polad.service.util.PolicyBusinessRuleUtils;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/policy/comprehensive", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ComprehensivePolicyOperations {

    private final PolicyComprehensiveService policyComprehensiveService;
    private final PolicyComprehensiveSearchService policyComprehensiveSearchService;
    private final ComprehensiveProductService comprehensiveProductService;
    private final AgentService agentService;
    private final PolicyHolderService policyHolderService;
    private final ModelMapper modelMapper;

    public ComprehensivePolicyOperations(
            final PolicyComprehensiveService policyComprehensiveService, PolicyComprehensiveSearchService policyComprehensiveSearchService,ComprehensiveProductService comprehensiveProductService,AgentService agentService,PolicyHolderService policyHolderService,ModelMapper modelMapper) {

        this.policyComprehensiveService = policyComprehensiveService;

        this.policyComprehensiveSearchService = policyComprehensiveSearchService;

        this.comprehensiveProductService = comprehensiveProductService;

        this.agentService = agentService;

        this.policyHolderService = policyHolderService;

        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "allocate-comprehensive-plan")
    @Audit(resource = "Comprehensive Plan", action = "Allocate Comprehensive Plan")
    public ApiResponse allocateComprehensivePlan(@Valid @RequestBody PolicyComprehensiveCreateDto policyComprehensiveCreateDto) {
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        log.info("############## Request to allocate: {}", policyComprehensiveCreateDto.toString());

        PolicyComprehensive policyComprehensive = modelMapper.map(policyComprehensiveCreateDto,PolicyComprehensive.class);

        if (policyComprehensiveService.findPolicyComprehensiveByPolicyHolder_Id(policyComprehensiveCreateDto.getPolicyHolderId()).size()!= 0) {
            throw new BusinessValidationException("This policyholder already has a Comprehensive Funeral Policy, consider upgrading");
        }
        //policy holder info
        PolicyHolder policyHolder = policyHolderService.getOne(policyComprehensiveCreateDto.getPolicyHolderId());

        //set policyHolder
        policyComprehensive.setPolicyHolder(policyHolder);

        if (PolicyBusinessRuleUtils.isPrincipalAgeAboveEntryAge(policyHolder.getDateOfBirth(), Constants.PRINCIPAL_ENTRY_AGE_FUNERAL)) {
        }

            ComprehensiveFuneralProduct comprehensiveFuneralProduct = comprehensiveProductService
                    .getOne(policyComprehensiveCreateDto.getComprehensiveProductId());

            Agent agent = agentService.getOne(policyComprehensiveCreateDto.getAgentId());

        //generate Policy Number
        policyComprehensive.setPolicyNumber(OtherUtils.generatePolicyNumberForPrincipal());
        //set product
        policyComprehensive.setComprehensiveFuneralProduct(comprehensiveFuneralProduct);
        //set agent
        policyComprehensive.setAgent(agent);
        //set policy type
        policyComprehensive.setPolicyType(PolicyType.COMPREHENSIVE);
        policyComprehensive.setPolicyUpgradeStatus(PolicyUpgradeStatus.INITIAL);

        return new ApiResponse(200, "SUCCESS", policyComprehensiveService.allocateProduct(policyComprehensive));
    }

    @PostMapping("fake-allocate-comprehensive-plan")
    public void fakerAllocateComprehensivePlan() {
        policyComprehensiveService.allocateFakerProduct();

    }

    @GetMapping("comprehensive-policy/beneficiaries/{id}")
    @Audit(resource = "Comprehensive Plan", action = "Get All Comprehensive Policy beneficiaries")
    public ResponseEntity<List<BeneficiaryComprehensiveResultDTO>> getBeneficiariesById(@PathVariable("id") Long id) {
        log.info("##############Request to get all beneficiaries : {}", id);
        return ResponseEntity.ok(policyComprehensiveService.getBeneficiariesById(id));
    }

    @GetMapping("comprehensive-policy/dependents/{id}")
    @Audit(resource = "Comprehensive Plan", action = "Get All Comprehensive policy dependents")
    public ResponseEntity<List<BeneficiaryComprehensiveResultDTO>> getDependentsById(@PathVariable("id") Long id) {
        log.info("############## Request to get beneficiaries : {}", id);
        return ResponseEntity.ok(policyComprehensiveService.getDependentsById(id));
    }

    @GetMapping("dependents/{policynumber}")
    @Audit(resource = "Comprehensive Policy", action = "Get All Dependents for a policy")
    public ResponseEntity<List<BeneficiaryComprehensiveResultDTO>> getDependentsByPolicyNumber(@PathVariable("policynumber") String policynumber) {
        log.info("############## Request to get dependents: {}", policynumber);
        return ResponseEntity.ok(policyComprehensiveService.getDependentsByPolicyNumber(policynumber));
    }


    @GetMapping("beneficiaries-policies/{policynumber}")
    @Audit(resource = "Comprehensive Policy", action = "Get All Comprehensive Policy beneficiaries")
    public ResponseEntity<List<BeneficiaryComprehensiveResultDTO>> getBeneficiariesByPolicyNumber(@PathVariable("policynumber") String policynumber) {
        log.info("##############Request to get beneficiaries by policy number: {}", policynumber);
        return ResponseEntity.ok(policyComprehensiveService.getBeneficiariesByPolicyNumber(policynumber));
    }


    @PostMapping("/beneficiary")
    @Audit(resource = "Comprehensive Policy", action = "Add Comprehensive Policy dependents")
    ResponseEntity<List<BeneficiaryComprehensiveResultDTO>> addFamily(@RequestBody @Valid List<ComprehensiveBeneficiaryDto> comprehensiveBeneficiaryDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        log.info("################ Request to save dependents: {}", comprehensiveBeneficiaryDto.toString());
        return ResponseEntity.created(uri)
                .body(policyComprehensiveService.addBeneficiary(comprehensiveBeneficiaryDto));
    }


    @PostMapping("/beneficiary/death")
    @Audit(resource = "Comprehensive Policy", action = "Add Comprehensive Policy beneficiary")
    ResponseEntity<List<BeneficiaryComprehensiveResultDTO>> addDependents(@RequestBody @Valid List<DeathBeneficiaryDto> deathBeneficiaryDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        log.info("############## Request to add death beneficiary: {}", deathBeneficiaryDto.toString());
        return ResponseEntity.created(uri)
                .body(policyComprehensiveService.addDeathBeneficiary(deathBeneficiaryDto));
    }


    @GetMapping("/get-all-comprehensive-policies")
    @Audit(resource = "Comprehensive Policy", action = "Get All Comprehensive Policies")
    public ApiResponse getAllComprehensivePolicies() {
        log.info("############## Request to get all comprehensive policies");
        return new ApiResponse(200,"SUCCESS",policyComprehensiveService.findAll());
    }

    @PutMapping("change-product/{policyId}/{accidentId}")
    @Audit(resource = "Comprehensive Plan", action = "Change Plan")
    public ResponseEntity<PolicyComprehensiveResultDTO> downgradeProduct(@PathVariable("policyId") Long policyId,
                                                                    @PathVariable("comprehensiveId") Long comprehensiveId) {
        log.info("##############Request to change product: {}, {}", policyId, comprehensiveId);

        return ResponseEntity.ok(policyComprehensiveService.downgradeProduct(policyId, comprehensiveId));

    }

    @PutMapping("upgrade-product/{policyId}/{accidentId}")
    @Audit(resource = "Comprehensive Plan", action = "Change Plan")
    public ResponseEntity<PolicyComprehensiveResultDTO> upgradeProduct(@PathVariable("policyId") Long policyId,
                                                                  @PathVariable("comprehensiveId") Long comprehensiveId) {
        log.info("##############Request to upgrade product: {}, {}", policyId, comprehensiveId);


        return ResponseEntity.ok(policyComprehensiveService.upgradeProduct(policyId, comprehensiveId));

    }

    @PostMapping("cancel")
    ResponseEntity cancelPolicy(@RequestBody CancelPolicyCreateDTO cancelPolicyCreateDto) {
        return null;
    }

    @PostMapping("refund")
    ResponseEntity cancelPolicy(@RequestBody RefundPolicyCreateDto refundPolicyCreateDto) {
        return null;
    }

    @PostMapping("claim")
    ResponseEntity claimLifeAssured(@RequestBody DeathClaimCreateDto deathClaimCreateDto) {
        return null;
    }

    @PostMapping("search/{page}/{size}")
    @Audit(resource = "Comprehensive Policy", action = "Search Comprehensive Policies")
    public ResponseEntity<List<PolicyComprehensiveResultDTO>> searchPolicyComprehensive(@RequestParam(value = "searchCriteria", required = false) String searchCriteria,
                                                                                        @PathVariable int page, @PathVariable int size) {
        log.info("############## Request to search all comprehensive policies: {}", searchCriteria.toString());
        return ResponseEntity.ok(policyComprehensiveSearchService.fuzzySearch(searchCriteria, page, size));

    }

    @DeleteMapping("beneficiary/{beneficiaryId}")
    @Audit(resource = "Comprehensive Policy", action = "Delete Comprehensive Policy beneficiary")
    ResponseEntity deleteBeneficiary(
            @PathVariable("beneficiaryId") Long beneficiaryId) {
        log.info("############## Request to delete comprehensive policy beneficiary: {}", beneficiaryId);
        policyComprehensiveService.deleteBeneficiaryOrDependent(beneficiaryId);
        return ResponseEntity.ok().build();
    }


}
