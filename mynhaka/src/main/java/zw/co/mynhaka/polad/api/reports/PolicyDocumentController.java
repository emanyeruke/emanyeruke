package zw.co.mynhaka.polad.api.reports;


import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zw.co.mynhaka.polad.service.contracts.ExportReportService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/reports/policy")
public class PolicyDocumentController {

    private final ExportReportService exportReportService;

    public PolicyDocumentController(ExportReportService exportReportService) {
        this.exportReportService = exportReportService;
    }

    @GetMapping("/policies")
    public ResponseEntity<Resource> generatePolicyDocument(HttpServletRequest servletRequest, @RequestParam(defaultValue = "CSV") String reportFormat) throws SQLException, IOException, JRException {
        return exportReportService.generatePoliciesReport(servletRequest, reportFormat);
    }

    @GetMapping("/policy-document")
    public ResponseEntity<Resource> generatePolicyDocument(HttpServletRequest servletRequest, @RequestParam(defaultValue = "CSV") String reportFormat, @RequestParam String policyNumber) throws SQLException, IOException, JRException {
        return exportReportService.generatePolicyDocument(servletRequest,  policyNumber,reportFormat);
    }


}
