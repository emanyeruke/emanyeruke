package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import zw.co.mynhaka.polad.domain.enums.PaymentChannel;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;
import zw.co.mynhaka.polad.domain.enums.PolicyType;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
public class CommissionPayment extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int month;

    private String paymentReference;

    private LocalDateTime paymentDate = LocalDateTime.now();

    private double agentCommission;

    private double managerCommission;

    private double upsellAgentCommission;

    private double upsellManagerCommission;

    @Enumerated(EnumType.STRING)
    private PaymentChannel paymentChannel;

    @Enumerated(EnumType.STRING)
    private PolicyUpgradeStatus policyUpgradeStatus;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Agent agent;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Agent upsellAgent;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Manager manager;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Manager upsellManager;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Commission commission;


    //method to calculate commission for agent
    public double calculateAgentCommission(double commissionRate){
        if(payment.getReceivedAmount() == payment.getBilledAmount()){
            agentCommission = commissionRate*payment.getBilledAmount();

        }else
            throw new BusinessValidationException("Amount received is less than billed amount. Unable to process commission ");

        return  agentCommission;
    }

    //method to calculate commission for manager
    public double calculateManagerCommission(double commissionRate){
        if(payment.getReceivedAmount() == payment.getBilledAmount()){
            managerCommission = commissionRate*payment.getBilledAmount();

        }else
            throw new BusinessValidationException("Amount received is less than billed amount. Unable to process commission ");

        return  managerCommission;

    }

    //method to calculate commission for upsell agent
    public double calculateUpsellAgentCommission(double commissionRate){
        if(payment.getReceivedAmount() == payment.getBilledAmount()){
            upsellAgentCommission = commissionRate*payment.getBilledAmount();

        }else
            throw new BusinessValidationException("Amount received is less than billed amount. Unable to process commission ");

        return  upsellAgentCommission;

    }

    //method to calculate commission for upsell manager
    public double calculateUpsellManagerCommission(double commissionRate){
        if(payment.getReceivedAmount() == payment.getBilledAmount()){
            upsellManagerCommission = commissionRate*payment.getBilledAmount();

        }else
            throw new BusinessValidationException("Amount received is less than billed amount. Unable to process commission ");

        return  upsellAgentCommission;

    }
}
