package zw.co.mynhaka.polad.domain.dtos.payment;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.BaseCurrency;
import zw.co.mynhaka.polad.domain.enums.PaymentMethod;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;
import zw.co.mynhaka.polad.domain.enums.PolicyType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IndividualPaymentCreateDTO {

    //private Long policyHolderId;

   // private Long policyNumber;

    private String paymentChannel;

    private String paymentReference;

    private LocalDate paymentDate;

    private double billedAmount;

    private double receivedAmount;

    private double suspenseAmount;

    private double arrearsAmount;

    private  PaymentStatus paymentStatus;

    private BaseCurrency baseCurrency;

    private PolicyType policyType;

    private PaymentMethod paymentMethod;
}
