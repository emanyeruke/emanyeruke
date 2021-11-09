package zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.PersonType;
import zw.co.mynhaka.policyservice.domain.enums.Term;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ComprehensiveFuneralPlanCreateDTO {
    @NotNull
    private Long productId;

    @Size(min = 5, max = 50)
    private String name;

    @DecimalMin("0.00")
    private BigDecimal sumAssured;

    @DecimalMin("0.00")
    private BigDecimal premium;

    private Term term;

    private PersonType personType;

    private int clawbackPeriod;
}
