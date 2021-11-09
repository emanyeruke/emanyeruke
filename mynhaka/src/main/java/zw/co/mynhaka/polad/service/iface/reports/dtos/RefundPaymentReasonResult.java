package zw.co.mynhaka.polad.service.iface.reports.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RefundPaymentReasonResult {
    String month;
    BigDecimal overDeductions;
    BigDecimal deductionsOfMisrepresentation;
    BigDecimal deductionsOfCancellation;
    BigDecimal deductionsAfterSurrender;
    BigDecimal otherDeductions;
}
