package zw.co.mynhaka.polad.domain.dtos.policyholderproduct;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.dtos.BankDetailsCreateDto;
import zw.co.mynhaka.polad.domain.dtos.BankingDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.MobileMoneyDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.accident.BeneficiaryAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.comprehensive.BeneficiaryComprehensiveResultDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyComprehensiveResultDTO {

    private Long id;

    private Long comprehensiveProductId;

    private String comprehensiveProductName;

    private Long policyHolderId;

    private String policyHolderFirstName;

    private String policyHolderLastName;

    private String policyNumber;

    private String paymentMethod;

    private String paymentFrequency;

    private String premiumPayer;

    BankingDetailsCreateDTO bankingDetails;

    MobileMoneyDetailsCreateDTO mobileMoneyDetails;

    private LocalDate firstPaymentDate;

    private Set<BeneficiaryComprehensiveResultDTO> beneficiaryList = new HashSet<>();

    private String policyStatus;

    private String applicationForm_url;

    private String agent;

    private String financialAdvisorReference;

    private double sumAssured;

    private double premium;

    private LocalDate commencementDate;

    private String policyUpgradeStatus;




}
