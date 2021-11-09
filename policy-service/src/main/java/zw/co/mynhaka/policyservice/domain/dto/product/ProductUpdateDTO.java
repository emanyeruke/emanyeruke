package zw.co.mynhaka.policyservice.domain.dto.product;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.ProductType;

import java.math.BigDecimal;

@Data
public class ProductUpdateDTO {
    private Long id;

    private String name;

    private BigDecimal adminFee;

    private ProductType productType;
}
