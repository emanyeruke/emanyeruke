package zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComprehensiveFuneralPlanResultDTO {
    private Long id;

    private String name;

    private BigDecimal sumAssured;

    private BigDecimal premium;

    private String personType;

    private String term;

    private Long productId;
}
