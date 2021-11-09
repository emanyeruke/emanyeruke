package zw.co.mynhaka.polad.domain.dtos.accident;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentResultDTO {

    private Long id;

    private String name;

    private Set<AccidentProductResultDTO> accidentProductResultDTOS = new HashSet<>();

    private double adminFee;

    private Instant createdDate;
}
