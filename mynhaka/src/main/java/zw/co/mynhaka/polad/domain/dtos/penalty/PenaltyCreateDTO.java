package zw.co.mynhaka.polad.domain.dtos.penalty;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PenaltyCreateDTO {
    @NotNull
    private int month;

    @DecimalMin("0.00")
    private BigDecimal rate;
}
