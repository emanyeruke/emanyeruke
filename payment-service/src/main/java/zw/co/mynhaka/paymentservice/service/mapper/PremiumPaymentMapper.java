package zw.co.mynhaka.paymentservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PremiumPaymentCreateDTO;
import zw.co.mynhaka.paymentservice.domain.dto.payment.PremiumPaymentResultDTO;
import zw.co.mynhaka.paymentservice.domain.model.PremiumPayment;
import zw.co.mynhaka.paymentservice.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PremiumPaymentMapper {

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    PremiumPayment toPremiumPayment(PremiumPaymentCreateDTO premiumPaymentCreateDTO);

    PremiumPaymentResultDTO toPremiumPaymentResultDTO(PremiumPayment premiumPayment);
}
