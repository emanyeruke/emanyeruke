package zw.co.mynhaka.policyservice.service.utils;

import zw.co.mynhaka.policyservice.domain.model.Policy;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

public interface BusinessRulesValidationService extends Function<Policy, Boolean> {
    static BusinessRulesValidationService canPolicyBeWithdrawnOrSurrendered() {
        return policy -> {
            if (!(policy instanceof SavingsPolicy))
                return false;

            long policyActiveMonths = ChronoUnit.MONTHS.between(policy.getCommencementDate(), LocalDate.now());
            return policyActiveMonths >= 12;
        };
    }

    static BusinessRulesValidationService isWithdrawalAmountAcceptable(BigDecimal amount) {
        return policy -> {
            if (policy instanceof SavingsPolicy) {
                BigDecimal maxAcceptableWithdrawalAmount = ((SavingsPolicy) policy).getBalance().multiply(BigDecimal.valueOf(0.75));
                return amount.compareTo(maxAcceptableWithdrawalAmount) < 1; // Checks if amount is less or equal to maxAcceptableWithdrawalAmount
            }

            return false;
        };
    }
}
