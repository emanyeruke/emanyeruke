package zw.co.mynhaka.policyservice.domain.dto.accidentplan;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.PersonType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class AccidentPlanCreateReverse {
    @NotNull
    private Long productId;

    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    @DecimalMin("0.00")
    private BigDecimal sumAssured;

    private PersonType personType;

    private int clawbackPeriod;
}
