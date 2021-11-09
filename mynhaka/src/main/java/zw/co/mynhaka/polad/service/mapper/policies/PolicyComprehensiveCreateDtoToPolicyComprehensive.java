package zw.co.mynhaka.polad.service.mapper.policies;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveCreateDto;
import zw.co.mynhaka.polad.domain.model.PolicyComprehensive;


@Component
public class PolicyComprehensiveCreateDtoToPolicyComprehensive implements Converter<PolicyComprehensiveCreateDto, PolicyComprehensive> {
    @Override
    public PolicyComprehensive convert(PolicyComprehensiveCreateDto policyComprehensiveCreateDto) {
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
