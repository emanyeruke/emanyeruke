package zw.co.mynhaka.policyservice.domain.dto.accidentplan;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccidentPlanResultDTO {
    private Long id;

    private String name;

    private BigDecimal sumAssured;

    private BigDecimal premium;

    private String personType;

    private Long productId;
}
