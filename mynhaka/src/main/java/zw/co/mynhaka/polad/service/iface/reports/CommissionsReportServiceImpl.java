package zw.co.mynhaka.polad.service.iface.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.repository.CommissionPaymentRepository;
import zw.co.mynhaka.polad.service.iface.reports.dtos.CommissionsPerProductResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommissionsReportServiceImpl implements CommissionsReportService {

    private final CommissionPaymentRepository commissionPaymentRepository;

    @Override
    public List<CommissionsPerProductResult> getCommissionsPerProduct() {
        return null;
    }
}
