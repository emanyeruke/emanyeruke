package zw.co.mynhaka.polad.reports.claims;

import java.util.Date;

public interface IAccidentClaimReport {

    Date getClaimDate();

    int getMonth();

    int getYear();

    Long getFirstTier();

    Long getSecondTier();

    Long getThirdTier();

    Long getFourthTier();
}
