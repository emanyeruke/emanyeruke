package zw.co.mynhaka.polad.reports.claims;

public interface IAccidentClaimMonthlyReport {

    int getMonth();

    int getYear();

    Long getFirstTier();

    Long getSecondTier();

    Long getThirdTier();

    Long getFourthTier();

}
