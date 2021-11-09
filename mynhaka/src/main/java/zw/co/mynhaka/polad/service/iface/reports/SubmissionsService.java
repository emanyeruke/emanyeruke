package zw.co.mynhaka.polad.service.iface.reports;


import zw.co.mynhaka.polad.service.iface.reports.dtos.TotalSubmissionsResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.WeeklySubmissionsResult;

public interface SubmissionsService {
    WeeklySubmissionsResult getSurrenderSettlementTime();

    TotalSubmissionsResult getPartialSettlementTime();
}
