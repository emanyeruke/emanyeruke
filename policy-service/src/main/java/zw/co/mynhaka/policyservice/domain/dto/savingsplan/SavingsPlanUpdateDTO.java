package zw.co.mynhaka.policyservice.domain.dto.savingsplan;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.PersonType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class SavingsPlanUpdateDTO {
    @NotNull
    private Long productId;

    @NotNull
    private Long savingsPlanId;

    @Size(min = 5, max = 50)
    private String name;

    @DecimalMin("0.00")
    private BigDecimal monthlyInvestmentPremium;

    @DecimalMin("0.00")
    private BigDecimal premiumWaiver;

    private PersonType personType;
}
