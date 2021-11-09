package zw.co.mynhaka.polad.domain.dtos.invoice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import zw.co.mynhaka.polad.domain.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResultDTO {

    private Long id;

    private List<InvoiceItemResultDTO> invoiceItemSet = new ArrayList<>();

    private LocalDate invoicingDate;

    private LocalDate dueDate;

    private Long policyHolderId;

    private Long companyId;

    private String policyHolderName;

    private String companyName;

    private double openingBalance;

    private double closingBalance;

    private InvoiceStatus invoiceStatus;

    private String description;

    private double paymentReceived;

    private JRBeanCollectionDataSource invoiceDataSource;


}
