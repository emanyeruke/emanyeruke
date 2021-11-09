package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.ProductType;

import java.math.BigDecimal;

@Data
public class ProductResultDto {
    private Long id;
    private String name;
    private ProductType productType;
    private BigDecimal adminFee;
}
