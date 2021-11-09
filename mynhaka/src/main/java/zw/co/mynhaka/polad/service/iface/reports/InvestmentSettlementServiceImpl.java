package zw.co.mynhaka.polad.service.iface.reports;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.reports.claims.IAccidentClaimReport;
import zw.co.mynhaka.polad.repository.ClaimSavingsDeathRepository;


@Service
@Slf4j
public class InvestmentSettlementServiceImpl implements InvestmentSettlementService {

    private final ClaimSavingsDeathRepository claimSavingsRepository;

    public InvestmentSettlementServiceImpl(final ClaimSavingsDeathRepository claimSavingsRepository) {
        this.claimSavingsRepository = claimSavingsRepository;
    }

    @Override
    public IAccidentClaimReport getSurrenderSettlementTime() {
        return null;
    }

    @Override
    public IAccidentClaimReport getPartialWithdrawalTime() {
        return null;
    }
}
