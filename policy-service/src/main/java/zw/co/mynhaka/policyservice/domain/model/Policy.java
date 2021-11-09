package zw.co.mynhaka.policyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import zw.co.mynhaka.policyservice.domain.enums.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(of = {"id", "policyNumber"}, callSuper = false)
public abstract class Policy extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(unique = true, length = 20)
    private String policyNumber;

    @Column(nullable = false)
    private Long agentId;

    @Column(nullable = false)
    private Long policyHolderId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentFrequency paymentFrequency;

    private LocalDate nextInvoicingDate;

    private LocalDate commencementDate;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus = PolicyStatus.WAITING_FOR_FIRST_PAYMENT;

    @Enumerated(EnumType.STRING)
    private PolicyState policyState = PolicyState.ACTIVE;

    private String applicationForm_url;
}
