package zw.co.mynhaka.polad.reports.invoice;

import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import zw.co.mynhaka.polad.domain.enums.InvoiceStatus;

import java.math.BigDecimal;

@Data
public class PolicyHolderInvoiceReportDto {

    private Long id;

    private String policyholder;

    private BigDecimal openingBalance;

    private BigDecimal closingBalance;

    private InvoiceStatus invoiceStatus;

    private JRBeanCollectionDataSource invoiceItemDataSource;

    public PolicyHolderInvoiceReportDto(Long id, String policyholder,
                                        BigDecimal openingBalance,
                                        BigDecimal closingBalance,
                                        InvoiceStatus invoiceStatus,
                                        JRBeanCollectionDataSource invoiceItemDataSource) {
        this.id = id;
        this.policyholder = policyholder;
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
        this.invoiceStatus = invoiceStatus;
        this.invoiceItemDataSource = invoiceItemDataSource;
    }
}
