package zw.co.mynhaka.polad.domain.dtos.funeral;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FuneralProductResultDTO {
    private Long id;

    private String name;

    private double sumAssured;

    private double premium;

    private String personType;

    private Long funeralId;

    private int clawbackPeriod;
}
