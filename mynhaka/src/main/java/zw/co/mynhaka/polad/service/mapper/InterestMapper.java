package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.InterestCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.InterestResultDTO;
import zw.co.mynhaka.polad.domain.dtos.InterestUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Interest;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InterestMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    Interest toInterest(InterestCreateDTO interestCreateDTO);

    InterestResultDTO toInterestResultDTO(Interest interest);

    void updateInterest(@MappingTarget Interest interest, InterestUpdateDTO interestUpdateDTO);
}