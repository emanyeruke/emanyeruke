package zw.co.mynhaka.paymentservice.domain.dto.payment;

import lombok.Data;
import zw.co.mynhaka.paymentservice.domain.enums.PaymentChannel;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PartialWithdrawalPaymentCreateDTO {
    private String policyNumber;

    private PaymentChannel paymentChannel;

    private String paymentReference;

    private LocalDate paymentDate;

    @DecimalMin("0.00")
    private BigDecimal amount;

    private Long withdrawalId;
}
