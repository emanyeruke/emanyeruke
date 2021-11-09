package zw.co.mynhaka.polad.domain.dtos.commissionpayment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.dtos.payment.PaymentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderResultDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionPaymentResultDTO {
    private Long id;
    private String paymentReference;
    private double amount;
    private LocalDateTime paymentDate;
    private String paymentChannel;
    private PaymentResultDTO paymentResultDTO;
    private String policyType;
    private String paymentStatus;
}
