package zw.co.mynhaka.polad.domain.dtos.savings;


import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class SavingsProductReverseCreateDto {

    private Long savingsId;

    @Size(min = 5, max = 50)
    private String name;

    @DecimalMin("0.00")
    private BigDecimal monthlyInvestmentPremium;

    private PersonType personType;

    private int clawbackPeriod;
}
