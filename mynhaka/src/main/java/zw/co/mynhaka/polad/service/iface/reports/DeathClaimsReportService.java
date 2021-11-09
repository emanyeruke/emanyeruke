package zw.co.mynhaka.polad.service.iface.reports;


import zw.co.mynhaka.polad.service.iface.reports.dtos.DeathClaimsPerProductResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.MonthlyReportResult;

import java.util.List;

public interface DeathClaimsReportService {

    List<MonthlyReportResult> getMonthlyReport();

    DeathClaimsPerProductResult getDeathClaimsPerProduct();
}
