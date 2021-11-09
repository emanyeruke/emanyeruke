package zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ComprehensiveFuneralResultDTO {

    private Long id;

    private String name;

    private Set<ComprehensiveFuneralProductResultDTO> comprehensiveFuneralProductResultDTOS = new HashSet<>();


}
