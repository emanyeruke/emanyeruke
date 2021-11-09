package zw.co.mynhaka.polad.service.mapper.payment;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.payment.PaymentCreateDTO;
import zw.co.mynhaka.polad.domain.model.Payment;


@Component
public class PaymentCreateDtoToPayment implements Converter<PaymentCreateDTO, Payment> {
    @Override
    public Payment convert(PaymentCreateDTO paymentCreateDTO) {
        return null;
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
