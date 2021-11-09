/*package zw.co.mynhaka.polad.api.crud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.PolicyResultDTO;
import zw.co.mynhaka.polad.domain.model.Policy;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.iface.PolicyService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/policies", produces = MediaType.APPLICATION_JSON_VALUE)
public class PolicyController {

    private final PolicyService policyService;
    private final PolicyHolderService policyHolderService;

    @GetMapping("{page}/{size}")
    public ApiResponse findAllPolicies(@PathVariable("page") int page,
                                @PathVariable("size") int size) {
        return new ApiResponse(200,"SUCCESS",policyService.findAll(page, size));
    }

    @GetMapping("all")
    public ApiResponse getAllPolicies() {
        return new ApiResponse(200,"SUCCESS",policyService.getAll());
    }

    @GetMapping("{id}")
    public ApiResponse getPolicyById( @PathVariable("id") Long id) {
        return new ApiResponse(200,"SUCCESS",policyService.getOne(id));
    }

    @GetMapping("{policyNumber}")
    public ApiResponse  getPolicyByByPolicyNumber( @PathVariable("policyNumber") String policyNumber) {
        return new ApiResponse (200,"SUCCESS",policyService.findByPolicyNumber(policyNumber));
    }

    @GetMapping("policyholder/{id}")
    public ApiResponse getPoliciesByPolicyHolder( @PathVariable("id") Long id ) {

        PolicyHolder policyHolder = policyHolderService.getOne(id);

        return new ApiResponse(200,"SUCCESS",policyService.getAllByPolicyHolder(policyHolder));
    }
}

 */
