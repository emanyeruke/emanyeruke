package zw.co.mynhaka.polad.service.mapper.policies;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyFuneral;
import zw.co.mynhaka.polad.service.mapper.beneficiary.BeneficiaryFuneralToBeneficiaryFuneralResultDTO;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class PolicyFuneralToPolicyFuneralDTO implements Converter<PolicyFuneral, PolicyFuneralResultDTO> {

    private final BeneficiaryFuneralToBeneficiaryFuneralResultDTO toBeneficiaryToBeneficiaryResultDTO;
    private final BankingDetailsToBankingDetailsResult toBankingDetailsResult;
    private final  MobileMoneyDetailsToMobileMoneyDetailsResult toMobileMoneyDetailsResult;

    public PolicyFuneralToPolicyFuneralDTO(final BeneficiaryFuneralToBeneficiaryFuneralResultDTO beneficiaryToBeneficiaryResultDTO,
                                           BankingDetailsToBankingDetailsResult toBankingDetailsResult,
                                           MobileMoneyDetailsToMobileMoneyDetailsResult toMobileMoneyDetailsResult ) {

        this.toBeneficiaryToBeneficiaryResultDTO = beneficiaryToBeneficiaryResultDTO;
        this.toBankingDetailsResult = toBankingDetailsResult;
        this.toMobileMoneyDetailsResult = toMobileMoneyDetailsResult;
    }


    @Override
    public PolicyFuneralResultDTO convert(PolicyFuneral policyFuneral) {

        PolicyFuneralResultDTO funeralResultDTO = new PolicyFuneralResultDTO();
        funeralResultDTO.setId(policyFuneral.getId());
        funeralResultDTO.setPolicyHolderId(policyFuneral.getPolicyHolder().getId());
        funeralResultDTO.setPolicyHolderFirstName(policyFuneral.getPolicyHolder().getFirstname());
        funeralResultDTO.setPolicyHolderLastName(policyFuneral.getPolicyHolder().getLastname());
        funeralResultDTO.setAgent(policyFuneral.getAgent().getName() + " " + policyFuneral.getAgent().getSurname());
        funeralResultDTO.setFuneralProductId(policyFuneral.getFuneralProduct().getId());
        funeralResultDTO.setFuneralProductName(policyFuneral.getFuneralProduct().getName());
        funeralResultDTO.setPaymentFrequency(policyFuneral.getPaymentFrequency().toString());
        funeralResultDTO.setPaymentMethod(policyFuneral.getPaymentMethod().toString());
        funeralResultDTO.setPolicyNumber(policyFuneral.getPolicyNumber());
        funeralResultDTO.setPolicyStatus(policyFuneral.getPolicyStatus().toString());
        funeralResultDTO.setApplicationForm_url(policyFuneral.getApplicationForm_url());
        if (policyFuneral.getFirstPaymentDate() != null) {
            funeralResultDTO.setFirstPaymentDate(policyFuneral.getFirstPaymentDate());
        }
        funeralResultDTO.setSumAssured(policyFuneral.getFuneralProduct().getSumAssured());

        funeralResultDTO.setPremium(policyFuneral.getFuneralProduct().getPremium());
        policyFuneral.getBeneficiaryList();
                /*.stream()
                .filter(beneficiaryFuneral -> beneficiaryFuneral.getFuneral() != null)
                .map(beneficiaryFuneral -> beneficiaryFuneral.getFuneral().getPremium())
                .reduce(BigDecimal.ZERO, BigDecimal::add)));

                 */
        funeralResultDTO.setBeneficiaryList(policyFuneral.getBeneficiaryList()
                .stream()
                .map(toBeneficiaryToBeneficiaryResultDTO::convert)
                .collect(Collectors.toSet()));
        funeralResultDTO.setCommencementDate(policyFuneral.getCommencementDate());

        funeralResultDTO.setPremiumPayer(policyFuneral.getPremiumPayer().toString());
        funeralResultDTO.setFirstPaymentDate(policyFuneral.getFirstPaymentDate());
        funeralResultDTO.setFinancialAdvisorReference(policyFuneral.getFinancialAdvisorReference());
        funeralResultDTO.setBankingDetails(toBankingDetailsResult.convert(policyFuneral.getBankingDetails()));
        funeralResultDTO.setMobileMoneyDetails(toMobileMoneyDetailsResult.convert(policyFuneral.getMobileMoneyDetails()));
        funeralResultDTO.setPolicyUpgradeStatus(policyFuneral.getPolicyUpgradeStatus().toString());

        return funeralResultDTO;
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
