package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.model.PremiumPayment;

import java.math.BigDecimal;
import java.util.List;

public interface SurrenderValueCalculatorService {
    BigDecimal calculateValue(List<PremiumPayment> premiumPayments);
}
