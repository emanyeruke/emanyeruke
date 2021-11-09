package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.model.PremiumPayment;
import zw.co.mynhaka.polad.service.iface.InterestService;
import zw.co.mynhaka.polad.service.iface.PenaltyService;
import zw.co.mynhaka.polad.service.iface.SurrenderValueCalculatorService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurrenderValueCalculatorServiceImpl implements SurrenderValueCalculatorService {

    private final InterestService interestService;
    private final PenaltyService penaltyService;

    @Override
    public BigDecimal calculateValue(List<PremiumPayment> premiumPayments) {
        BigDecimal penaltyRate = penaltyService.findPenaltyByMonth(premiumPayments.size()).getRate();
        BigDecimal surrender = BigDecimal.ZERO;

        for (int i = 1; i < premiumPayments.size(); i++) {
            BigDecimal rate = interestService.findInterestByMonth(i).getRate();
            BigDecimal premium = premiumPayments.get(i - 1).getAmount();
            surrender = surrender.add(premium.multiply(rate));
        }

        BigDecimal lastPremiumPayment = premiumPayments.get(premiumPayments.size() - 1).getAmount();
        surrender = surrender.add(lastPremiumPayment);

        return surrender.subtract(surrender.multiply(penaltyRate));
    }
}
