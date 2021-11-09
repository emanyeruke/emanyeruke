package zw.co.mynhaka.polad.domain.dtos.invoice;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class InvoiceCreateDTO {

    private Long employerId;

    private Long policyHolderId;

    private Long companyId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate invoicingDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dueDate;

    private List<InvoiceItemCreateDTO> invoiceItem = new ArrayList<>();

    private double openingBalance;

    private double closingBalance;


    public InvoiceCreateDTO(Long employerId, Long policyHolderId, double openingBalance, double closingBalance) {
        this.employerId = employerId;
        this.policyHolderId = policyHolderId;
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
    }
}
