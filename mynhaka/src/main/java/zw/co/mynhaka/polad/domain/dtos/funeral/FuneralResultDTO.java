package zw.co.mynhaka.polad.domain.dtos.funeral;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class FuneralResultDTO {


    private Long id;


    private String name;


    private Set<FuneralProductResultDTO> funeralProductResultDTO = new HashSet<>();

    private double adminFee;
}
