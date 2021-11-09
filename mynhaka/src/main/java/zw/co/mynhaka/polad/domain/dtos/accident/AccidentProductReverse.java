package zw.co.mynhaka.polad.domain.dtos.accident;


import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class AccidentProductReverse {

    private Long accidentId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @DecimalMin("0.00")
    private double sumAssured;

    private PersonType personType;

    private int clawbackPeriod;
}
