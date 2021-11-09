package zw.co.mynhaka.polad.service.mapper.policyholder;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderCreateDto;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;


@Component
public class PolicyHolderCreateDtoToPolicyHolder implements Converter<PolicyHolderCreateDto, PolicyHolder> {
    @Override
    public PolicyHolder convert(PolicyHolderCreateDto policyHolderCreateDto) {
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
