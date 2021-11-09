package zw.co.mynhaka.polad.domain.dtos.policyholderproduct;


import lombok.Data;
import zw.co.mynhaka.polad.domain.dtos.BankDetailsCreateDto;
import zw.co.mynhaka.polad.domain.dtos.BankingDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.MobileMoneyDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.enums.PaymentFrequency;
import zw.co.mynhaka.polad.domain.enums.PaymentMethod;
import zw.co.mynhaka.polad.domain.enums.PremiumPayer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PolicyFuneralCreateDto {


    private Long policyHolderId;


    private Long funeralProductId;

    @NotNull(message = "Payment method should not be null")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull(message = "Payment frequency should not be null")
    @Enumerated(EnumType.STRING)
    private PaymentFrequency paymentFrequency;

    private PremiumPayer premiumPayer;

    private @Valid
    BankingDetailsCreateDTO bankingDetails;

    private @Valid
    MobileMoneyDetailsCreateDTO mobileMoneyDetails;

    @NotNull
    private String applicationForm_url;

    private Long agentId;

   // private double premium;

   // private double sumAssured;

    private String financialAdvisorReference;

    private LocalDate commencementDate;

}
