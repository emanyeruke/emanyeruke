package zw.co.mynhaka.polad.api.reports;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.mynhaka.polad.service.iface.reports.SubmissionsService;
import zw.co.mynhaka.polad.service.iface.reports.dtos.TotalSubmissionsResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.WeeklySubmissionsResult;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/report/submissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubmissionsReportController {

    private final SubmissionsService submissionsService;

    public SubmissionsReportController(final SubmissionsService submissionsService) {
        this.submissionsService = submissionsService;
    }

    @GetMapping("weekly-submitted-business")
    public ResponseEntity<WeeklySubmissionsResult> getSurrenderSettlementTime() {
        return ResponseEntity.ok(submissionsService.getSurrenderSettlementTime());
    }

    @GetMapping("total-submissions")
    public ResponseEntity<TotalSubmissionsResult> getPartialSettlementWithdrawalTime() {
        return ResponseEntity.ok(submissionsService.getPartialSettlementTime());

    }

}
