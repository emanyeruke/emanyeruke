package zw.co.mynhaka.polad.service.mapper.policies;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyAccident;
import zw.co.mynhaka.polad.service.mapper.beneficiary.BeneficiaryAccidentToBeneficiaryAccidentResultDTO;

import java.util.stream.Collectors;

@Component
@Slf4j
public class PolicyAccidentToPolicyAccidentDTO implements Converter<PolicyAccident, PolicyAccidentResultDTO> {

    private final BeneficiaryAccidentToBeneficiaryAccidentResultDTO toBeneficiaryAccidentResultDTO;
    private final BankingDetailsToBankingDetailsResult toBankingDetailsResult;
    private final  MobileMoneyDetailsToMobileMoneyDetailsResult toMobileMoneyDetailsResult;



    public PolicyAccidentToPolicyAccidentDTO(final BeneficiaryAccidentToBeneficiaryAccidentResultDTO toBeneficiaryAccidentResultDTO,
                                             BankingDetailsToBankingDetailsResult toBankingDetailsResult,
                                             MobileMoneyDetailsToMobileMoneyDetailsResult toMobileMoneyDetailsResult ) {
        this.toBeneficiaryAccidentResultDTO = toBeneficiaryAccidentResultDTO;
        this.toBankingDetailsResult = toBankingDetailsResult;
        this.toMobileMoneyDetailsResult = toMobileMoneyDetailsResult;
    }

    @Override
    public PolicyAccidentResultDTO convert(PolicyAccident policyAccident) {
        PolicyAccidentResultDTO policyAccidentResultDTO = new PolicyAccidentResultDTO();
        policyAccidentResultDTO.setAccidentProductId(policyAccident.getAccidentProduct().getId());
        policyAccidentResultDTO.setAgent(policyAccident.getAgent().getName() + " " + policyAccident.getAgent().getSurname());
        policyAccidentResultDTO.setAccidentProductName(policyAccident.getAccidentProduct().getName());
        if (policyAccident.getFirstPaymentDate() != null) {
            policyAccidentResultDTO.setFirstPaymentDate(policyAccident.getFirstPaymentDate());
        }
        policyAccidentResultDTO.setId(policyAccident.getId());
        policyAccidentResultDTO.setPaymentFrequency(policyAccident.getPaymentFrequency().toString());
        policyAccidentResultDTO.setPaymentMethod(policyAccident.getPaymentMethod().toString());
        policyAccidentResultDTO.setPolicyHolderId(policyAccident.getPolicyHolder().getId());
        policyAccidentResultDTO.setPolicyHolderFirstName(policyAccident.getPolicyHolder().getFirstname());
        policyAccidentResultDTO.setPolicyHolderLastName(policyAccident.getPolicyHolder().getLastname());
        policyAccidentResultDTO.setPolicyNumber(policyAccident.getPolicyNumber());
        policyAccidentResultDTO.setPolicyStatus(policyAccident.getPolicyStatus().toString());
        policyAccidentResultDTO.setApplicationForm_url(policyAccident.getApplicationForm_url());

        policyAccidentResultDTO.setPremium(policyAccident.getAccidentProduct().getPremium());
                /*.add(policyAccident.getBeneficiaryList().stream()
                        .filter(beneficiaryAccident -> beneficiaryAccident.getAccident() != null)
                        .map(beneficiaryAccident -> beneficiaryAccident.getAccident().getPremium())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)));

                 */
        policyAccidentResultDTO.setSumAssured(policyAccident.getAccidentProduct().getSumAssured());
        policyAccidentResultDTO.setCommencementDate(policyAccident.getCommencementDate());
        policyAccidentResultDTO.setBeneficiaryList(policyAccident.getBeneficiaryList()
                .stream()
                .map(toBeneficiaryAccidentResultDTO::convert)
                .collect(Collectors.toSet()));
        policyAccidentResultDTO.setPremiumPayer(policyAccident.getPremiumPayer().toString());
        policyAccidentResultDTO.setFirstPaymentDate(policyAccident.getFirstPaymentDate());
        policyAccidentResultDTO.setFinancialAdvisorReference(policyAccident.getFinancialAdvisorReference());
        policyAccidentResultDTO.setBankDetails(toBankingDetailsResult.convert(policyAccident.getBankingDetails()));
        policyAccidentResultDTO.setMobileMoneyDetails(toMobileMoneyDetailsResult.convert(policyAccident.getMobileMoneyDetails()));
        policyAccidentResultDTO.setPolicyUpgradeStatus(policyAccident.getPolicyUpgradeStatus().toString());

        return policyAccidentResultDTO;
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
