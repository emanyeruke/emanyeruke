package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyservice.domain.dto.payment.PartialWithdrawalPaymentCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.payment.WithdrawalPaymentCreateDTO;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    @Mapping(target = "policyNumber", source = "savingsPolicy.policyNumber")
    PartialWithdrawalPaymentCreateDTO toPartialWithdrawalPaymentCreateDTO(WithdrawalPaymentCreateDTO withdrawalPaymentCreateDTO, SavingsPolicy savingsPolicy);
}
