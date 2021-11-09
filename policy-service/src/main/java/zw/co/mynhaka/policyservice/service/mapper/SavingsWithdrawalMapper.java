package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal.SavingsPartialWithdrawalCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspartialwithdrawal.SavingsPartialWithdrawalResultDTO;
import zw.co.mynhaka.policyservice.domain.model.SavingsPartialWithdrawal;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;
import zw.co.mynhaka.policyservice.service.utils.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavingsWithdrawalMapper {

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    SavingsPartialWithdrawal toSavingsPartialWithdrawal(SavingsPartialWithdrawalCreateDTO savingsPartialWithdrawalCreateDTO, SavingsPolicy savingsPolicy);

    @Mapping(target = "savingsPolicyId", source = "savingsPolicy.id")
    SavingsPartialWithdrawalResultDTO toSavingsPartialWithdrawalResultDTO(SavingsPartialWithdrawal savingsPartialWithdrawal);
}
