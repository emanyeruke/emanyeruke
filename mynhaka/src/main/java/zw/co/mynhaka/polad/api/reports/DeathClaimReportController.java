package zw.co.mynhaka.polad.api.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.mynhaka.polad.service.iface.reports.DeathClaimsReportService;
import zw.co.mynhaka.polad.service.iface.reports.dtos.MonthlyReportResult;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/report/deatch-claims", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeathClaimReportController {


    private final DeathClaimsReportService deathClaimsReportService;

    public DeathClaimReportController(final DeathClaimsReportService deathClaimsReportService) {
        this.deathClaimsReportService = deathClaimsReportService;
    }

    /*
     @PostMapping("accidentclaim")
     public ResponseEntity<List<IAccidentClaimReport>> getReport(@RequestBody DateRange dateRange) {
         log.info("dates", dateRange.getStartDate());
         log.info("dates", dateRange.getEndDate());
         return ResponseEntity.ok(claimAccidentRepository.countClaimPaymentDuration(dateRange.getStartDate(), dateRange.getEndDate()));
     }

     @GetMapping("accidentclaim")
     public ResponseEntity<List<IAccidentClaimMonthlyReport>> getReportReportForMonth() {
         return ResponseEntity.ok(claimAccidentRepository.countClaimPaymentDurationByMonth());
     }

     @GetMapping("accidentclaim/all")
     public ResponseEntity<IAccidentClaimAllReport> getReportReportForAll() {
         return ResponseEntity.ok(claimAccidentRepository.countClaimPaymentDuratioFromBeginning());
     }
 */
    @GetMapping("monthly-report")
    public ResponseEntity<List<MonthlyReportResult>> getMonthlyReportForClaims() {
        return ResponseEntity.ok(deathClaimsReportService.getMonthlyReport());
    }
}