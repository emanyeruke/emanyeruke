package zw.co.mynhaka.polad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.PaymentChannel;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PremiumPayment extends AbstractAuditingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String policyNumber;

    @Enumerated(EnumType.STRING)
    private PaymentChannel paymentChannel;

    private String paymentReference;

    private LocalDate paymentDate;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.VALIDATED;
}