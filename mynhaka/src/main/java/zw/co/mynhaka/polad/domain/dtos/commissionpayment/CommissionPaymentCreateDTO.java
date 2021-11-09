package zw.co.mynhaka.polad.domain.dtos.commissionpayment;

import lombok.AllArgsConstructor;
import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.PaymentChannel;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;
import zw.co.mynhaka.polad.domain.enums.PolicyType;
import zw.co.mynhaka.polad.domain.model.Agent;

import java.time.LocalDateTime;

@Data
public class CommissionPaymentCreateDTO {

    private int month;

    private String paymentReference;

    private LocalDateTime paymentDate;

    private PaymentChannel paymentChannel;

    //private PolicyType policyType;

    private Long paymentId;

    private Long commissionId;

    private Long agentId;

    private Long managerId;

 //   private Long upsellAgentId;

   // private Long upsellManagerId;

}
