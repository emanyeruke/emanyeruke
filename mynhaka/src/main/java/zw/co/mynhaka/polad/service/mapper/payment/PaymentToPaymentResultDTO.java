package zw.co.mynhaka.polad.service.mapper.payment;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.payment.PaymentResultDTO;
import zw.co.mynhaka.polad.domain.model.Payment;


@Component
public class PaymentToPaymentResultDTO implements Converter<Payment, PaymentResultDTO> {
    @Override
    public PaymentResultDTO convert(Payment payment) {
        PaymentResultDTO paymentResultDTO = new PaymentResultDTO();
        paymentResultDTO.setAmount(payment.getBilledAmount());
        paymentResultDTO.setId(payment.getId());
       // paymentResultDTO.setInvoiceId(payment.getInvoice().getId());
        paymentResultDTO.setPaymentStatus(payment.getPaymentStatus().toString());

//        if (payment.getPolicyHolder() != null) {
//            paymentResultDTO.setPolicyHolderId(payment.getPolicyHolder().getId());
//            paymentResultDTO.setPolicyHolderName(payment.getPolicyHolder().getFirstname() + " " + payment.getPolicyHolder().getLastname());
//
//        }
        paymentResultDTO.setPaymentChannel(payment.getPaymentChannel().toString());
        paymentResultDTO.setPaymentReference(payment.getPaymentReference());
        paymentResultDTO.setPaymentDate(payment.getPaymentDate());


//        if (payment.getEmployer() != null) {
//            paymentResultDTO.setEmployerName(payment.getEmployer().getName());
//        }

        return paymentResultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
