package zw.co.mynhaka.policyservice.domain.dto.product;

import lombok.Data;
import zw.co.mynhaka.policyservice.domain.enums.ProductType;

import java.math.BigDecimal;

@Data
public class ProductResultDTO {
    private Long id;

    private String name;

    private ProductType productType;

    private BigDecimal adminFee;
}
