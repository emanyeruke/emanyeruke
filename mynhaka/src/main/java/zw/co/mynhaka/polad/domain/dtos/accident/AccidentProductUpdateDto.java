package zw.co.mynhaka.polad.domain.dtos.accident;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AccidentProductUpdateDto {
    @NotNull
    private Long accidentId;

    @NotNull
    private Long accidentProductId;

    private String name;

    @DecimalMin("0.00")
    private double sumAssured;

    private PersonType personType;


}
