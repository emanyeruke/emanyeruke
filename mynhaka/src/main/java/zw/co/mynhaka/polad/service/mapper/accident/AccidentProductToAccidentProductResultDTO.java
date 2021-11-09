package zw.co.mynhaka.polad.service.mapper.accident;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductResultDTO;
import zw.co.mynhaka.polad.domain.model.AccidentProduct;


@Component
public class AccidentProductToAccidentProductResultDTO implements Converter<AccidentProduct, AccidentProductResultDTO> {
    @Override
    public AccidentProductResultDTO convert(AccidentProduct accidentProduct) {

        AccidentProductResultDTO resultDTO = new AccidentProductResultDTO();
        resultDTO.setAccidentId(accidentProduct.getAccident().getId());
        resultDTO.setId(accidentProduct.getId());
        resultDTO.setName(accidentProduct.getName());
        resultDTO.setPersonType(accidentProduct.getPersonType().toString());
        resultDTO.setPremium(accidentProduct.getPremium());
        resultDTO.setSumAssured(accidentProduct.getSumAssured());

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
