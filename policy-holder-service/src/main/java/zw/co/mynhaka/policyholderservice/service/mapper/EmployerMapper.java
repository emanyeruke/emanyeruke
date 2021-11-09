package zw.co.mynhaka.policyholderservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerCreateDT0;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Employer;
import zw.co.mynhaka.policyholderservice.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployerMapper {

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    Employer toEmployer(EmployerCreateDT0 employerCreateDT0);

    EmployerResultDTO toEmployerResultDTO(Employer employer);

    void updateEmployer(@MappingTarget Employer employer, EmployerUpdateDTO employerUpdateDTO);
}
