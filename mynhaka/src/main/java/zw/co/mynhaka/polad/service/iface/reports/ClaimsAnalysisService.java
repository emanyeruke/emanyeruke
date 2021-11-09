package zw.co.mynhaka.polad.service.iface.reports;


import zw.co.mynhaka.polad.service.iface.reports.dtos.DeathClaimsPerProductResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.MonthlyReportResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.RefundPaymentReasonResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.WeeklyClaimsResult;

public interface ClaimsAnalysisService {
    DeathClaimsPerProductResult getDeathClaimsPerProduct();

    RefundPaymentReasonResult getRefundPaymentReason();

    MonthlyReportResult getMonthlyClaimsPaid();

    WeeklyClaimsResult getWeeklyClaimsResult();
}
