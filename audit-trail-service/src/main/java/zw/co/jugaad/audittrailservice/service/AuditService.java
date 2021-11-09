package zw.co.jugaad.audittrailservice.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import model.entity.AuditAction;
import model.repository.AuditActionRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import zw.co.invenico.springcommonsmodule.exception.RecordNotFoundException;
import zw.co.invenico.springcommonsmodule.jpa.CustomSpecificationTemplateImplBuilder;
import zw.co.jugaad.audittrailservice.aspects.AuditActionRequest;
import zw.co.jugaad.audittrailservice.reports.JasperReportDTO;
import zw.co.jugaad.audittrailservice.reports.JasperReportGenerator;
import zw.co.jugaad.audittrailservice.reports.ReportFormat;
import zw.co.jugaad.audittrailservice.utils.Slf4jLoggingAuditTrailManager;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class AuditService {


    @Autowired
    DataSource dataSource;

    public final AuditActionRepository auditActionRepository;
    private final JasperReportGenerator jasperReportGenerator;
    private final Slf4jLoggingAuditTrailManager slf4jLoggingAuditTrailManager;

    public AuditService(AuditActionRepository auditActionRepository,
                        JasperReportGenerator jasperReportGenerator,
                        Slf4jLoggingAuditTrailManager slf4jLoggingAuditTrailManager) {
        this.auditActionRepository = auditActionRepository;
        this.jasperReportGenerator = jasperReportGenerator;
        this.slf4jLoggingAuditTrailManager = slf4jLoggingAuditTrailManager;
    }

    public static Specification<AuditAction> isBetween(LocalDateTime startDate,
                                                       LocalDateTime endDate) {
        return (root, query, builder) -> builder.between(root.get("dateTime"),
                startDate, endDate);
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

    public AuditAction findById(Long id) {
        return auditActionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Audit not found"));
    }

    public Page<AuditAction> findAll(PageRequest pageRequest, String search) {
        if (isNull(search)) {
            return this.auditActionRepository.findAll(pageRequest);
        } else {
            Specification<AuditAction> spec = (new CustomSpecificationTemplateImplBuilder<AuditAction>())
                    .buildSpecification(search);
            return this.auditActionRepository.findAll(spec, pageRequest);
        }
    }

    public AuditAction create(AuditActionRequest auditActionRequest) {

        AuditAction auditAction = AuditAction.builder()
                .actionPerformed(auditActionRequest.getActionPerformed())
                .clientIpAddress(auditActionRequest.getClientIpAddress())
                .dateTime(auditActionRequest.getDateTime())
                .resource(auditActionRequest.getResource())
                .userAgent(auditActionRequest.getUserAgent())
                .username(auditActionRequest.getUsername())
                .payload(auditActionRequest.getPayload())
                .build();

        auditAction = auditActionRepository.save(auditAction);

        slf4jLoggingAuditTrailManager.record(auditAction);

        return auditAction;
    }

    public Page<AuditAction> findAll(PageRequest pageRequest, String search, LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));

        if (isNull(search)) {

            return this.auditActionRepository.findAllByDateTimeIsBetween(startDateTime, endDateTime,
                    pageRequest);

        } else {

            Specification<AuditAction> spec = (new CustomSpecificationTemplateImplBuilder<AuditAction>())
                    .buildSpecification(search);

            spec = spec.and(isBetween(startDateTime, endDateTime));

            return this.auditActionRepository.findAll(spec, pageRequest);
        }
    }

    public ResponseEntity<Resource> generateReport(HttpServletRequest servletRequest,
                                                   String search, String reportFormat,
                                                   LocalDate startDate, LocalDate endDate) throws SQLException, IOException, JRException {

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/audit-trail-reportv1.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("audit-trail-reportv1")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    private ResponseEntity<Resource> getResourceResponseEntity(HttpServletRequest servletRequest, JasperReportDTO jasperReportDTO) {
        val resource = jasperReportGenerator.generateReport(jasperReportDTO);
        String contentType = getContentType(servletRequest, resource);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public ResponseEntity<Resource> generateBillerReport(HttpServletRequest servletRequest, String search, String reportFormat) throws SQLException, IOException, JRException {


        ClassPathResource resource = new ClassPathResource("reports/billers.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("billers")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(null)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateEvalueReport(HttpServletRequest servletRequest, String search, String reportFormat, LocalDate startDate, LocalDate endDate) throws SQLException, IOException, JRException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/evalue.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("evalue")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateExceptionReport(HttpServletRequest servletRequest, String search, String reportFormat, LocalDate startDate, LocalDate endDate) throws IOException, JRException, SQLException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/exception.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("exception")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateFeesReport(HttpServletRequest servletRequest, String search, String reportFormat, LocalDate startDate, LocalDate endDate) throws IOException, JRException, SQLException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/fees.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("fees")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateMerchantReport(HttpServletRequest servletRequest, String search, String reportFormat) throws IOException, JRException, SQLException {


        ClassPathResource resource = new ClassPathResource("reports/merchant.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("merchant")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(null)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateSubscribersReport(HttpServletRequest servletRequest, String search, String reportFormat) throws IOException, JRException, SQLException {


        ClassPathResource resource = new ClassPathResource("reports/subscribers.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("subscribers")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(null)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateTaxReport(HttpServletRequest servletRequest, String search, String reportFormat, LocalDate startDate, LocalDate endDate) throws IOException, JRException, SQLException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/tax.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("tax")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateTransactionsReport(HttpServletRequest servletRequest, String search, String reportFormat, LocalDate startDate, LocalDate endDate) throws IOException, JRException, SQLException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/transactions.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("transactions")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateBillerTransactionsReport(HttpServletRequest servletRequest, Long billerId, String reportFormat, LocalDate startDate, LocalDate endDate) throws IOException, JRException, SQLException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_BILLER_ID", Long.valueOf(billerId));
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/transactions-biller.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("biller-transactions")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateDeviceTransactionsReport(HttpServletRequest servletRequest, String imei, String reportFormat, LocalDate startDate, LocalDate endDate) throws IOException, JRException, SQLException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_DEVICE_ID", String.valueOf(imei));
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/transactions-device.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("device-transactions")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateMerchantTransactionsReport(HttpServletRequest servletRequest, Long agentId, String reportFormat, LocalDate startDate, LocalDate endDate) throws IOException, JRException, SQLException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_MERCHANT_ID", Long.valueOf(agentId));
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/transactions-merchant.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("merchant-transactions")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateOperatorTransactionsReport(HttpServletRequest servletRequest, Long operatorId, String reportFormat, LocalDate startDate, LocalDate endDate) throws IOException, JRException, SQLException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_OPERATOR_ID", Long.valueOf(operatorId));
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/transactions-operator.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("operator-transactions")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateProductTransactionsReport(HttpServletRequest servletRequest, Long productId, String reportFormat, LocalDate startDate, LocalDate endDate) throws SQLException, JRException, IOException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_PRODUCT_ID", Long.valueOf(productId));
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/transactions-product.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("product-transactions")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }

    public ResponseEntity<Resource> generateSubscriberTransactionsReport(HttpServletRequest servletRequest, Long subscriberId, String reportFormat, LocalDate startDate, LocalDate endDate) throws SQLException, IOException, JRException {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59));


        HashMap params = new HashMap();
        params.put("FILTER_SUBSCRIBER_ID", Long.valueOf(subscriberId));
        params.put("FILTER_START_DATE", Timestamp.valueOf(startDateTime));
        params.put("FILTER_END_DATE", Timestamp.valueOf(endDateTime));

        ClassPathResource resource = new ClassPathResource("reports/transactions-subscriber.jrxml");
        InputStream file = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(
                file
        );
        System.out.println("Done compiling");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());


        val jasperReportDTO = JasperReportDTO.builder()
                .reportName("transactions-subscriber")
                .reportFormat(ReportFormat.valueOf(reportFormat))
                .parameters(params)
                .source(dataSource.getConnection())
                .build();

        return getResourceResponseEntity(servletRequest, jasperReportDTO);
    }
}
