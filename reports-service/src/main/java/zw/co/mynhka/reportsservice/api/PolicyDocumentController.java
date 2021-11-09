package zw.co.mynhka.reportsservice.api;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reports/policy")
public class PolicyDocumentController {

    @GetMapping("/document/{policyNumber}")
    public ResponseEntity<Resource> generateSendMoneyReceipt(HttpServletRequest servletRequest, @PathVariable Long policyNumber) {
        return null;
    }
}
