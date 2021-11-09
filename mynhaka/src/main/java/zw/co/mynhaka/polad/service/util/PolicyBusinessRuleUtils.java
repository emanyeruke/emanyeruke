package zw.co.mynhaka.polad.service.util;


import zw.co.mynhaka.polad.service.exception.BusinessValidationException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static zw.co.mynhaka.polad.service.util.Util.calculateAge;


public class PolicyBusinessRuleUtils {

    private PolicyBusinessRuleUtils() {
    }


    public static boolean isPrincipalAgeAboveEntryAge(final LocalDate dob, int maxEntryAge) {
        return calculateAge(dob) <= maxEntryAge;
    }

    public static boolean isSpouseAgeAboveEntryAge(final LocalDate dob, int maxEntryAge) {
        if (calculateAge(dob) < maxEntryAge) {
            throw new BusinessValidationException("Spouse age at entry cannot be more than" + maxEntryAge + " years.");
        }
        return true;
    }

    public static boolean isParentAgeAboveEntryAge(final LocalDate dob, int maxEntryAge) {
        if (calculateAge(dob) < maxEntryAge) {
            throw new BusinessValidationException("Parent age at entry cannot be more than" + maxEntryAge + " years.");
        }
        return true;
    }

    public static boolean isPrincipalAbove18(final LocalDate dob, int maxEntryAge) {
        //throw new BusinessValidationException("Age of principal must be " + maxEntryAge + " years or above.");
        return calculateAge(dob) < maxEntryAge;
    }

    public static boolean isSpouseAbove18(final LocalDate dob, int maxEntryAge) {
        if (calculateAge(dob) < maxEntryAge) {
            throw new BusinessValidationException("Age of spouse must be " + maxEntryAge + " years or above.");
        }
        return true;
    }

    public static boolean isChildBelow18(final LocalDate dob, int maxEntryAge) {
        if (calculateAge(dob) < maxEntryAge) {
            throw new BusinessValidationException("Age of child must be " + maxEntryAge + " years or above.");
        }
        return true;
    }

    public static boolean isLegalGuardianAbove18(final LocalDate dob, int maxEntryAge) {
        return calculateAge(dob) < maxEntryAge;
    }

    public static boolean isSameSex(String principal, String spouse) {
        if (principal.equalsIgnoreCase(spouse)) {
            throw new BusinessValidationException("Principal and spouse cannot be of the same sex.");
        }
        return true;
    }

    public static boolean isSameProductOrLowerSpouse(BigDecimal principal, BigDecimal spouse) {
        int value = principal.compareTo(spouse);
        if (value == -1) {
            throw new BusinessValidationException("Spouse cannot have product above principal");
        }
        return true;
    }

    public static boolean isSameProductOrLowerOther(BigDecimal principal, BigDecimal other) {
        int value = principal.compareTo(other);
        if (value == -1) {
            throw new BusinessValidationException("Beneficiary cannot have product above principal");
        }
        return true;
    }

    public static boolean isHundredPercent(List<Long> percentages) {
        //To Do
        //Lambda it here
        /*if (value==-1) {
            throw new BusinessValidationException("Beneficiary cannot have product above principal");
        }*/
        return true;
    }

    public static boolean isOverLimit(BigDecimal amount, BigDecimal limit) {
        return true;
    }

}
