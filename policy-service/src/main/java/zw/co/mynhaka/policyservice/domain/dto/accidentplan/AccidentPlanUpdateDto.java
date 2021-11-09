package zw.co.mynhaka.policyservice.domain.dto.accidentplan;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.PersonType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AccidentPlanUpdateDto {
    @NotNull
    private Long productId;

    @NotNull
    private Long accidentPlanId;

    private String name;

    @DecimalMin("0.00")
    private BigDecimal sumAssured;

    @DecimalMin("0.00")
    private BigDecimal premium;

    private PersonType personType;

    private int clawbackPeriod;
}
