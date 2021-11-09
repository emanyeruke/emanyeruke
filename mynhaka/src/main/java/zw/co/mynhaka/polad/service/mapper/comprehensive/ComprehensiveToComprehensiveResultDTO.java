package zw.co.mynhaka.polad.service.mapper.comprehensive;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneral;

import java.util.stream.Collectors;


@Component
public class ComprehensiveToComprehensiveResultDTO implements Converter<ComprehensiveFuneral, ComprehensiveFuneralResultDTO> {
    private final ComprehensiveProductToComprehensiveProductResultDTO toComprehensiveProductResultDTO;

    public ComprehensiveToComprehensiveResultDTO(final ComprehensiveProductToComprehensiveProductResultDTO toComprehensiveProductResultDTO) {
        this.toComprehensiveProductResultDTO = toComprehensiveProductResultDTO;
    }

    @Override
    public ComprehensiveFuneralResultDTO convert(ComprehensiveFuneral comprehensiveFuneral) {
        ComprehensiveFuneralResultDTO resultDTO = new ComprehensiveFuneralResultDTO();
        resultDTO.setId(comprehensiveFuneral.getId());
        resultDTO.setName(comprehensiveFuneral.getName());
        /*
        if (!accident.getAccidentProduct().isEmpty()){
            resultDTO.setAccidentProductResultDTOS(
                    accident.getAccidentProduct()
                    .stream()
                    .map(toAccidentProductResultDTO::convert)
                    .collect(Collectors.toSet())
            );
        }
         */
        if (!comprehensiveFuneral.getComprehensiveFuneralProduct().isEmpty()) {
            resultDTO.setComprehensiveFuneralProductResultDTOS(
                    comprehensiveFuneral.getComprehensiveFuneralProduct()
                            .stream()
                            .map(toComprehensiveProductResultDTO::convert)
                            .collect(Collectors.toSet())
            );
        }
        return resultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
