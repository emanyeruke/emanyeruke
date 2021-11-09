package zw.co.mynhaka.policyservice.domain.dto.funeralplan;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StandardFuneralPlanResultDTO {
    private Long id;

    private String name;

    private BigDecimal sumAssured;

    private BigDecimal premium;

    private String personType;

    private Long productId;
}
