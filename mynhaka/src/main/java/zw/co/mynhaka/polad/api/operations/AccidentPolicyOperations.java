package zw.co.mynhaka.polad.api.operations;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.audit.Audit;
import zw.co.mynhaka.polad.domain.dtos.accident.PolicyAccidentUpdateCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.enums.PolicyType;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.AccidentProduct;
import zw.co.mynhaka.polad.domain.model.Agent;
import zw.co.mynhaka.polad.domain.model.PolicyAccident;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.search.PolicyAccidentSearchService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.AccidentProductService;
import zw.co.mynhaka.polad.service.iface.AgentService;
import zw.co.mynhaka.polad.service.iface.PolicyAccidentService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.util.Constants;
import zw.co.mynhaka.polad.service.util.OtherUtils;
import zw.co.mynhaka.polad.service.util.PolicyBusinessRuleUtils;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/policy/accident", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidentPolicyOperations {

    private final PolicyAccidentService policyAccidentService;
    private final PolicyAccidentSearchService policyAccidentSearchService;
    private  final AccidentProductService accidentProductService;
    private  final AgentService agentService;
    private final PolicyHolderService policyHolderService;
    private final ModelMapper modelMapper;

    public AccidentPolicyOperations(final PolicyAccidentService policyAccidentService, PolicyAccidentSearchService policyAccidentSearchService,AccidentProductService accidentProductService,AgentService agentService,PolicyHolderService policyHolderService, ModelMapper modelMapper) {

        this.policyAccidentService = policyAccidentService;

        this.policyAccidentSearchService = policyAccidentSearchService;

        this.accidentProductService = accidentProductService;

        this.agentService = agentService;

        this.policyHolderService = policyHolderService;

        this.modelMapper = modelMapper;
    }

    /*
    CRUD for Accident Policy
     */
    @PostMapping("allocate-accident-plan")
    //@Audit(resource = "Accident Plan", action = "Allocate Accident Plan")
    public ApiResponse allocateAccidentPlan(@Valid @RequestBody PolicyAccidentCreateDto policyAccidentCreateDto) {
        log.info("##############Request: {}", policyAccidentCreateDto);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        PolicyAccident policyAccident = modelMapper.map(policyAccidentCreateDto,PolicyAccident.class);

        log.info("##############GETTING & SETTING PRODUCT HERE");
        //get product
        AccidentProduct accidentProduct = accidentProductService.getOne(policyAccidentCreateDto.getAccidentProductId());
        //get agent
        Agent agent = agentService.getOne(policyAccidentCreateDto.getAgentId());

        log.info("##############GETTING & SETTING PRODUCT HERE");
        //set product
        policyAccident.setAccidentProduct(accidentProduct);
        //set agent
        policyAccident.setAgent(agent);

        //generate Policy Number
        policyAccident.setPolicyNumber(OtherUtils.generatePolicyNumberForPrincipal());
        //set policy type
        policyAccident.setPolicyType(PolicyType.ACCIDENT);
        //set default policy upgrade status
        policyAccident.setPolicyUpgradeStatus(PolicyUpgradeStatus.INITIAL);

        if (policyAccidentService.findPolicyAccidentByPolicyHolder_Id(policyAccidentCreateDto.getPolicyHolderId()).size()!= 0) {
            throw new BusinessValidationException("This policyholder already has an Accident Policy, consider upgrading");
        }


        log.info("##############GETTING & SETTING POLICY HOLDER HERE");
        PolicyHolder policyHolder = policyHolderService.getOne(policyAccidentCreateDto.getPolicyHolderId());
        //set policy holder
        policyAccident.setPolicyHolder(policyHolder);
        //perform validations
        if (PolicyBusinessRuleUtils.isPrincipalAgeAboveEntryAge(policyHolder.getDateOfBirth(), Constants.PRINCIPAL_ENTRY_AGE_ACCIDENT)) {

        }else {
            throw new BusinessValidationException("Life assured age at entry cannot be more than  " + Constants.PRINCIPAL_ENTRY_AGE_ACCIDENT + " years.");
        }


        return new ApiResponse(200,"SUCCESS", policyAccidentService.allocateProduct(policyAccident));

    }

    @GetMapping("accident-policy/{id}")
    @Audit(resource = "Accident Plan", action = "Get accident Policy")
    public ApiResponse getAccidentPolicy(@PathVariable("id") Long id) {
        log.info("############## Request to get : {}", id);
        return new ApiResponse(200,"SUCCESS",policyAccidentService.getOne(id));
    }

    @GetMapping("get-all")
    @Audit(resource = "Accident Plan", action = "Get All accident Policies")
    public ApiResponse getAllAccidentPolicies() {
        log.info("############## Request to get All Accident Policies: {}");
        return new ApiResponse(200,"SUCCESS",policyAccidentService.getAll());
    }




    @DeleteMapping("accident-policy/{id}")
    @Audit(resource = "Accident Plan", action = "Delete Accident Policy")
    public ResponseEntity deleteAccidentPlan(@PathVariable("id") Long id) {
        log.info("##############Request to delete : {}", id);
        policyAccidentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Audit(resource = "Accident Plan", action = "Change Payment Method and Frequency")
    public ResponseEntity<PolicyAccidentResultDTO> changePaymentMethodAndFrequency(@Valid @RequestBody PolicyAccidentUpdateCreateDto policyAccidentCreateDto) {
        log.info("############## Request to update: {}", policyAccidentCreateDto);
        return ResponseEntity.ok(policyAccidentService.changePaymentMethodAndFrequency(policyAccidentCreateDto));

    }

    @PutMapping("change-product/{policyId}/{accidentId}")
    @Audit(resource = "Accident Plan", action = "Change Plan")
    public ResponseEntity<PolicyAccidentResultDTO> downgradeProduct(@PathVariable("policyId") Long policyId,
                                                                 @PathVariable("accidentId") Long accidentId) {
        log.info("##############Request to change product: {}, {}", policyId, accidentId);

        return ResponseEntity.ok(policyAccidentService.downgradeProduct(policyId, accidentId));

    }


    @PutMapping("upgrade-product/{policyId}/{accidentId}")
    @Audit(resource = "Accident Plan", action = "Change Plan")
    public ResponseEntity<PolicyAccidentResultDTO> upgradeProduct(@PathVariable("policyId") Long policyId,
                                                                 @PathVariable("accidentId") Long accidentId) {
        log.info("##############Request to upgrade product: {}, {}", policyId, accidentId);

        return ResponseEntity.ok(policyAccidentService.upgradeProduct(policyId, accidentId));

    }

   /* @GetMapping("get-all-accident-policies/{page}/{size}")
    @Audit(resource = "Accident Plan", action = "Get All Accident Policies")
    ResponseEntity<Page<PolicyAccidentResultDTO>> getAllAccidentPolicies(@PathVariable("page") int page, @PathVariable("size") int size) {
        log.info("############# #Request to get : {}, {}", page, size);
        return ResponseEntity.ok(policyAccidentService.findAll(PageRequest.of(page, size)));
    }

    */

    /**
     * @param searchCriteria
     * @param page
     * @param size
     * @return
     */

    @PostMapping("search/{page}/{size}")
    @Audit(resource = "Accident Plan", action = "Search accident policy")
    public ResponseEntity<List<PolicyAccidentResultDTO>> searchPolicyAccidents(@RequestParam(value = "searchCriteria", required = false) String searchCriteria,
                                                                               @PathVariable int page, @PathVariable int size) {
        log.info("############## Request to search : {}", searchCriteria.toString());
        return ResponseEntity.ok(policyAccidentSearchService.fuzzySearch(searchCriteria, page, size));
    }
}
