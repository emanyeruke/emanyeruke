package zw.co.mynhaka.polad.service.mapper.accident;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentResultDTO;
import zw.co.mynhaka.polad.domain.model.Accident;

import java.util.stream.Collectors;

@Component
public class AccidentToAccidentResultDTO implements Converter<Accident, AccidentResultDTO> {

    private final AccidentProductToAccidentProductResultDTO toAccidentProductResultDTO;

    public AccidentToAccidentResultDTO(final AccidentProductToAccidentProductResultDTO toAccidentProductResultDTO) {
        this.toAccidentProductResultDTO = toAccidentProductResultDTO;
    }

    @Override
    public AccidentResultDTO convert(Accident accident) {

        AccidentResultDTO resultDTO = new AccidentResultDTO();
        resultDTO.setId(accident.getId());
        resultDTO.setName(accident.getName());
        resultDTO.setCreatedDate(accident.getCreatedDate());

        if (accident.getAdminFee() != 0.0) {
            resultDTO.setAdminFee(accident.getAdminFee());
        }

        if (!accident.getAccidentProduct().isEmpty()) {
            resultDTO.setAccidentProductResultDTOS(
                    accident.getAccidentProduct()
                            .stream()
                            .map(toAccidentProductResultDTO::convert)
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
