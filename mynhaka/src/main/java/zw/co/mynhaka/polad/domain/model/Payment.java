package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Payment extends AbstractAuditingEntity {

    private static final long serialVersionUID = 8576177367014635123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JsonIgnore
   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   private Invoice invoice;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Policy policy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PolicyHolder policyHolder;

    private String policyNumber;

    @Enumerated(EnumType.STRING)
    private PaymentChannel paymentChannel;

    private String paymentReference;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    private double billedAmount;

    private double receivedAmount;

    private double suspenseAmount;

    private double arrearsAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private BaseCurrency baseCurrency;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    //suspense logic
    public double suspense(double billedAmount, double receivedAmount){
        if(receivedAmount>billedAmount){
            policy.setPolicyStatus(PolicyStatus.IN_SUSPENSE);
           return suspenseAmount = receivedAmount-billedAmount;
        }else
            policy.setPolicyStatus(PolicyStatus.PAID_UP);
          return   suspenseAmount = 0.0;
    }
    //arrears logic
    public double arrears(double billedAmount, double receivedAmount){
        if(receivedAmount<billedAmount){
            policy.setPolicyStatus(PolicyStatus.IN_ARREARS);
          return  arrearsAmount = billedAmount-receivedAmount;
        }else
            policy.setPolicyStatus(PolicyStatus.PAID_UP);
        return   arrearsAmount = 0.0;
    }
}
