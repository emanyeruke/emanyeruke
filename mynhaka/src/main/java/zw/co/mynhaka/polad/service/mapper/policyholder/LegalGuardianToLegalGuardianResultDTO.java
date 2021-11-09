package zw.co.mynhaka.polad.service.mapper.policyholder;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianResultDTO;
import zw.co.mynhaka.polad.domain.model.LegalGuardian;


@Component
public class LegalGuardianToLegalGuardianResultDTO implements Converter<LegalGuardian, LegalGuardianResultDTO> {
    @Override
    public LegalGuardianResultDTO convert(LegalGuardian legalGuardian) {

        LegalGuardianResultDTO resultDTO = new LegalGuardianResultDTO();
        resultDTO.setContactNumber(legalGuardian.getContactNumber());
        resultDTO.setDateOfBirth(legalGuardian.getDateOfBirth());
        resultDTO.setEmail(legalGuardian.getEmail());
        resultDTO.setId(legalGuardian.getId());
        resultDTO.setName(legalGuardian.getName());
        resultDTO.setSurname(legalGuardian.getSurname());

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
