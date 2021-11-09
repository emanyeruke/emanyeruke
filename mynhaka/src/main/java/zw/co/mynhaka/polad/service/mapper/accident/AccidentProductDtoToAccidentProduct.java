package zw.co.mynhaka.polad.service.mapper.accident;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductCreateDto;
import zw.co.mynhaka.polad.domain.model.AccidentProduct;


@Component
public class AccidentProductDtoToAccidentProduct implements Converter<AccidentProductCreateDto, AccidentProduct> {
    @Override
    public AccidentProduct convert(AccidentProductCreateDto accidentProductCreateDto) {
        AccidentProduct accidentProduct = new AccidentProduct();
        accidentProduct.setName(accidentProductCreateDto.getName());
        accidentProduct.setPersonType(accidentProductCreateDto.getPersonType());
        accidentProduct.setPremium(accidentProductCreateDto.getPremium());
        accidentProduct.setSumAssured(accidentProductCreateDto.getSumAssured());
        return accidentProduct;
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
