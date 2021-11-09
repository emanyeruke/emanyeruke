package zw.co.mynhaka.polad.api.crud;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.accident.ClaimAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ClaimComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.funeral.ClaimFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderUpdateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.model.Employer;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.search.PolicyHolderSearchService;
import zw.co.mynhaka.polad.service.iface.*;
import zw.co.mynhaka.polad.service.util.PolicyBusinessRuleUtils;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/policyholder", produces = MediaType.APPLICATION_JSON_VALUE)
public class PolicyHolderController {


    private final PolicyHolderService policyHolderService;
    private final PolicyAccidentService policyAccidentService;
    private final ClaimFuneralService claimFuneralService;
    private final ClaimAccidentService claimAccidentService;
    private final ClaimComprehensiveService claimComprehensiveService;
    private final PolicyHolderSearchService policyHolderSearchService;
    private  final  EmployerService employerService;
    private final ModelMapper modelMapper;

    @GetMapping("/get-all-policyholders")
    public ApiResponse findALlPolicyHolders() {

        return new ApiResponse(200,"SUCCESS",policyHolderService.findAll());
    }


    @PostMapping("/add-policyholder")
    public ApiResponse createPolicyHolder(@Valid @RequestBody PolicyHolderCreateDto policyHolderCreateDto) {
        log.info("####### Request to add policyholder: {}", policyHolderCreateDto.toString());
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        PolicyHolder policyHolder = modelMapper.map(policyHolderCreateDto,PolicyHolder.class);

        log.info("########### I have checked employer this stage");
        final boolean isBelowAge = PolicyBusinessRuleUtils.isPrincipalAbove18(policyHolderCreateDto.getDateOfBirth(), 18);
        log.info("########### I have checked age limit this stage");

        log.info("########### I have reached this stage");
        final boolean hasEmployer = policyHolderCreateDto.getEmployerId() != null && policyHolderCreateDto.getEmployerId() > 0;

        if (hasEmployer) {
                log.info("########### I have reached this employer stage");
        Employer employer = employerService.getOne(policyHolderCreateDto.getEmployerId());
        policyHolder.setEmployer(employer);
        }

        log.info("Request received");

        return new ApiResponse(200,"SUCCESS",policyHolderService.add(policyHolder));
    }


    @PostMapping("/faker-policyholder")
    public ResponseEntity<?> fakePolicyHolderCreator() {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        policyHolderService.saveFaker();
        return ResponseEntity.ok().build();
    }


    @GetMapping("/get-policyholder/{id}")
    public ResponseEntity<PolicyHolderResultDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(policyHolderService.findById(id));
    }

    @PutMapping("edit-policyholder/{id}")
    public ApiResponse updatePolicyHolder(@PathVariable("id") Long id,
                                          @Valid @RequestBody PolicyHolderUpdateDto policyHolderUpdateDto) {

        PolicyHolder policyHolder = modelMapper.map(policyHolderUpdateDto,PolicyHolder.class);
        //old Record
        PolicyHolder oldRecord = policyHolderService.getOne(id);

        //get employer and financial Advisor from old record
        oldRecord.setEmployer(policyHolder.getEmployer());

        policyHolder.setId(oldRecord.getId());


        return new ApiResponse(200,"SUCCESS",policyHolderService.update(policyHolder));
    }

    @DeleteMapping("delete-policyholder/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        policyHolderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

/*
Get Policies
 */

    @GetMapping("get-policyholder-accident/{id}")
    public ResponseEntity<List<PolicyAccidentResultDTO>> findAccidentPoliciesByPolicyHolderId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(policyHolderService.getAccidentPolicy(id));
    }

    @GetMapping("get-policyholder-funeral/{id}")
    public ResponseEntity<List<PolicyFuneralResultDTO>> findFuneralPoliciesByPolicyHolderId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(policyHolderService.getFuneralPolicy(id));
    }

    @GetMapping("get-policyholder-savings/{id}")
    public ResponseEntity<List<PolicySavingsResultDTO>> findSavingsPoliciesByPolicyHolderId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(policyHolderService.getSavingsPolicy(id));
    }

    @GetMapping("get-policyholder-comprehensive/{id}")
    public ResponseEntity<List<PolicyComprehensiveResultDTO>> findComprehensivePoliciesByPolicyHolderId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(policyHolderService.getComprehensivePolicy(id));
    }

    @GetMapping("all-accident-claims/{id}")
    public ResponseEntity<List<ClaimAccidentResultDTO>> findAllAccidentClaims(@PathVariable("id") Long id) {

        return ResponseEntity.ok(claimAccidentService.findAllByPolicyHolder(id));
    }

    @GetMapping("all-funeral-claims/{id}")
    public ResponseEntity<List<ClaimFuneralResultDTO>> findAllFuneralClaims(@PathVariable("id") Long id) {
        return ResponseEntity.ok(claimFuneralService.findAllByPolicyHolder(id));
    }

    @GetMapping("all-comprehensive-claims/{id}")
    public ResponseEntity<List<ClaimComprehensiveResultDTO>> findAllComprehensiveClaims(@PathVariable("id") Long id) {
        return ResponseEntity.ok(claimComprehensiveService.findAllByPolicyHolder(id));
    }

    @PostMapping("search/{page}/{size}")
    public ResponseEntity<List<PolicyHolderResultDTO>> searchPolicyHolder(@RequestParam(value = "searchCriteria", required = false) String searchCriteria,
                                                                          @PathVariable int page, @PathVariable int size) {

        /*if (Objects.isNull(searchCriteria)) {
            return ResponseEntity.ok(policyHolderService.findAll(PageRequest.of(page, size)));
        } else {
            Specification<PolicyHolder> spec = (new CustomSpecificationTemplateImplBuilder<PolicyHolder>())
                    .buildSpecification(searchCriteria);

            return ResponseEntity.ok(policyHolderService.searchAll(spec, PageRequest.of(page, size)));
        }*/

        return ResponseEntity.ok(policyHolderSearchService.fuzzySearch(searchCriteria, page, size));


    }


}



