package zw.co.mynhaka.policyholderservice.service.util;

import zw.co.mynhaka.policyholderservice.domain.models.PolicyHolder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import static zw.co.mynhaka.policyholderservice.service.util.PolicyHolderValidationService.PolicyHolderValidationResult;
import static zw.co.mynhaka.policyholderservice.service.util.PolicyHolderValidationService.PolicyHolderValidationResult.IS_BELOW_AGE;
import static zw.co.mynhaka.policyholderservice.service.util.PolicyHolderValidationService.PolicyHolderValidationResult.VALID;

public interface PolicyHolderValidationService extends Function<PolicyHolder, PolicyHolderValidationResult> {

    enum PolicyHolderValidationResult {
        VALID,
        IS_BELOW_AGE
    }

    static PolicyHolderValidationService isPrincipleAbove18() {
        return policyHolder -> {
            long years = ChronoUnit.YEARS.between(policyHolder.getDateOfBirth(), LocalDate.now());
            return years > 18 ? VALID : IS_BELOW_AGE;
        };
    }

    default PolicyHolderValidationService and (PolicyHolderValidationService other) {
        return policyHolder -> {
            PolicyHolderValidationResult validationResult = apply(policyHolder);
            return validationResult.equals(VALID) ? other.apply(policyHolder) : validationResult;
        };
    }
}
