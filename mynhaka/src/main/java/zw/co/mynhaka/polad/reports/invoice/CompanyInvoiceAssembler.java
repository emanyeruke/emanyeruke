package zw.co.mynhaka.polad.reports.invoice;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceResultDTO;
import zw.co.mynhaka.polad.domain.model.Invoice;
import zw.co.mynhaka.polad.service.iface.InvoiceService;
import zw.co.mynhaka.polad.service.mapper.invoice.InvoiceItemToInvoiceItemResultDTO;
import zw.co.mynhaka.polad.service.mapper.invoice.InvoiceToInvoiceResultDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CompanyInvoiceAssembler {

    private final InvoiceItemToInvoiceItemResultDTO toInvoiceItemResultDTO;
    private final InvoiceToInvoiceResultDTO toInvoiceResultDTO;
    private final InvoiceService invoiceService;

    public CompanyInvoiceAssembler(InvoiceItemToInvoiceItemResultDTO toInvoiceItemResultDTO,
                                   InvoiceToInvoiceResultDTO toInvoiceResultDTO,
                                   InvoiceService invoiceService) {
        this.toInvoiceItemResultDTO = toInvoiceItemResultDTO;
        this.toInvoiceResultDTO = toInvoiceResultDTO;
        this.invoiceService = invoiceService;
    }


    public CompanyInvoiceReportDto assemble(Invoice invoice) {
        CompanyInvoiceReportDto reportDto = new CompanyInvoiceReportDto();
        List<InvoiceResultDTO> invoices = new ArrayList<>();
//        Invoice invoice = invoiceService.getOne(1L);

        InvoiceResultDTO invoiceResultDTO = toInvoiceResultDTO.convert(invoice);

        /*Set<InvoiceItemResultDTO> invoiceItems = invoice.getInvoiceItemSet()
                .stream()
                .map(toInvoiceItemResultDTO::convert)
                .collect(Collectors.toSet());
*/
        //invoiceResultDTO.setInvoiceDataSource();
        reportDto.setId(invoiceResultDTO.getId());
        reportDto.setCompanyName(invoiceResultDTO.getCompanyName());
        reportDto.setPolicyHolderName(invoiceResultDTO.getPolicyHolderName());
        reportDto.setClosingBalance(invoiceResultDTO.getClosingBalance());
        reportDto.setOpeningBalance(invoiceResultDTO.getOpeningBalance());
        reportDto.setCreatedDate(invoiceResultDTO.getInvoicingDate());
        reportDto.setDueDate(invoiceResultDTO.getDueDate());
        log.info("### Invoice items {}", invoiceResultDTO.getInvoiceItemSet());

        JRBeanCollectionDataSource companyDataSource = new JRBeanCollectionDataSource(invoiceResultDTO.getInvoiceItemSet(), false);
        reportDto.setInvoiceItemDataSource(companyDataSource);

        return reportDto;
    }
}
