package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PaymentChannel;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PremiumPaymentUpdateDTO {
    private Long id;

    private String policyNumber;

    private PaymentChannel paymentChannel;

    private String paymentReference;

    private LocalDate paymentDate;

    private BigDecimal amount;

    private PaymentStatus paymentStatus;
}
