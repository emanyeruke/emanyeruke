package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.PaymentChannel;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PremiumPaymentCreateDTO {
    @NotNull
    private String policyNumber;

    private PaymentChannel paymentChannel;

    private String paymentReference;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    @DecimalMin("0.00")
    private BigDecimal amount;

    PaymentStatus paymentStatus;
}