package zw.co.mynhaka.polad.api.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.mynhaka.polad.reports.claims.IAccidentClaimReport;
import zw.co.mynhaka.polad.service.iface.reports.InvestmentSettlementService;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/report/investment", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvestmentSettlementController {

    private final InvestmentSettlementService investmentSettlementService;

    public InvestmentSettlementController(final InvestmentSettlementService investmentSettlementService) {
        this.investmentSettlementService = investmentSettlementService;
    }

    @GetMapping("surrender-settlement-time")
    public ResponseEntity<IAccidentClaimReport> getSurrenderSettlementTime() {
        return ResponseEntity.ok(investmentSettlementService.getSurrenderSettlementTime());
    }

    @GetMapping("partial-withdrawal-settlement-time")
    public ResponseEntity<IAccidentClaimReport> getPartialSettlementWithdrawalTime() {
        return ResponseEntity.ok(investmentSettlementService.getPartialWithdrawalTime());

    }
}
