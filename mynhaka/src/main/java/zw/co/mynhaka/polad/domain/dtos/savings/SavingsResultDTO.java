package zw.co.mynhaka.polad.domain.dtos.savings;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class SavingsResultDTO {

    private Long id;

    private String name;

    private Set<SavingsProductResultDTO> savingsProductResultDTO = new HashSet<>();

    private BigDecimal adminFee;
}
