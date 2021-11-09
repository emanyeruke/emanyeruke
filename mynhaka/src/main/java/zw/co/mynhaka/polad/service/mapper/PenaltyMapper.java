package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyResultDTO;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Penalty;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PenaltyMapper {

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    Penalty toPenalty(PenaltyCreateDTO penaltyCreateDTO);

    PenaltyResultDTO toPenaltyResultDTO(Penalty penalty);

    void updatePenalty(@MappingTarget Penalty penalty, PenaltyUpdateDTO penaltyUpdateDTO);
}
