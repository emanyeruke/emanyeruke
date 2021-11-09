package zw.co.mynhaka.policyholderservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Representative;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepresentativeMapper {
    @Mapping(target = "id", ignore = true)
    Representative  toRepresentative(RepresentativeCreateDTO representativeCreateDTO);

    @Mapping(target = "representativeId", source = "representative.id")
    @Mapping(target = "employer", source = "representative.employer.name")
    RepresentativeResultDTO toRepresentativeResultDTO(Representative representative);

    void updateRepresentative(@MappingTarget Representative representative, RepresentativeUpdateDTO representativeUpdateDto);
}
