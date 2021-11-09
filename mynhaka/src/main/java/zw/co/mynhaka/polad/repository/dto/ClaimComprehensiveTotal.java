package zw.co.mynhaka.polad.repository.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClaimComprehensiveTotal {
    BigDecimal totalPaid;
    int month;
    int year;
}
