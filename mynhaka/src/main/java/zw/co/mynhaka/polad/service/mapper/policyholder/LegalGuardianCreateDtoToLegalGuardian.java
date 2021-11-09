package zw.co.mynhaka.polad.service.mapper.policyholder;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianResultDTO;
import zw.co.mynhaka.polad.domain.model.LegalGuardian;


@Component
public class LegalGuardianCreateDtoToLegalGuardian implements Converter<LegalGuardianResultDTO, LegalGuardian> {
    @Override
    public LegalGuardian convert(LegalGuardianResultDTO legalGuardianResultDTO) {
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
