package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import zw.co.mynhaka.polad.domain.dtos.commissionpayment.CommissionPaymentResultDTO;
import zw.co.mynhaka.polad.domain.model.CommissionPayment;

@Mapper(componentModel = "spring")
public interface CommissionPaymentMapper {

    CommissionPaymentResultDTO toCommissionPaymentResultDTO(CommissionPayment commissionPayment);
}
