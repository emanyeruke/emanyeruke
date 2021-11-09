package zw.co.mynhaka.polad.service.mapper.policies;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.service.mapper.beneficiary.BeneficiarySavingsToBeneficiarySavingsResultDTO;

import java.util.stream.Collectors;

@Component
public class PolicySavingsToPolicySavingsDTO implements Converter<PolicySavings, PolicySavingsResultDTO> {

    private final BeneficiarySavingsToBeneficiarySavingsResultDTO toBeneficiaryToBeneficiaryResultDTO;
    private final BankingDetailsToBankingDetailsResult toBankingDetailsResult;
    private final  MobileMoneyDetailsToMobileMoneyDetailsResult toMobileMoneyDetailsResult;

    public PolicySavingsToPolicySavingsDTO(final BeneficiarySavingsToBeneficiarySavingsResultDTO beneficiaryToBeneficiaryResultDTO,
                                           BankingDetailsToBankingDetailsResult toBankingDetailsResult,
                                           MobileMoneyDetailsToMobileMoneyDetailsResult toMobileMoneyDetailsResult) {
        this.toBeneficiaryToBeneficiaryResultDTO = beneficiaryToBeneficiaryResultDTO;
        this.toBankingDetailsResult = toBankingDetailsResult;
        this.toMobileMoneyDetailsResult = toMobileMoneyDetailsResult;
    }

    @Override
    public PolicySavingsResultDTO convert(PolicySavings policySavings) {

        PolicySavingsResultDTO savingsResultDTO = new PolicySavingsResultDTO();
        savingsResultDTO.setId(policySavings.getId());
        savingsResultDTO.setSavingsProductId(policySavings.getSavingsProduct().getId());
        savingsResultDTO.setPolicyHolderId(policySavings.getPolicyHolder().getId());
        savingsResultDTO.setPolicyHolderFirstName(policySavings.getPolicyHolder().getFirstname());
        savingsResultDTO.setPolicyHolderLastName(policySavings.getPolicyHolder().getLastname());
        savingsResultDTO.setAgent(policySavings.getAgent().getName() + " " + policySavings.getAgent().getSurname());
        savingsResultDTO.setPaymentFrequency(policySavings.getPaymentFrequency().toString());
        savingsResultDTO.setPaymentMethod(policySavings.getPaymentMethod().toString());
        savingsResultDTO.setPolicyNumber(policySavings.getPolicyNumber());
        savingsResultDTO.setPolicyStatus(policySavings.getPolicyStatus().toString());
        savingsResultDTO.setApplicationForm_url(policySavings.getApplicationForm_url());
        if (policySavings.getFirstPaymentDate() != null) {
            savingsResultDTO.setFirstPaymentDate(policySavings.getFirstPaymentDate());
        }
        savingsResultDTO.setPremiumWaiverRate(policySavings.getSavingsProduct().getPremiumWaiverRate());
        savingsResultDTO.setMonthlyInvestmentPremium(policySavings.getSavingsProduct().getMonthlyInvestmentPremium());

        savingsResultDTO.setBeneficiaryList(policySavings.getBeneficiaryList()
                .stream()
                .map(toBeneficiaryToBeneficiaryResultDTO::convert)
                .collect(Collectors.toSet()));

        savingsResultDTO.setBalance(policySavings.getBalance());

        savingsResultDTO.setPremiumPayer(policySavings.getPremiumPayer().toString());
        savingsResultDTO.setFirstPaymentDate(policySavings.getFirstPaymentDate());
        savingsResultDTO.setFinancialAdvisorReference(policySavings.getFinancialAdvisorReference());
        savingsResultDTO.setBankingDetails(toBankingDetailsResult.convert(policySavings.getBankingDetails()));
        savingsResultDTO.setMobileMoneyDetails(toMobileMoneyDetailsResult.convert(policySavings.getMobileMoneyDetails()));
        savingsResultDTO.setPolicyUpgradeStatus(policySavings.getPolicyUpgradeStatus().toString());

        return savingsResultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
