package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderResultDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderUpdateDTO;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.domain.model.SavingsSurrender;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavingsSurrenderMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    SavingsSurrender toSavingsSurrender(SavingsSurrenderCreateDTO savingsSurrenderCreateDTO, PolicySavings policySavings);

    @Mapping(target = "savingsSurrenderId", source = "savingsSurrender.id")
    @Mapping(target = "policyNumber", source = "savingsSurrender.policySavings.policyNumber")
    SavingsSurrenderResultDTO toSavingsSurrenderResultDTO(SavingsSurrender savingsSurrender);

    @IgnoreAuditing
    void updateSavingsSurrender(@MappingTarget SavingsSurrender savingsSurrender, SavingsSurrenderUpdateDTO savingsSurrenderUpdateDTO);
}
