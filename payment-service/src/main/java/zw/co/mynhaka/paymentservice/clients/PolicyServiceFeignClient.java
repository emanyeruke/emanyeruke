package zw.co.mynhaka.paymentservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zw.co.mynhaka.paymentservice.domain.dto.policy.PolicyResultDTO;

@FeignClient("policy-service")
public interface PolicyServiceFeignClient {
    @GetMapping("/api/v1/savings-policy/get-policy/{policyNumber}")
    PolicyResultDTO getPolicy(
            @PathVariable("policyNumber") String policyNumber
    );
}
