package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import zw.co.mynhaka.polad.domain.enums.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Indexed
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(of = {"policyNumber"}, callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AttributeOverrides({
        @AttributeOverride(
                name = "bankingDetails.bankName",
                column = @Column(name = "bank_name")
        ),
        @AttributeOverride(
                name = "bankingDetails.branch",
                column = @Column(name = "branch")
        ),
        @AttributeOverride(
                name = "bankingDetails.accountName",
                column = @Column(name = "account_name")
        ),
        @AttributeOverride(
                name = "bankingDetails.accountNumber",
                column = @Column(name = "account_number")
        ),
        @AttributeOverride(
                name = "mobileMoneyDetails.mobileNumber",
                column = @Column(name = "mobile_number")
        ),
        @AttributeOverride(
                name = "mobileMoneyDetails.mobileAccountName",
                column = @Column(name = "mobile_account_name")
        )
})

public abstract class Policy extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private PolicyHolder policyHolder;

    @NaturalId
    @Field
    @Column(nullable = false, unique = true, length = 20)
    private String policyNumber;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Field
    @Enumerated(EnumType.STRING)
    private PaymentFrequency paymentFrequency;

    private LocalDate nextInvoicingDate;

    private LocalDate firstPaymentDate;

    private LocalDate commencementDate;

    private LocalDate cancelDate;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus = PolicyStatus.WAITING_FOR_FIRST_PAYMENT;

    @Enumerated(EnumType.STRING)
    private PolicyState policyState = PolicyState.ACTIVE;

    private double premium;

    private double sumAssured;

    private BankingDetails bankingDetails;

    private MobileMoneyDetails mobileMoneyDetails;

    @Field
    private String applicationForm_url;

    @Field
    private String financialAdvisorReference;

    @Enumerated(EnumType.STRING)
    private PremiumPayer premiumPayer;

    @Enumerated(EnumType.STRING)
    private PolicyUpgradeStatus policyUpgradeStatus;


    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    Agent agent;
}
