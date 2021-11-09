package zw.co.mynhaka.polad.service.mapper.representative;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeResultDTO;
import zw.co.mynhaka.polad.domain.model.Representative;


@Component
public class RepresentativeToRepresentativeResultDTO implements Converter<Representative, RepresentativeResultDTO> {

    @Override
    public RepresentativeResultDTO convert(Representative representative) {

        RepresentativeResultDTO representativeResultDTO = new RepresentativeResultDTO();
        representativeResultDTO.setCategory(representative.getCategory().toString());
        representativeResultDTO.setContactNumber(representative.getContactNumber());
        representativeResultDTO.setEmail(representative.getEmail());
        representativeResultDTO.setEmployer(representative.getEmployer().getName());
        representativeResultDTO.setFirstName(representative.getFirstName());
        representativeResultDTO.setLastName(representative.getLastName());
        representativeResultDTO.setRepresentativeId(representative.getId());
        return representativeResultDTO;
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
