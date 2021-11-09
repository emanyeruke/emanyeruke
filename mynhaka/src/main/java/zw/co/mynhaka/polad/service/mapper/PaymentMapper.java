package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.payment.IndividualPaymentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.payment.PaymentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.payment.PaymentResultDTO;
import zw.co.mynhaka.polad.domain.model.Invoice;
import zw.co.mynhaka.polad.domain.model.Payment;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

   /* @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice", source = "invoice")
    Payment toPayment(PaymentCreateDTO paymentCreateDTO, Invoice invoice);





    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice", source = "invoice")
    Payment toPayment(IndividualPaymentCreateDTO individualPaymentCreateDTO, Invoice invoice);





    @Mapping(target = "invoiceId", source = "invoice.id")
    //TODO reconfigure payment mapper
    @Mapping(target = "policyHolderId", source = "policyHolder.id")
    @Mapping(target = "policyHolderName", expression = "java(payment.getPolicyHolder().getFirstname()" +
            "+ ' ' + payment.getPolicyHolder().getLastname())")
//    @Mapping(target = "employerId", source = "employer.id")
//    @Mapping(target = "employerName", source = "employer.name")
    PaymentResultDTO toPaymentResultDto(Payment payment);


    */

}
