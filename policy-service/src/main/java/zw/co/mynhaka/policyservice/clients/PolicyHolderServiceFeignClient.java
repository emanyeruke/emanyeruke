package zw.co.mynhaka.policyservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zw.co.mynhaka.policyservice.domain.model.Agent;
import zw.co.mynhaka.policyservice.domain.model.PolicyHolder;

@FeignClient("policy-holder-service")
public interface PolicyHolderServiceFeignClient {
    @GetMapping(value = "/api/v1/policy-holder/get-policy-holder/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolicyHolder getPolicyHolder(@PathVariable("id") Long policyHolderId);

    @GetMapping(value = "/api/v1/agent/get-agent/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Agent getAgent(@PathVariable("id") Long agentId);
}
