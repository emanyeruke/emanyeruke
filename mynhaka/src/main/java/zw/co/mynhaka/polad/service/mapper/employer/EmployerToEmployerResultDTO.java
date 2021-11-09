package zw.co.mynhaka.polad.service.mapper.employer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerResultDTO;
import zw.co.mynhaka.polad.domain.model.Employer;


@Component
public class EmployerToEmployerResultDTO implements Converter<Employer, EmployerResultDTO> {

    @Override
    public EmployerResultDTO convert(Employer employer) {
        EmployerResultDTO employerResultDTO = new EmployerResultDTO();
        employerResultDTO.setId(employer.getId());
        employerResultDTO.setBalance(employer.getBalance());
        employerResultDTO.setContactNumber(employer.getContactNumber());
        employerResultDTO.setEmail(employer.getEmail());
        employerResultDTO.setName(employer.getName());
        /*employerResultDTO.setRepresentative(employer.getRepresentative());*/
        return employerResultDTO;
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
