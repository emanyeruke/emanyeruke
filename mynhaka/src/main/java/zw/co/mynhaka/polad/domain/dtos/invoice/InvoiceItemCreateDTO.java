package zw.co.mynhaka.polad.domain.dtos.invoice;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PolicyType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 */

@Data
public class InvoiceItemCreateDTO implements Serializable {

    private static final long serialVersionUID = -3835118568439185381L;

    @NotEmpty
    @Size(max = 64)
    private String principal;

    @Size(max = 64)
    private String beneficiary;

    @NotEmpty
    @Size(max = 32)
    private String product;

    private double price;

    private String policyNumber;

    private PolicyType policyType;

}
