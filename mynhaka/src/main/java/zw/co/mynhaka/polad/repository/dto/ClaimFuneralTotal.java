package zw.co.mynhaka.polad.repository.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClaimFuneralTotal {
    int year;
    int month;
    BigDecimal totalPaid;
}
