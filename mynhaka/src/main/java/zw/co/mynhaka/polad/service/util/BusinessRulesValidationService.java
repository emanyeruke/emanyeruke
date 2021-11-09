package zw.co.mynhaka.polad.service.util;

import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.domain.model.PremiumPayment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public interface BusinessRulesValidationService extends Function<PolicySavings, Boolean> {
    static BusinessRulesValidationService canBeSurrendered(List<PremiumPayment> premiumPayments) {
        return policySavings -> ChronoUnit.MONTHS.between(policySavings.getCommencementDate(), LocalDate.now()) >= 12
                && premiumPayments.size() >= 12;
    }

    static BusinessRulesValidationService canBeWithdrawn(List<PremiumPayment> premiumPayments, LocalDate lastWithdrawalDate) {
        return policySavings -> {
            Boolean canBeSurrendered = canBeSurrendered(premiumPayments).apply(policySavings);
            if (lastWithdrawalDate != null)
                return canBeSurrendered && ChronoUnit.MONTHS.between(lastWithdrawalDate, LocalDate.now()) >= 12;

            return canBeSurrendered;
        };
    }
}
