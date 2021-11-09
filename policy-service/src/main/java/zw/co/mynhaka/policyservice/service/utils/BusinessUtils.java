package zw.co.mynhaka.policyservice.service.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class BusinessUtils {
    public static String generatePolicyNumber() {
        //TODO Implement logic to generate a policyNumber
        return RandomStringUtils.randomNumeric(6).concat("P").concat("01");
    }
}
