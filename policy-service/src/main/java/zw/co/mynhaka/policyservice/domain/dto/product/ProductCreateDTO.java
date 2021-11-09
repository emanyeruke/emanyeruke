package zw.co.mynhaka.policyservice.domain.dto.product;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.ProductType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductCreateDTO {
    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    @DecimalMin("0.00")
    private BigDecimal adminFee;

    private ProductType productType;
}
