package zw.co.mynhaka.polad.service.iface.reports.dtos;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlyReportResult {

    String month;
    BigDecimal refundPayments;
    BigDecimal investmentClaims;
    BigDecimal insuranceClaims;

}
