package zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ComprehensiveCreateDto {
    @Size(min = 5, max = 50)
    private String name;

    @DecimalMin("0.00")
    private double adminFee;
}
