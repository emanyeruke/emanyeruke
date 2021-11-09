package zw.co.mynhaka.polad.service.mapper.employer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerCreateDto;
import zw.co.mynhaka.polad.domain.model.Employer;


@Component
public class EmployerCreateDtoToEmployer implements Converter<EmployerCreateDto, Employer> {

    @Override
    public Employer convert(EmployerCreateDto employerCreateDto) {
        return null;
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
