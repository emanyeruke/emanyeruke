package zw.co.jugaad.audittrailservice.api;

import lombok.extern.slf4j.Slf4j;
import model.entity.AuditAction;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.jugaad.audittrailservice.aspects.AuditActionRequest;
import zw.co.jugaad.audittrailservice.service.AuditService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/v1/audits")
public class AuditRestController {

    public final AuditService auditService;

    public AuditRestController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditAction> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(auditService.findById(id));
    }

    @GetMapping("/all/{page}/{size}")
    /*@Audit(resource = "Audit trail", action = "View all audit trails")*/
    public ResponseEntity<Page<AuditAction>> getAll(@PathVariable int page, @PathVariable int size,
                                                    @RequestParam(required = false) String search) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(auditService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateTime")), search));
    }

    @GetMapping("/date-range/{page}/{size}")
    //@Audit(resource = "Audit trail", action = "View all audit trails")
    public ResponseEntity<Page<AuditAction>> getAll(@PathVariable Integer page,
                                                    @PathVariable Integer size,
                                                    @RequestParam(required = false) String search,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(auditService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateTime")), search, startDate, endDate));

    }

    @GetMapping("/generate-audit-report")
    public ResponseEntity<Resource> generateAuditReport(HttpServletRequest servletRequest,
                                                        @RequestParam(defaultValue = "CSV") String reportFormat,
                                                        @RequestParam(required = false) String search,
                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateReport(servletRequest, search, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-biller-report")
    public ResponseEntity<Resource> generateBillersReport(HttpServletRequest servletRequest,
                                                          @RequestParam(defaultValue = "CSV") String reportFormat,
                                                          @RequestParam(required = false) String search) throws SQLException, IOException, JRException {
        return auditService.generateBillerReport(servletRequest, search, reportFormat);
    }

    @GetMapping("/generate-evalue-report")
    public ResponseEntity<Resource> generateEvalueReport(HttpServletRequest servletRequest,
                                                         @RequestParam(defaultValue = "CSV") String reportFormat,
                                                         @RequestParam(required = false) String search,
                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateEvalueReport(servletRequest, search, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-exception-report")
    public ResponseEntity<Resource> generateExceptionReport(HttpServletRequest servletRequest,
                                                            @RequestParam(defaultValue = "CSV") String reportFormat,
                                                            @RequestParam(required = false) String search,
                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateExceptionReport(servletRequest, search, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-fees-report")
    public ResponseEntity<Resource> generateFeesReport(HttpServletRequest servletRequest,
                                                       @RequestParam(defaultValue = "CSV") String reportFormat,
                                                       @RequestParam(required = false) String search,
                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateFeesReport(servletRequest, search, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-merchant-report")
    public ResponseEntity<Resource> generateMerchantReport(HttpServletRequest servletRequest,
                                                           @RequestParam(defaultValue = "CSV") String reportFormat,
                                                           @RequestParam(required = false) String search) throws SQLException, IOException, JRException {
        return auditService.generateMerchantReport(servletRequest, search, reportFormat);
    }


    @GetMapping("/generate-subscribers-report")
    public ResponseEntity<Resource> generateSubscribersReport(HttpServletRequest servletRequest,
                                                              @RequestParam(defaultValue = "CSV") String reportFormat,
                                                              @RequestParam(required = false) String search) throws SQLException, IOException, JRException {
        return auditService.generateSubscribersReport(servletRequest, search, reportFormat);
    }

    @GetMapping("/generate-tax-report")
    public ResponseEntity<Resource> generateTaxReport(HttpServletRequest servletRequest,
                                                      @RequestParam(defaultValue = "CSV") String reportFormat,
                                                      @RequestParam(required = false) String search,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateTaxReport(servletRequest, search, reportFormat, startDate, endDate);
    }


    @GetMapping("/generate-transactions-report")
    public ResponseEntity<Resource> generateTransactionsReport(HttpServletRequest servletRequest,
                                                               @RequestParam(defaultValue = "CSV") String reportFormat,
                                                               @RequestParam(required = false) String search,
                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateTransactionsReport(servletRequest, search, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-biller-transactions-report")
    public ResponseEntity<Resource> generateBillerTransactionsReport(HttpServletRequest servletRequest,
                                                                     @RequestParam(defaultValue = "CSV") String reportFormat,
                                                                     @RequestParam(required = true) Long billerId,
                                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateBillerTransactionsReport(servletRequest, billerId, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-device-transactions-report")
    public ResponseEntity<Resource> generateDeviceTransactionsReport(HttpServletRequest servletRequest,
                                                                     @RequestParam(defaultValue = "CSV") String reportFormat,
                                                                     @RequestParam(required = true) String imei,
                                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateDeviceTransactionsReport(servletRequest, imei, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-merchant-transactions-report")
    public ResponseEntity<Resource> generateMerchantTransactionsReport(HttpServletRequest servletRequest,
                                                                       @RequestParam(defaultValue = "CSV") String reportFormat,
                                                                       @RequestParam(required = true) Long merchantId,
                                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateMerchantTransactionsReport(servletRequest, merchantId, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-operator-transactions-report")
    public ResponseEntity<Resource> generateOperatorTransactionsReport(HttpServletRequest servletRequest,
                                                                       @RequestParam(defaultValue = "CSV") String reportFormat,
                                                                       @RequestParam(required = true) Long operatorId,
                                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateOperatorTransactionsReport(servletRequest, operatorId, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-product-transactions-report")
    public ResponseEntity<Resource> generateProductTransactionsReport(HttpServletRequest servletRequest,
                                                                      @RequestParam(defaultValue = "CSV") String reportFormat,
                                                                      @RequestParam(required = true) Long productId,
                                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateProductTransactionsReport(servletRequest, productId, reportFormat, startDate, endDate);
    }

    @GetMapping("/generate-subscriber-transactions-report")
    public ResponseEntity<Resource> generateSubscribersTransactionsReport(HttpServletRequest servletRequest,
                                                                          @RequestParam(defaultValue = "CSV") String reportFormat,
                                                                          @RequestParam Long subscriberId,
                                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws SQLException, IOException, JRException {
        return auditService.generateSubscriberTransactionsReport(servletRequest, subscriberId, reportFormat, startDate, endDate);
    }


    @PostMapping("create")
    public AuditAction create(@RequestBody AuditActionRequest auditActionRequest) {
        log.info("######################### Creating a log here using aspects {}", auditActionRequest.toString());
        return auditService.create(auditActionRequest);
    }


}
