package zw.co.mynhaka.polad.service.mapper.invoice;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceItemResultDTO;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.service.iface.PolicyAccidentService;
import zw.co.mynhaka.polad.service.iface.PolicyComprehensiveService;
import zw.co.mynhaka.polad.service.iface.PolicyFuneralService;
import zw.co.mynhaka.polad.service.iface.PolicySavingsService;

import java.math.BigDecimal;

@Component
public class InvoiceItemToInvoiceItemResultDTO implements Converter<InvoiceItem, InvoiceItemResultDTO> {

    private final PolicyAccidentService policyAccidentService;
    private final PolicySavingsService policySavingsService;
    private final PolicyFuneralService policyFuneralService;
    private final PolicyComprehensiveService policyComprehensiveService;

    public InvoiceItemToInvoiceItemResultDTO(final PolicyAccidentService policyAccidentService,
                                             final PolicySavingsService policySavingsService,
                                             final PolicyFuneralService policyFuneralService,
                                             final PolicyComprehensiveService policyComprehensiveService) {
        this.policyAccidentService = policyAccidentService;
        this.policySavingsService = policySavingsService;
        this.policyFuneralService = policyFuneralService;
        this.policyComprehensiveService = policyComprehensiveService;
    }

    @Override
    public InvoiceItemResultDTO convert(InvoiceItem invoiceItem) {
        InvoiceItemResultDTO invoiceItemResultDTO = new InvoiceItemResultDTO();
        invoiceItemResultDTO.setId(invoiceItem.getId());
        if (invoiceItem.getPolicyholder() != null) {
            invoiceItemResultDTO.setPrincipal(invoiceItem.getPolicyholder());
        }
        if (invoiceItem.getBeneficiary() != null) {
            invoiceItemResultDTO.setBeneficiary(invoiceItem.getBeneficiary());
        }
        invoiceItemResultDTO.setFirstName(invoiceItem.getPolicyholder());
        invoiceItemResultDTO.setLastName(invoiceItem.getPolicyholder());
        invoiceItemResultDTO.setProduct(invoiceItem.getProduct());
        invoiceItemResultDTO.setPrice(invoiceItem.getPrice());
        invoiceItemResultDTO.setPolicyNumber(invoiceItem.getPolicyNumber());
        invoiceItemResultDTO.setPolicyType(invoiceItem.getPolicyType().toString());

        String policyType = invoiceItem.getPolicyType().toString();
        if (invoiceItem.getBeneficiary() == null) {
            switch (policyType) {
                case "SAVINGS": {
                    PolicySavings policySavings = policySavingsService.findByPolicyNumber(invoiceItem.getPolicyNumber());
                    invoiceItemResultDTO.setTotalPremium(policySavings.getSavingsProduct().getMonthlyInvestmentPremium());
                            /*.add(policySavings.getBeneficiaryList().stream()
                                    .filter(beneficiarySavings -> beneficiarySavings.getSavings() != null)
                                    .map(beneficiaryAccident -> beneficiaryAccident.getSavings().getMonthlyInvestmentPremium())
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)));

                             */
                    invoiceItemResultDTO.setPrincipalCover(policySavings.getSavingsProduct().getMonthlyInvestmentPremium());
                    invoiceItemResultDTO.setDependentsNumber(policySavings.getBeneficiaryList()
                            .stream().count());
                    break;
                }
                case "COMPREHENSIVE": {
                    PolicyComprehensive policyComprehensive = policyComprehensiveService.findByPolicyNumber(invoiceItem.getPolicyNumber());
                    invoiceItemResultDTO.setTotalPremium(policyComprehensive.getComprehensiveFuneralProduct().getPremium());
                            /*.add(policyComprehensive.getBeneficiaryList().stream()
                                    .filter(beneficiaryComprehensive -> beneficiaryComprehensive.getComprehensiveFuneral() != null)
                                    .map(beneficiaryComprehensive -> beneficiaryComprehensive.getComprehensiveFuneral().getPremium())
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)));

                             */
                    invoiceItemResultDTO.setPrincipalCover(policyComprehensive.getComprehensiveFuneralProduct().getSumAssured());

                    invoiceItemResultDTO.setDependentsNumber(policyComprehensive.getBeneficiaryList()
                            .stream().count());
                    break;
                }
                case "FUNERAL": {
                    PolicyFuneral policyFuneral = policyFuneralService.findByPolicyNumber(invoiceItem.getPolicyNumber());
                    invoiceItemResultDTO.setTotalPremium(policyFuneral.getFuneralProduct().getPremium());
                            /*.add(policyFuneral.getBeneficiaryList().stream()
                                    .filter(beneficiaryFuneral -> beneficiaryFuneral.getFuneral() != null)
                                    .map(beneficiaryFuneral -> beneficiaryFuneral.getFuneral().getPremium())
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)));

                             */
                    invoiceItemResultDTO.setPrincipalCover(policyFuneral.getFuneralProduct().getSumAssured());
                    invoiceItemResultDTO.setDependentsNumber(policyFuneral.getBeneficiaryList()
                            .stream().count());
                    break;
                }
                case "ACCIDENT": {
                    PolicyAccident policyAccident = policyAccidentService.findByPolicyNumber(invoiceItem.getPolicyNumber());
                    invoiceItemResultDTO.setTotalPremium(policyAccident.getAccidentProduct().getPremium());
                           /* .add(policyAccident.getBeneficiaryList().stream()
                                    .filter(beneficiaryAccident -> beneficiaryAccident.getAccident() != null)
                                    .map(beneficiaryAccident -> beneficiaryAccident.getAccident().getPremium())
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)));

                            */
                    invoiceItemResultDTO.setPrincipalCover(policyAccident.getAccidentProduct().getSumAssured());
                    invoiceItemResultDTO.setDependentsNumber(policyAccident.getBeneficiaryList()
                            .stream().count());
                    break;
                }
                default:

            }
        }
        return invoiceItemResultDTO;
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
