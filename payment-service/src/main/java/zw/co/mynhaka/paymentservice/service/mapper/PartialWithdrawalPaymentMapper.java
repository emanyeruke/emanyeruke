package zw.co.mynhaka.paymentservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PartialWithdrawalPaymentCreateDTO;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PartialWithdrawalPaymentResultDTO;
import zw.co.mynhaka.paymentservice.domain.model.PartialWithdrawalPayment;
import zw.co.mynhaka.paymentservice.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartialWithdrawalPaymentMapper {

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    PartialWithdrawalPayment toPartialWithdrawalPayment(PartialWithdrawalPaymentCreateDTO partialWithdrawalPaymentCreateDTO);

    PartialWithdrawalPaymentResultDTO toPartialWithdrawalPaymentResultDTO(PartialWithdrawalPayment partialWithdrawalPayment);
}
