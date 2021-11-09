package zw.co.mynhaka.polad.domain.dtos.policyholderproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.dtos.BankDetailsCreateDto;
import zw.co.mynhaka.polad.domain.dtos.BankingDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.MobileMoneyDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.accident.BeneficiaryAccidentResultDTO;
import zw.co.mynhaka.polad.domain.enums.PremiumPayer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyAccidentResultDTO {

    private Long id;

    private Long accidentProductId;

    private String accidentProductName;

    private Long policyHolderId;

    private String policyHolderFirstName;

    private String policyHolderLastName;

    private String policyNumber;

    private String paymentMethod;

    private String paymentFrequency;

    private String premiumPayer;

    BankingDetailsCreateDTO bankDetails;

    MobileMoneyDetailsCreateDTO mobileMoneyDetails;

    private LocalDate firstPaymentDate;

    private Set<BeneficiaryAccidentResultDTO> beneficiaryList = new HashSet<>();

    private String policyStatus;

    private String applicationForm_url;

    private String agent;

    private String financialAdvisorReference;

    private double sumAssured;

    private double premium;

    private LocalDate commencementDate;

    private String policyUpgradeStatus;


}
