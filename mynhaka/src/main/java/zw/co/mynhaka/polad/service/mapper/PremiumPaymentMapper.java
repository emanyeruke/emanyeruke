package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.PremiumPaymentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.PremiumPaymentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.PremiumPaymentUpdateDTO;
import zw.co.mynhaka.polad.domain.model.PremiumPayment;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PremiumPaymentMapper {

    @IgnoreAuditing
    PremiumPayment toPremiumPayment(PremiumPaymentCreateDTO premiumPaymentCreateDTO);

    PremiumPaymentResultDTO toPremiumPaymentResultDTO(PremiumPayment premiumPayment);

    void updatePremiumPayment(@MappingTarget PremiumPayment premiumPayment, PremiumPaymentUpdateDTO premiumPaymentUpdateDTO);
}
