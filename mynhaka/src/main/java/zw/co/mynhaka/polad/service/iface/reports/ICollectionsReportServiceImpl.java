package zw.co.mynhaka.polad.service.iface.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.reports.claims.IInvoiveTotal;
import zw.co.mynhaka.polad.reports.claims.IPaymentTotal;
import zw.co.mynhaka.polad.repository.InvoiceRepository;
import zw.co.mynhaka.polad.repository.PaymentRepository;
import zw.co.mynhaka.polad.service.iface.reports.dtos.CollectionResultDto;

import java.time.LocalDate;


@Service
@Slf4j
public class ICollectionsReportServiceImpl implements ICollectionsReportService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    public ICollectionsReportServiceImpl(final PaymentRepository paymentRepository,
                                         final InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public CollectionResultDto getCollections() {
        CollectionResultDto collectionResultDto = new CollectionResultDto();
        log.info("Month, Year {}, {} ", LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        IInvoiveTotal iInvoiveTotal = invoiceRepository.findInvoiceTotalForCurrentMonth(9, LocalDate.now().getYear());
        IPaymentTotal iPaymentTotal = paymentRepository.findAmountPaidThisWeek();
        collectionResultDto.setMonthlyInvoiceTotal(iInvoiveTotal.getInvoiceTotal());
        collectionResultDto.setTotalCollectedThisWeek(iPaymentTotal.getPayment());
        collectionResultDto.setYetToBeCollected(iInvoiveTotal.getInvoiceTotal().subtract(iPaymentTotal.getPayment()));
        log.info("Result {} ", collectionResultDto);
        return collectionResultDto;
    }
}
