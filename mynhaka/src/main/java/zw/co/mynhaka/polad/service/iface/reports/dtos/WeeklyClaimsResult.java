package zw.co.mynhaka.polad.service.iface.reports.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WeeklyClaimsResult {
    String month;
    BigDecimal investmentClaims;
    BigDecimal insuranceClaims;
    BigDecimal refundPayment;
}
