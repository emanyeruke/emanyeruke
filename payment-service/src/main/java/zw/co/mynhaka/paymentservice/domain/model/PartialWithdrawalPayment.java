package zw.co.mynhaka.paymentservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.paymentservice.domain.enums.PaymentChannel;
import zw.co.mynhaka.paymentservice.domain.enums.PaymentStatus;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PartialWithdrawalPayment extends AbstractAuditingEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String policyNumber;

    @Enumerated(EnumType.STRING)
    private PaymentChannel paymentChannel;

    private String paymentReference;

    private LocalDate paymentDate;

    @DecimalMin("0.00")
    private BigDecimal amount;

    private Long withdrawalId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.VALIDATED;
}
