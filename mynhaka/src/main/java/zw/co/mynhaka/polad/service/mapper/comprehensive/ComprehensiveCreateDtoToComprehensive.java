package zw.co.mynhaka.polad.service.mapper.comprehensive;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveCreateDto;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneral;


@Component
public class ComprehensiveCreateDtoToComprehensive implements Converter<ComprehensiveCreateDto, ComprehensiveFuneral> {
    @Override
    public ComprehensiveFuneral convert(ComprehensiveCreateDto comprehensiveCreateDto) {
        ComprehensiveFuneral comprehensiveFuneral = new ComprehensiveFuneral();
        comprehensiveFuneral.setName(comprehensiveCreateDto.getName());
        return comprehensiveFuneral;
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
