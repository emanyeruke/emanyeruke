package zw.co.mynhaka.policyservice.domain.dto.funeralplan;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.PersonType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class StandardFuneralPlanUpdateDTO {
    @NotNull
    private Long productId;

    @NotNull
    private Long funeralPlanId;

    @NotEmpty
    private String name;

    @DecimalMin("0.00")
    private BigDecimal sumAssured;

    @DecimalMin("0.00")
    private BigDecimal premium;

    private PersonType personType;

    private int clawbackPeriod;
}
