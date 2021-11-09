package zw.co.mynhaka.polad.service.iface.reports;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.repository.ClaimSavingsDeathRepository;
import zw.co.mynhaka.polad.service.iface.reports.dtos.TotalSubmissionsResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.WeeklySubmissionsResult;


@Service
@Slf4j
public class SubmissionsServiceImpl implements SubmissionsService {

    private final ClaimSavingsDeathRepository claimSavingsRepository;

    public SubmissionsServiceImpl(final ClaimSavingsDeathRepository claimSavingsRepository) {
        this.claimSavingsRepository = claimSavingsRepository;
    }

    @Override
    public WeeklySubmissionsResult getSurrenderSettlementTime() {
        return null;
    }

    @Override
    public TotalSubmissionsResult getPartialSettlementTime() {
        return null;
    }
}
