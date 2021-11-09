package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeResultDTO;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeUpdateDto;
import zw.co.mynhaka.polad.domain.model.Employer;
import zw.co.mynhaka.polad.domain.model.Representative;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepresentativeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employer", source = "employer")
    @Mapping(target = "email", source = "representativeCreateDTO.email")
    @Mapping(target = "contactNumber", source = "representativeCreateDTO.contactNumber")
    Representative toRepresentative(RepresentativeCreateDTO representativeCreateDTO, Employer employer);

    @Mapping(target = "employer", source = "employer.name")
    @Mapping(target = "representativeId", source = "id")
    @Mapping(target = "employerId", source = "employer.id")
    RepresentativeResultDTO toRepresentativeResultDTO(Representative representative);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employer", source = "employer")
    @Mapping(target = "email", source = "representativeUpdateDto.email")
    @Mapping(target = "contactNumber", source = "representativeUpdateDto.contactNumber")
    void updateRepresentative(@MappingTarget Representative representative, Employer employer, RepresentativeUpdateDto representativeUpdateDto);
}
