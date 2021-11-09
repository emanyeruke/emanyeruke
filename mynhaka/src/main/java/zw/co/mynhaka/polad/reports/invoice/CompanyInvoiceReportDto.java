package zw.co.mynhaka.polad.reports.invoice;

import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import zw.co.mynhaka.polad.domain.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CompanyInvoiceReportDto {

    private Long id;

    private LocalDate createdDate;

    private LocalDate dueDate;

    private String companyName;

    private String policyHolderName;

    private double openingBalance;

    private double closingBalance;

    private InvoiceStatus invoiceStatus;

    private double paymentReceived;

    private LocalDate coverStartDate;

    private double administrationFeeAmount;

    private double administrationFeePercent;

    private JRBeanCollectionDataSource invoiceItemDataSource;

    public CompanyInvoiceReportDto() {

    }

    public CompanyInvoiceReportDto(Long id, LocalDate createdDate, LocalDate dueDate, String companyName, String policyHolderName, double openingBalance, double closingBalance, InvoiceStatus invoiceStatus, JRBeanCollectionDataSource invoiceItemDataSource) {
        this.id = id;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.companyName = companyName;
        this.policyHolderName = policyHolderName;
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
        this.invoiceStatus = invoiceStatus;
        this.invoiceItemDataSource = invoiceItemDataSource;
    }
}
