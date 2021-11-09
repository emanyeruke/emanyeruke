package zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprehensiveFuneralProductResultDTO {

    private Long id;

    private String name;

    private double sumAssured;

    private double premium;

    private String personType;

    private String term;

    private Long comprehensiveFuneralId;

    private int clawbackPeriod;
}
