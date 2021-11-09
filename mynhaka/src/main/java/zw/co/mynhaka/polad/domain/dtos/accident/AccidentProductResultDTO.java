package zw.co.mynhaka.polad.domain.dtos.accident;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentProductResultDTO {
    private Long id;

    private String name;

    private double sumAssured;

    private double premium;

    private String personType;

    private Long accidentId;

    private int clawbackPeriod;
}
