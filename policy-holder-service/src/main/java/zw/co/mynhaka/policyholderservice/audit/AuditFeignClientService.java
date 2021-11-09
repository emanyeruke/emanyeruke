package zw.co.mynhaka.policyholderservice.audit;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "mynhaka-audit-trail-service")

public interface AuditFeignClientService {

    @PostMapping("/api/v1/audits/create")
    Object create(@RequestBody AuditActionRequest auditActionRequest);
}
