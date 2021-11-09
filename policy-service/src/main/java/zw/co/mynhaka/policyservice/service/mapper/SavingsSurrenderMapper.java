package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyservice.domain.dto.savingssurrender.SavingsSurrenderCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingssurrender.SavingsSurrenderResultDTO;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;
import zw.co.mynhaka.policyservice.domain.model.SavingsSurrender;
import zw.co.mynhaka.policyservice.service.utils.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavingsSurrenderMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    SavingsSurrender toSavingsSurrender(SavingsSurrenderCreateDTO savingsSurrenderCreateDTO, SavingsPolicy savingsPolicy);

    @Mapping(target = "savingsSurrenderId", source = "savingsSurrender.id")
    @Mapping(target = "surrenderValue", source = "savingsSurrender.savingsPolicy.balance")
    @Mapping(target = "policyNumber", source = "savingsSurrender.savingsPolicy.policyNumber")
    SavingsSurrenderResultDTO toSavingsSurrenderResultDTO(SavingsSurrender savingsSurrender);
}
