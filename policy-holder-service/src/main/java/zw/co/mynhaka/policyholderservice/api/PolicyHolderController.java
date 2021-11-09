package zw.co.mynhaka.policyholderservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyholderservice.audit.Audit;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderUpdateDTO;
import zw.co.mynhaka.policyholderservice.service.PolicyHolderService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/policy-holder", produces = MediaType.APPLICATION_JSON_VALUE)
public class PolicyHolderController {

    private final PolicyHolderService policyHolderService;

    @PostMapping("create-policy-holder")
    @Audit(resource = "Policyholder", action = "Create PolicyHolder")
    public ResponseEntity<PolicyHolderResultDTO> createPolicyHolder (
            @RequestBody @Valid PolicyHolderCreateDTO policyHolderCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(policyHolderService.save(policyHolderCreateDTO));
    }

    @PutMapping("edit-policy-holder")
    @Audit(resource = "Policyholder", action = "Update PolicyHolder")
    public ResponseEntity<PolicyHolderResultDTO> editPolicyHolder (
            @RequestBody @Valid PolicyHolderUpdateDTO policyHolderUpdateDTO
    ) {
        return ResponseEntity.ok(policyHolderService.save(policyHolderUpdateDTO));
    }

    @GetMapping("get-policy-holder/{id}")
    @Audit(resource = "Policyholder", action = "View PolicyHolder")
    public ResponseEntity<PolicyHolderResultDTO> getPolicyHolder (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(policyHolderService.findById(id));
    }

    @GetMapping("get-all-policy-holders/{page}/{size}")
    @Audit(resource = "Policyholder", action = "Get all PolicyHolders")
    public ResponseEntity<Page<PolicyHolderResultDTO>> getAllPolicyHolders (
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(policyHolderService.findAll(page, size));
    }

    @GetMapping("get-all-policy-holders/{employerId}/policy-holders/{page}/{size}")
    @Audit(resource = "Policyholder", action = "Get all policyholders")
    public ResponseEntity<Page<PolicyHolderResultDTO>> getAllPolicyHolders (
            @PathVariable("employerId") Long employerId,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(policyHolderService.findAllByEmployer(employerId, page, size));
    }

    @DeleteMapping("delete-policy-holder/{id}")
    @Audit(resource = "Policyholder", action = "Delete PolicyHolder")
    public ResponseEntity<?> deletePolicyHolder(
            @PathVariable("id") Long id
    ) {
        policyHolderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
