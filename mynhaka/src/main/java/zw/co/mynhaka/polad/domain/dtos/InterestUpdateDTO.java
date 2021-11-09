package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InterestUpdateDTO {
    private long id;
    private int month;
    private BigDecimal rate;
}
