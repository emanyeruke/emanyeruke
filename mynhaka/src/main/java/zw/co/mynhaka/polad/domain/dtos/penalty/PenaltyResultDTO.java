package zw.co.mynhaka.polad.domain.dtos.penalty;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PenaltyResultDTO {
    private long id;
    private int month;
    private BigDecimal rate;
}
