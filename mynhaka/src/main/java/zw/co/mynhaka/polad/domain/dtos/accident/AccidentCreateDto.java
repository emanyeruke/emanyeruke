package zw.co.mynhaka.polad.domain.dtos.accident;


import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class AccidentCreateDto {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private double adminFee;
}
