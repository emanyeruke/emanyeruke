package zw.co.mynhaka.polad.api.operations;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.audit.Audit;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.funeral.BeneficiaryFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralBeneficiaryCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralDeathBeneficiaryCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.enums.PolicyType;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.Agent;
import zw.co.mynhaka.polad.domain.model.FuneralProduct;
import zw.co.mynhaka.polad.domain.model.PolicyFuneral;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.search.PolicyFuneralSearchService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.AgentService;
import zw.co.mynhaka.polad.service.iface.FuneralProductService;
import zw.co.mynhaka.polad.service.iface.PolicyFuneralService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.util.Constants;
import zw.co.mynhaka.polad.service.util.OtherUtils;
import zw.co.mynhaka.polad.service.util.PolicyBusinessRuleUtils;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController

@Slf4j
@RequestMapping(value = "/api/v1/policy/funeral", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuneralPolicyOperations {

    private final PolicyFuneralService policyFuneralService;
    private final PolicyFuneralSearchService policyFuneralSearchService;
    private final FuneralProductService funeralProductService;
    private final AgentService agentService;
    private  final PolicyHolderService policyHolderService;
    private final ModelMapper modelMapper;


    public FuneralPolicyOperations(final PolicyFuneralService policyFuneralService, PolicyFuneralSearchService policyFuneralSearchService,FuneralProductService funeralProductService,
                                    AgentService agentService,PolicyHolderService policyHolderService, ModelMapper modelMapper) {
        this.policyFuneralService = policyFuneralService;

        this.policyFuneralSearchService = policyFuneralSearchService;

        this.funeralProductService = funeralProductService;

        this.agentService = agentService;

        this.policyHolderService = policyHolderService;

        this.modelMapper = modelMapper;
    }

    @PostMapping("/beneficiary")
    @Audit(resource = "Standard Funeral Policy", action = "Add family to Standard Funeral Policy")
    ResponseEntity<List<BeneficiaryFuneralResultDTO>> addFamily(@RequestBody @Valid List<FuneralBeneficiaryCreateDto> funeralBeneficiaryCreateDtos) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        log.info("############## Request to add beneficiaries to funeral policy: {}", funeralBeneficiaryCreateDtos.toString());
        return ResponseEntity.created(uri)
                .body(policyFuneralService.addBeneficiary(funeralBeneficiaryCreateDtos));

    }

    @PostMapping("fake-allocate-funeral-plan")
    public void fakerAllocateFuneralPlan() {
        policyFuneralService.allocateFakerProduct();

    }

    @PostMapping("allocate-funeral-plan")
    @Audit(resource = "Standard Funeral Policy", action = "Allocate Standard Funeral Policy")
    public ApiResponse allocateFuneralPlan(@Valid @RequestBody PolicyFuneralCreateDto policyFuneralCreateDto) {
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        log.info("############## Request to allocate funeral policy: {}", policyFuneralCreateDto.toString());

        PolicyFuneral policyFuneral = modelMapper.map(policyFuneralCreateDto,PolicyFuneral.class);

        if (policyFuneralService.findPolicyFuneralByPolicyHolder_Id(policyFuneralCreateDto.getPolicyHolderId()).size()!= 0) {
            throw new BusinessValidationException("This policyholder already has a Funeral Policy, consider upgrading");
        }
        //generate Policy Number
        policyFuneral.setPolicyNumber(OtherUtils.generatePolicyNumberForPrincipal());
        //get policy holder
        PolicyHolder policyHolder = policyHolderService.getOne(policyFuneralCreateDto.getPolicyHolderId());
        //set policy holder
        policyFuneral.setPolicyHolder(policyHolder);
        if (PolicyBusinessRuleUtils.isPrincipalAgeAboveEntryAge(policyHolder.getDateOfBirth(), Constants.PRINCIPAL_ENTRY_AGE_FUNERAL)) {
        }
        //get funeral product & agent details
        FuneralProduct funeralProduct = funeralProductService.getOne(policyFuneralCreateDto.getFuneralProductId());
        Agent agent = agentService.getOne(policyFuneralCreateDto.getAgentId());
        LocalDate today = LocalDate.now();

        //set funeral product & agent details
        policyFuneral.setFuneralProduct(funeralProduct);
        policyFuneral.setAgent(agent);
        //set policy type
        policyFuneral.setPolicyType(PolicyType.FUNERAL);
        policyFuneral.setPolicyUpgradeStatus(PolicyUpgradeStatus.INITIAL);

        return new ApiResponse(200,"SUCCESS", policyFuneralService.allocateProduct(policyFuneral));

    }

    @GetMapping("beneficiaries-policies/{policynumber}")
    @Audit(resource = "Standard Funeral Policy", action = "Get All beneficiaries Standard Funeral Policy")
    public ResponseEntity<List<BeneficiaryFuneralResultDTO>> getBeneficiariesByPolicyNumber(@PathVariable("policynumber") String policynumber) {
        log.info("############## Request to get beneficiaries  funeral policy: {}", policynumber.toString());

        return ResponseEntity.ok(policyFuneralService.getBeneficiariesByPolicyNumber(policynumber));
    }

    @GetMapping("beneficiaries/{id}")
    @Audit(resource = "Standard Funeral Policy", action = "Get beneficiary Standard Funeral Policy")
    public ResponseEntity<List<BeneficiaryFuneralResultDTO>> getBeneficiariesById(@PathVariable("id") Long id) {
        log.info("############## Request to get beneficiaries of funeral policy: {}", id.toString());

        return ResponseEntity.ok(policyFuneralService.getBeneficiariesById(id));
    }

    @GetMapping("funeral-policy/dependents/{id}")
    @Audit(resource = "Standard Funeral Policy", action = "Get All dependents for Standard Funeral Policy")
    public ResponseEntity<List<BeneficiaryFuneralResultDTO>> getDependentsById(@PathVariable("id") Long id) {
        log.info("############## Request to get dependents of funeral policy: {}", id.toString());

        return ResponseEntity.ok(policyFuneralService.getDependentsById(id));

    }

    @GetMapping("dependents/{policynumber}")
    @Audit(resource = "Standard Funeral Policy", action = "Get All dependents for Standard Funeral Policy")
    public ResponseEntity<List<BeneficiaryFuneralResultDTO>> getDependentsByPolicyNumber(@PathVariable("policynumber") String policynumber) {
        log.info("############## Request to get all dependents for all funeral policy: {}", policynumber.toString());

        return ResponseEntity.ok(policyFuneralService.getDependentsByPolicyNumber(policynumber));

    }

    @PostMapping("/beneficiary/death")
    @Audit(resource = "Standard Funeral Policy", action = "Add death beneficiary Standard Funeral Policy")
    ResponseEntity<List<BeneficiaryFuneralResultDTO>> addDependents(@RequestBody @Valid List<FuneralDeathBeneficiaryCreateDto> funeralBeneficiaryCreateDtos) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        log.info("############## Request to add beneficiary to funeral policy: {}", funeralBeneficiaryCreateDtos.toString());

        return ResponseEntity.created(uri)
                .body(policyFuneralService.addDeathBeneficiary(funeralBeneficiaryCreateDtos));
    }

    @GetMapping("/get-all-funeral-policies")
    @Audit(resource = "Standard Funeral Policy", action = "Get All Standard Funeral Policies")
    public ApiResponse getAllFuneralPolicies() {
        log.info("############## Request to get all funeral policies");
        return new ApiResponse(200,"SUCCESS",policyFuneralService.findAll());
    }


    @PostMapping("search/{page}/{size}")
    @Audit(resource = "Standard Funeral Policy", action = "Get All Standard Funeral Policies")
    public ResponseEntity<List<PolicyFuneralResultDTO>> searchPolicyFuneral(@RequestParam(value = "searchCriteria", required = false) String searchCriteria,
                                                                            @PathVariable int page, @PathVariable int size) {
        log.info("############## Request to search  funeral policy: {}", searchCriteria.toString());

        return ResponseEntity.ok(policyFuneralSearchService.fuzzySearch(searchCriteria, page, size));

    }

    @DeleteMapping("beneficiary/{beneficiaryId}")
    @Audit(resource = "Standard Funeral Policy", action = "Delete beneficiary from Standard Funeral Policy")
    ResponseEntity deleteBeneficiary(@PathVariable("beneficiaryId") Long beneficiaryId) {
        log.info("############## Request to delete  funeral policy: {}", beneficiaryId);

        policyFuneralService.deleteBeneficiaryOrDependent(beneficiaryId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("change-product/{policyId}/{accidentId}")
    @Audit(resource = "Funeral Plan", action = "Change Plan")
    public ResponseEntity<PolicyFuneralResultDTO> downgradeProduct(@PathVariable("policyId") Long policyId,
                                                                         @PathVariable("funeralId") Long funeralId) {
        log.info("##############Request to change product: {}, {}", policyId, funeralId);

        return ResponseEntity.ok(policyFuneralService.downgradeProduct(policyId, funeralId));

    }

    @PutMapping("upgrade-product/{policyId}/{accidentId}")
    @Audit(resource = "Funeral Plan", action = "Change Plan")
    public ResponseEntity<PolicyFuneralResultDTO> upgradeProduct(@PathVariable("policyId") Long policyId,
                                                                       @PathVariable("funeralId") Long funeralId) {
        log.info("##############Request to upgrade product: {}, {}", policyId, funeralId);

        return ResponseEntity.ok(policyFuneralService.upgradeProduct(policyId, funeralId));

    }

}
