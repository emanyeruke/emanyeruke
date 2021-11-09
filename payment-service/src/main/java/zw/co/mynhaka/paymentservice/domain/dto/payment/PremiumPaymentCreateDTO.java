package zw.co.mynhaka.paymentservice.domain.dto.payment;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.paymentservice.domain.enums.PaymentChannel;
import zw.co.mynhaka.paymentservice.domain.enums.PaymentStatus;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PremiumPaymentCreateDTO {
    private String policyNumber;

    private PaymentChannel paymentChannel;

    private String paymentReference;

    @DateTimeFormat( pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    @DecimalMin("0.00")
    private BigDecimal amount;

    PaymentStatus paymentStatus;
}
