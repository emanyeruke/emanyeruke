package zw.co.mynhaka.polad.service.iface.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.repository.ClaimAccidentRepository;
import zw.co.mynhaka.polad.repository.ClaimComprehensiveRepository;
import zw.co.mynhaka.polad.repository.ClaimFuneralRepository;
import zw.co.mynhaka.polad.service.iface.reports.dtos.DeathClaimsPerProductResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.MonthlyReportResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.RefundPaymentReasonResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.WeeklyClaimsResult;


@Service
@Slf4j
public class ClaimsAnalysisServiceImpl implements ClaimsAnalysisService {

    private final ClaimAccidentRepository claimAccidentRepository;
    private final ClaimFuneralRepository claimFuneralRepository;
    private final ClaimComprehensiveRepository claimComprehensiveRepository;

    public ClaimsAnalysisServiceImpl(final ClaimAccidentRepository claimAccidentRepository,
                                     final ClaimFuneralRepository claimFuneralRepository,
                                     final ClaimComprehensiveRepository claimComprehensiveRepository) {
        this.claimAccidentRepository = claimAccidentRepository;
        this.claimFuneralRepository = claimFuneralRepository;
        this.claimComprehensiveRepository = claimComprehensiveRepository;
    }


    @Override
    public DeathClaimsPerProductResult getDeathClaimsPerProduct() {
        return null;
    }

    @Override
    public RefundPaymentReasonResult getRefundPaymentReason() {
        return null;
    }

    @Override
    public MonthlyReportResult getMonthlyClaimsPaid() {
        return null;
    }

    @Override
    public WeeklyClaimsResult getWeeklyClaimsResult() {
        return null;
    }


}
