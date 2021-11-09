package zw.co.mynhaka.polad.service.mapper.policies;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentCreateDto;
import zw.co.mynhaka.polad.domain.model.PolicyAccident;


@Component
public class PolicyAccidentCreateDtoToPolicyAccident implements Converter<PolicyAccidentCreateDto, PolicyAccident> {
    @Override
    public PolicyAccident convert(PolicyAccidentCreateDto policyAccidentCreateDto) {
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
