package zw.co.mynhaka.policyservice.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.mynhaka.policyservice.audit.Audit;
import zw.co.mynhaka.policyservice.domain.dto.policy.PolicyResultDTO;
import zw.co.mynhaka.policyservice.service.PolicyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/policy", produces = MediaType.APPLICATION_JSON_VALUE)
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping("get-all-policies/{page}/{size}")
    @Audit(resource = "Policy", action = "Create Policy")
    public ResponseEntity<List<PolicyResultDTO>> getAll(
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(policyService.findAll(page, size));
    }

    @GetMapping("get-policy-by-policy-number/{policyNumber}")
    @Audit(resource = "Policy", action = "Get Policy")
    public ResponseEntity<PolicyResultDTO> getByPolicyNumber (
            @PathVariable("policyNumber") String policyNumber
    ) {
        return ResponseEntity.ok(policyService.findByPolicyNumber(policyNumber));
    }

    @GetMapping("get-policy/{id}")
    @Audit(resource = "Policy", action = "Get Policy")
    public ResponseEntity<PolicyResultDTO> get (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(policyService.findById(id));
    }
}
