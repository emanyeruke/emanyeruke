package zw.co.mynhaka.polad.service.util;

import org.apache.commons.lang3.RandomStringUtils;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;

import java.time.LocalDate;

import static zw.co.mynhaka.polad.service.util.Util.calculateAge;


public class OtherUtils {

    private OtherUtils() {
    }


    public static String generatePolicyNumberForPrincipal() {
        //TODO Implement logic to generate a policyNumber
        return RandomStringUtils.randomNumeric(6).concat("P").concat("01");
    }

    public static boolean isPrincipalMemberOverEighteen(final LocalDate dob) {
        if (calculateAge(dob) < 18) {
            throw new BusinessValidationException("Life assured needs legal advisor cannot be less than 18 yrs");
        }
        return true;
    }

    public static boolean isSpouseOverEighteen(final LocalDate localDate) {
        if (calculateAge(localDate) < 18) {
            throw new BusinessValidationException("Spouse cannot be less than 18 yrs");
        }
        return true;
    }

    public static boolean isSpouseOppositeSex(final Gender memberGender, Gender spouseGender) {
        if (memberGender != spouseGender) {
            throw new BusinessValidationException("Spouse cannot be of the same sex");
        }
        return true;
    }

    public static boolean isStudentChild(final LocalDate localDate) {
        final long age = calculateAge(localDate);
        if (age < 18 || age > 25) {
            throw new BusinessValidationException("Student child must be between 18 and 25 yrs");
        }
        return true;
    }

    public static boolean isChild(final LocalDate dob) {
        if (calculateAge(dob) > 18) {
            throw new BusinessValidationException("Child must be less than 18 yrs");
        }
        return true;
    }

    public static boolean isOneHundredPercent() {
        return true;
    }
}
