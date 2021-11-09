package zw.co.mynhaka.polad.service.util;


import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

public final class Util {

    private Util() {
    }

    public static long calculateAge(final LocalDate dob) {
        final long years = ChronoUnit.YEARS.between(dob, LocalDate.now());
        return years;
    }

   /* public static String formatRegNumber(final String regNumber) {
        return StringUtils.deleteWhitespace(regNumber).toUpperCase(Locale.ENGLISH);
    }

    public static String formatMobile(final String mobile) {
        if (!StringUtils.isEmpty(mobile)) {
            return "263".concat(mobile.substring(mobile.length() - 9));
        } else {
            return "";
        }
    }*/

    public static String maskEmailAddress(String strEmail, char maskChar)
            throws Exception {

        final String[] parts = strEmail.split("@");

        //mask first part
        String strId = "";
        if (parts[0].length() < 4)
            strId = maskString(parts[0], 0, parts[0].length(), '*');
        else
            strId = maskString(parts[0], 1, parts[0].length() - 1, '*');

        //now append the domain part to the masked id part
        return strId + "@" + parts[1];
    }

    private static String maskString(String strText, int start, int end, char maskChar)
            throws Exception {

        if (strText == null || strText.equals(""))
            return "";

        if (start < 0)
            start = 0;

        if (end > strText.length())
            end = strText.length();

        if (start > end)
            throw new Exception("End index cannot be greater than start index");

        int maskLength = end - start;

        if (maskLength == 0)
            return strText;

        final StringBuilder sbMaskString = new StringBuilder(maskLength);

        for (int i = 0; i < maskLength; i++) {
            sbMaskString.append(maskChar);
        }

        return strText.substring(0, start)
                + sbMaskString.toString()
                + strText.substring(start + maskLength);
    }

    public static void verify(PolicyHolder policyHolder, Predicate<PolicyHolder> predicate) {
        if (!predicate.test(policyHolder)) {
            System.out.println("Please add a legal guardian");
        }
    }
}