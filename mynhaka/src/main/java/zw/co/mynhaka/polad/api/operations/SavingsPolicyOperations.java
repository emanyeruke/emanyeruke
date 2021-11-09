package zw.co.mynhaka.polad.api.operations;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.audit.Audit;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.enums.PolicyType;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.Agent;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.domain.model.SavingsProduct;
import zw.co.mynhaka.polad.search.PolicySavingsSearchService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.*;
import zw.co.mynhaka.polad.service.util.Constants;
import zw.co.mynhaka.polad.service.util.OtherUtils;
import zw.co.mynhaka.polad.service.util.PolicyBusinessRuleUtils;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/policy/savings", produces = MediaType.APPLICATION_JSON_VALUE)
public class SavingsPolicyOperations {

    private final PolicySavingsService policySavingsService;
    private final SavingsSurrenderService savingsSurrenderService;
    private final PolicySavingsSearchService policySavingsSearchService;
    private final SavingsProductService savingsProductService;
    private final AgentService agentService;
    private final PolicyHolderService policyHolderService;
    private final ModelMapper modelMapper;


    @PostMapping("allocate-savings-plan")
    @Audit(resource = "Savings Policy", action = "Allocate Savings Policy")
    public ApiResponse allocateSavingsPlan(@Valid @RequestBody PolicySavingsCreateDto policySavingsCreateDto) {
       // URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        log.info("############## Request to allocate savings policy: {}", policySavingsCreateDto.toString());
        PolicySavings policySavings = modelMapper.map(policySavingsCreateDto,PolicySavings.class);

        if (policySavingsService.findPolicySavingsByPolicyHolder_Id(policySavingsCreateDto.getPolicyHolderId()).size()!= 0) {
            throw new BusinessValidationException("This policyholder already has a Savings Policy, consider upgrading");
        }

        //generate Policy Number
        policySavings.setPolicyNumber(OtherUtils.generatePolicyNumberForPrincipal());

        //get policyHolder
        PolicyHolder policyHolder = policyHolderService.getOne(policySavingsCreateDto.getPolicyHolderId());

        //set policy holder
        policySavings.setPolicyHolder(policyHolder);
        //perform validations

        if (PolicyBusinessRuleUtils.isPrincipalAgeAboveEntryAge(policyHolder.getDateOfBirth(), Constants.PRINCIPAL_ENTRY_AGE_SAVINGS)) {

        }
        //get savings product and agent details
        SavingsProduct savingsProduct = savingsProductService.getOne(policySavingsCreateDto.getSavingsProductId());

        Agent agent = agentService.getOne(policySavingsCreateDto.getAgentId());

        //set savings product and agent details
        policySavings.setSavingsProduct(savingsProduct);

        policySavings.setAgent(agent);

        //set policy type
        policySavings.setPolicyType(PolicyType.SAVINGS);
        policySavings.setPolicyUpgradeStatus(PolicyUpgradeStatus.INITIAL);

        return new ApiResponse(200, "SUCCESS",policySavingsService.allocateProduct(policySavings));
    }

    @PostMapping("fake-allocate-savings-plan")
    public void fakerAllocateSavingsPlan() {
        policySavingsService.allocateFakerProduct();

    }

    @GetMapping("/get-all-savings-policies")
    @Audit(resource = "Savings Policy", action = "Get All Savings policies")
    public ApiResponse getAllSavingsPolicies() {
        log.info("############## Request to get all savings policy");
        return new ApiResponse(200,"SUCCESS",policySavingsService.findAll());
    }


    @PostMapping("/{policyNumber}/surrender")
    @Audit(resource = "Savings Policy", action = "Surrender savings policy")
    public ResponseEntity<SavingsSurrenderResultDTO> surrenderPolicy(
            @PathVariable("policyNumber") String policyNumber,
            @RequestBody @Valid SavingsSurrenderCreateDTO savingsSurrenderCreateDTO
    ) {
        log.info("############## Request to surrender savings policy: {}", savingsSurrenderCreateDTO.toString());

        return ResponseEntity.ok(savingsSurrenderService.save(policyNumber, savingsSurrenderCreateDTO));
    }

    @PostMapping("search/{page}/{size}")
    @Audit(resource = "Savings Policy", action = "Search Savings Policy")
    public ResponseEntity<List<PolicySavingsResultDTO>> searchPolicyAccidents(
            @RequestParam(value = "searchCriteria", required = false) String searchCriteria,
            @PathVariable int page, @PathVariable int size
    ) {
        log.info("############## Request to search savings policy: {}", searchCriteria.toString());
        return ResponseEntity.ok(policySavingsSearchService.fuzzySearch(searchCriteria, page, size));
    }

    @PutMapping("change-product/{policyId}/{accidentId}")
    @Audit(resource = "Comprehensive Plan", action = "Change Plan")
    public ResponseEntity<PolicySavingsResultDTO> downgradeProduct(@PathVariable("policyId") Long policyId,
                                                                   @PathVariable("savingsId") Long savingsId) {
        log.info("##############Request to change product: {}, {}", policyId, savingsId);

        return ResponseEntity.ok(policySavingsService.downgradeProduct(policyId, savingsId));

    }

    @PutMapping("upgrade-product/{policyId}/{accidentId}")
    @Audit(resource = "Comprehensive Plan", action = "Change Plan")
    public ResponseEntity<PolicySavingsResultDTO> upgradeProduct(@PathVariable("policyId") Long policyId,
                                                                 @PathVariable("savingsId") Long savingsId) {
        log.info("##############Request to upgrade product: {}, {}", policyId, savingsId);

        return ResponseEntity.ok(policySavingsService.upgradeProduct(policyId, savingsId));

    }
}