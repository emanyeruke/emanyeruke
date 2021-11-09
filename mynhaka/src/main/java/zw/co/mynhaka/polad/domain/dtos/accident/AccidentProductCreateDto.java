package zw.co.mynhaka.polad.domain.dtos.accident;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class AccidentProductCreateDto {


    private Long accidentId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;


    @DecimalMin("0.00")
    private double sumAssured;


    @DecimalMin("0.00")
    private double premium;

    private PersonType personType;


}
