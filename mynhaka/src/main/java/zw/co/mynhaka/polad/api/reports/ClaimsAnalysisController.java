package zw.co.mynhaka.polad.api.reports;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.mynhaka.polad.service.iface.reports.ClaimsAnalysisService;
import zw.co.mynhaka.polad.service.iface.reports.dtos.DeathClaimsPerProductResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.MonthlyReportResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.RefundPaymentReasonResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.WeeklyClaimsResult;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/report/claims-analysis", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClaimsAnalysisController {

    private final ClaimsAnalysisService claimsAnalysisService;

    public ClaimsAnalysisController(ClaimsAnalysisService claimsAnalysisService) {
        this.claimsAnalysisService = claimsAnalysisService;
    }

    @GetMapping("death-claims-per-product")
    public ResponseEntity<DeathClaimsPerProductResult> getDeathClaimsPerProduct() {
        return ResponseEntity.ok(claimsAnalysisService.getDeathClaimsPerProduct());
    }

    @GetMapping("refund-payment-reason")
    public ResponseEntity<RefundPaymentReasonResult> getRefundPaymentReason() {
        return ResponseEntity.ok(claimsAnalysisService.getRefundPaymentReason());

    }

    @GetMapping("monthly-claims-paid")
    public ResponseEntity<MonthlyReportResult> getMonthlyClaimsPaid() {
        return ResponseEntity.ok(claimsAnalysisService.getMonthlyClaimsPaid());

    }

    @GetMapping("weekly-claims-paid")
    public ResponseEntity<WeeklyClaimsResult> getWeeklyClaimsPaid() {
        return ResponseEntity.ok(claimsAnalysisService.getWeeklyClaimsResult());

    }

}
