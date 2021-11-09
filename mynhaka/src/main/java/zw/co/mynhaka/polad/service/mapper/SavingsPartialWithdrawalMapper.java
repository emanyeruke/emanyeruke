package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalResultDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsPartialWithdrawalUpdateDTO;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.domain.model.SavingsPartialWithdrawal;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavingsPartialWithdrawalMapper {
    @IgnoreAuditing
    @Mapping(target = "policyNumber", source = "savingsPartialWithdrawalCreateDTO.policyNumber")
    SavingsPartialWithdrawal toSavingsPartialWithdrawal(SavingsPartialWithdrawalCreateDTO savingsPartialWithdrawalCreateDTO, PolicySavings policySavings);

    SavingsPartialWithdrawalResultDTO toSavingsPartialWithdrawalResultDTO(SavingsPartialWithdrawal savingsPartialWithdrawal);

    void updateSavingsPartialWithdrawal(@MappingTarget SavingsPartialWithdrawal savingsPartialWithdrawal, SavingsPartialWithdrawalUpdateDTO savingsPartialWithdrawalUpdateDTO);
}
