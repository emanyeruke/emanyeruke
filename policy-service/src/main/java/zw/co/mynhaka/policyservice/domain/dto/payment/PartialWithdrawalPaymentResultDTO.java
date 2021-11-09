package zw.co.mynhaka.policyservice.domain.dto.payment;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PartialWithdrawalPaymentResultDTO {
    private Long paymentId;

    private String policyNumber;

    private String paymentChannel;

    private String paymentReference;

    private LocalDate paymentDate;

    private BigDecimal amount;

    private Long withdrawalId;

    private String paymentStatus;
}
