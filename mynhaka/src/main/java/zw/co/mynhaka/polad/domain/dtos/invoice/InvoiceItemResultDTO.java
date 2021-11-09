package zw.co.mynhaka.polad.domain.dtos.invoice;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class InvoiceItemResultDTO {

    private Long id;

    private String principal;

    private String beneficiary;

    private String product;

    private double price;

    private String firstName;

    private String lastName;

    private Long dependentsNumber;

    private double principalCover;

    private double totalPremium;

    private String policyNumber;

    private String policyType;

    public InvoiceItemResultDTO(Long id, String principal, String beneficiary, String product, double price) {
        this.id = id;
        this.principal = principal;
        this.beneficiary = beneficiary;
        this.product = product;
        this.price = price;
    }
}
