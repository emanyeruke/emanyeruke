package zw.co.mynhaka.polad.service.iface.reports.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WeeklySubmittedResult {
    String year;
    BigDecimal amount;
    BigDecimal average;
}
