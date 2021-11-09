package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import lombok.ToString;
import zw.co.mynhaka.polad.domain.enums.ProductType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@ToString
public class ProductCreateDto {
    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    @DecimalMin("0.00")
    private BigDecimal adminFee;

    private ProductType productType;
}
