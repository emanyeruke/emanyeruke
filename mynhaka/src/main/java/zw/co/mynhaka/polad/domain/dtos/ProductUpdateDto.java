package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.ProductType;

import java.math.BigDecimal;

@Data
public class ProductUpdateDto {
    private Long id;

    private String name;

    private BigDecimal adminFee;

    private ProductType productType;
}
