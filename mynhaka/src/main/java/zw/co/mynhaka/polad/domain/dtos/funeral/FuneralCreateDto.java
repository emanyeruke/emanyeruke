package zw.co.mynhaka.polad.domain.dtos.funeral;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
public class FuneralCreateDto {

    @Size(min = 5, max = 50)
    private String name;


    @DecimalMin("0.00")
    private BigDecimal adminFee;

    List<@Valid FuneralProductCreateDto> funeralProductCreateDto;
}
