package zw.co.mynhaka.polad.service.mapper.policies;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyComprehensive;
import zw.co.mynhaka.polad.service.mapper.beneficiary.BeneficiaryAccidentToBeneficiaryAccidentResultDTO;
import zw.co.mynhaka.polad.service.mapper.beneficiary.BeneficiaryComprehensiveToBeneficiaryComprehensiveResultDTO;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class PolicyComprehensiveToPolicyComprehensiveDTO implements Converter<PolicyComprehensive, PolicyComprehensiveResultDTO> {

    private final BeneficiaryComprehensiveToBeneficiaryComprehensiveResultDTO toBeneficiaryToBeneficiaryResultDTO;
    private final BankingDetailsToBankingDetailsResult toBankingDetailsResult;
    private final  MobileMoneyDetailsToMobileMoneyDetailsResult toMobileMoneyDetailsResult;


    public PolicyComprehensiveToPolicyComprehensiveDTO(final BeneficiaryComprehensiveToBeneficiaryComprehensiveResultDTO toBeneficiaryToBeneficiaryResultDTO,
                                                       BankingDetailsToBankingDetailsResult toBankingDetailsResult,
                                                       MobileMoneyDetailsToMobileMoneyDetailsResult toMobileMoneyDetailsResult ) {
        this.toBeneficiaryToBeneficiaryResultDTO = toBeneficiaryToBeneficiaryResultDTO;
        this.toBankingDetailsResult = toBankingDetailsResult;
        this.toMobileMoneyDetailsResult = toMobileMoneyDetailsResult;
    }


    @Override
    public PolicyComprehensiveResultDTO convert(PolicyComprehensive policyComprehensive) {

        PolicyComprehensiveResultDTO comprehensiveResultDTO = new PolicyComprehensiveResultDTO();
        comprehensiveResultDTO.setId(policyComprehensive.getId());
        comprehensiveResultDTO.setPolicyHolderId(policyComprehensive.getPolicyHolder().getId());
        comprehensiveResultDTO.setPolicyHolderFirstName(policyComprehensive.getPolicyHolder().getFirstname());
        comprehensiveResultDTO.setPolicyHolderLastName(policyComprehensive.getPolicyHolder().getLastname());
        comprehensiveResultDTO.setComprehensiveProductId(policyComprehensive.getComprehensiveFuneralProduct().getId());
        comprehensiveResultDTO.setComprehensiveProductName(policyComprehensive.getComprehensiveFuneralProduct().getName());
        comprehensiveResultDTO.setAgent(policyComprehensive.getAgent().getName() + " " + policyComprehensive.getAgent().getSurname());

        comprehensiveResultDTO.setPolicyNumber(policyComprehensive.getPolicyNumber());
        comprehensiveResultDTO.setPolicyStatus(policyComprehensive.getPolicyStatus().toString());
        comprehensiveResultDTO.setPaymentFrequency(policyComprehensive.getPaymentFrequency().toString());
        comprehensiveResultDTO.setPaymentMethod(policyComprehensive.getPaymentMethod().toString());
        comprehensiveResultDTO.setComprehensiveProductId(policyComprehensive.getComprehensiveFuneralProduct().getId());


        comprehensiveResultDTO.setApplicationForm_url(policyComprehensive.getApplicationForm_url());
        if (policyComprehensive.getFirstPaymentDate() != null) {
            comprehensiveResultDTO.setFirstPaymentDate(policyComprehensive.getFirstPaymentDate());
        }

        comprehensiveResultDTO.setSumAssured(policyComprehensive.getComprehensiveFuneralProduct().getSumAssured());
        comprehensiveResultDTO.setPremium(policyComprehensive.getComprehensiveFuneralProduct().getPremium());
               /* .add(policyComprehensive.getBeneficiaryList().stream()
                        .filter(beneficiaryComprehensive -> beneficiaryComprehensive.getComprehensiveFuneral() != null)
                        .map(beneficiaryComprehensive -> beneficiaryComprehensive.getComprehensiveFuneral().getPremium())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)));

                */

        comprehensiveResultDTO.setBeneficiaryList(policyComprehensive.getBeneficiaryList()
                .stream()
                .map(toBeneficiaryToBeneficiaryResultDTO::convert)
                .collect(Collectors.toSet()));
        comprehensiveResultDTO.setPremiumPayer(policyComprehensive.getPremiumPayer().toString());
        comprehensiveResultDTO.setFirstPaymentDate(policyComprehensive.getFirstPaymentDate());
        comprehensiveResultDTO.setFinancialAdvisorReference(policyComprehensive.getFinancialAdvisorReference());
        comprehensiveResultDTO.setBankingDetails(toBankingDetailsResult.convert(policyComprehensive.getBankingDetails()));
        comprehensiveResultDTO.setMobileMoneyDetails(toMobileMoneyDetailsResult.convert(policyComprehensive.getMobileMoneyDetails()));
        comprehensiveResultDTO.setPolicyUpgradeStatus(policyComprehensive.getPolicyUpgradeStatus().toString());
        return comprehensiveResultDTO;
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
