package zw.co.mynhaka.polad.service.iface.reports.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeathClaimsPerProductResult {
    String month;
    BigDecimal savingsPlan;
    BigDecimal accidentalPlan;
    BigDecimal comprehensivePlan;
    BigDecimal funeralPlan;
}
