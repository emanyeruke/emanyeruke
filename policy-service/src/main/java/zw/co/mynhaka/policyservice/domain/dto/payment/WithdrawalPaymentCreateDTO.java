package zw.co.mynhaka.policyservice.domain.dto.payment;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.policyservice.domain.enums.PaymentChannel;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WithdrawalPaymentCreateDTO {
    @NotNull
    private Long withdrawalId;

    @NotNull
    private Long savingsPolicyId;

    private PaymentChannel paymentChannel;

    private String paymentReference;

    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate paymentDate;

    @DecimalMin("0.00")
    private BigDecimal amount;
}
