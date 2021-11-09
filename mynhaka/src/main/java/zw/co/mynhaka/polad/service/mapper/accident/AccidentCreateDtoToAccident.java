package zw.co.mynhaka.polad.service.mapper.accident;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentCreateDto;
import zw.co.mynhaka.polad.domain.model.Accident;


@Component
public class AccidentCreateDtoToAccident implements Converter<AccidentCreateDto, Accident> {
    @Override
    public Accident convert(AccidentCreateDto accidentCreateDto) {
        Accident accident = new Accident();
        accident.setName(accidentCreateDto.getName());
        accident.setAdminFee(accidentCreateDto.getAdminFee());
        return accident;
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
