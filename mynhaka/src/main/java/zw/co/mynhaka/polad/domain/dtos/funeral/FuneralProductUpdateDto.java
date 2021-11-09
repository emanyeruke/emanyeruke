package zw.co.mynhaka.polad.domain.dtos.funeral;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PersonType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class FuneralProductUpdateDto {
    @NotNull
    private Long funeralId;

    @NotNull
    private Long funeralProductId;

    @NotEmpty
    private String name;

    @DecimalMin("0.00")
    private double sumAssured;

    private PersonType personType;

    private int clawbackPeriod;
}
