package zw.co.mynhaka.polad.service.impl;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.reports.JJasperReportDTO;
import zw.co.mynhaka.polad.reports.JasperReportGenerator;
import zw.co.mynhaka.polad.reports.ReportFormat;
import zw.co.mynhaka.polad.service.contracts.ExportReportService;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

@Service
@Slf4j
public class ExportReportServiceImpl implements ExportReportService {

    @Autowired
    DataSource dataSource;

    private final JasperReportGenerator jasperReportGenerator;

    public ExportReportServiceImpl(JasperReportGenerator jasperReportGenerator) {
        this.jasperReportGenerator = jasperReportGenerator;
    }

    private static String getContentType(HttpServletRequest httpServletRequest, Resource resource) {

        String contentType = null;
        try {
            contentType = httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error("### Could not determine file type. due to {}", ex.getMessage());
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }


    private ResponseEntity<Resource> getResourceResponseEntity(HttpServletRequest servletRequest, JJasperReportDTO jasperReportDTO) {
        val resource = jasperReportGenerator.generateReport(jasperReportDTO);
        String contentType = getContentType(servletRequest, resource);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    public ResponseEntity<Resource> generatePoliciesReport(HttpServletRequest servletRequest, String reportFormat) throws SQLException, IOException, JRException {


        ClassPathResource resource = new ClassPathResource("reports/policies.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());


        val jasperReportDTO = JJasperReportDTO.builder()
                .reportName("policies")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(null)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }


    @Override
    public ResponseEntity<Resource> generatePolicyDocument(HttpServletRequest servletRequest, String policyNumber, String reportFormat) throws SQLException, IOException, JRException {


        HashMap params = new HashMap();
        params.put("POLICY_NUMBER",policyNumber);


        ClassPathResource resource = new ClassPathResource("reports/policyDocument.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JJasperReportDTO.builder()
                .reportName("policyDocument")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }
}
