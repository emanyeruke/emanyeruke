package zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral;


import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PersonType;
import zw.co.mynhaka.polad.domain.enums.Term;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ComprehensiveFuneralProductReverseCreateDto {

    private Long comprehensiveId;

    @Size(min = 5, max = 50)
    private String name;


    @DecimalMin("0.00")
    private double sumAssured;

    private Term term;

    private PersonType personType;

    private int clawbackPeriod;
}
