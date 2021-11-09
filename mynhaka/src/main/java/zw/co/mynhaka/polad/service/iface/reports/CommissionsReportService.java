package zw.co.mynhaka.polad.service.iface.reports;

import zw.co.mynhaka.polad.service.iface.reports.dtos.CommissionsPerProductResult;

import java.util.List;

public interface CommissionsReportService {
    List<CommissionsPerProductResult> getCommissionsPerProduct();
}
