package zw.co.mynhaka.polad.service.mapper.representative;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeCreateDTO;
import zw.co.mynhaka.polad.domain.model.Employer;
import zw.co.mynhaka.polad.domain.model.Representative;
import zw.co.mynhaka.polad.service.iface.EmployerService;


@Component
public class RepresentativeCreateDtoToRepresentative implements Converter<RepresentativeCreateDTO, Representative> {

    private final EmployerService employerService;

    public RepresentativeCreateDtoToRepresentative(final EmployerService employerService) {
        this.employerService = employerService;
    }

    @Override
    public Representative convert(RepresentativeCreateDTO representativeCreateDTO) {
        Representative representative = new Representative();
        representative.setCategory(representativeCreateDTO.getCategory());
        representative.setContactNumber(representativeCreateDTO.getContactNumber());
        representative.setEmail(representativeCreateDTO.getEmail());
        representative.setFirstName(representativeCreateDTO.getFirstName());
        representative.setLastName(representativeCreateDTO.getLastName());
        Employer employer = employerService.getOne(representativeCreateDTO.getEmployerId());
        representative.setEmployer(employer);
        return representative;
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
