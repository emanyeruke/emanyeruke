package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.enums.PaymentFrequency;
import zw.co.mynhaka.polad.domain.enums.PaymentMethod;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.PolicySavingsRepository;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.iface.*;
import zw.co.mynhaka.polad.service.mapper.PolicyMapper;
import zw.co.mynhaka.polad.service.mapper.PolicySavingsMapper;
import zw.co.mynhaka.polad.service.mapper.policies.PolicySavingsToPolicySavingsDTO;
import zw.co.mynhaka.polad.service.util.OtherUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

@Slf4j
@Service
@RequiredArgsConstructor
public class PolicySavingsServiceImpl implements PolicySavingsService {

    private final PolicySavingsToPolicySavingsDTO toPolicySavingsDTO;
    private final PolicyHolderService policyHolderService;
    private final SavingsProductService savingsProductService;
    private final PolicySavingsRepository policySavingsRepository;
    private final AgentService agentService;
    private final PolicySavingsMapper policySavingsMapper;
    private final PenaltyService penaltyService;
    private final InterestService interestService;
    private final PolicyMapper policyMapper;

    @Override
    public String allocateProduct(PolicySavings policySavings) {

            log.info("################ABOUT TO SAVE SAVINGS POLICY..............");
            SavingsProduct savingsProduct = savingsProductService.getOne(policySavings.getSavingsProduct().getId());

            policySavings.setPremium(savingsProduct.getMonthlyInvestmentPremium());
            policySavingsRepository.save(policySavings);

            return  policySavings.getPolicyNumber().toString();

    }

    @Override
    public List<PolicySavings> findAll() {
        List<PolicySavings> policySavings = policySavingsRepository.findAll();
        if (policySavings.isEmpty()){
            throw new EntityNotFoundException("No Savings policies at the moment");
        }
        return policySavings;
    }

    @Override
    public PolicySavingsResultDTO findById(Long id) {
        return policyMapper.toPolicySavingsResultDTO(getOne(id));
    }

    @Override
    public List<PolicySavingsResultDTO> findAllByPolicyHolder(Long id) {
        PolicyHolder policyHolder = policyHolderService.getOne(id);

        return policySavingsRepository.findPolicySavingsByPolicyHolder(policyHolder).stream()
                .map(policyMapper::toPolicySavingsResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PolicySavings getOne(Long id) {
        return policySavingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Policy not found"));
    }

    @Override
    public PolicySavings findByPolicyNumber(String policyNumber) {
        return policySavingsRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new EntityNotFoundException("Policy with policy number: " + policyNumber + " not found"));
    }


    @Override
    public void allocateFakerProduct() {
        List<PaymentFrequency> paymentFrequencies = Arrays.asList(PaymentFrequency.ANNUALLY,
                PaymentFrequency.HALF_YEARLY,
                PaymentFrequency.QUARTERLY,
                PaymentFrequency.MONTHLY);

        List<PaymentMethod> paymentMethods = Arrays.asList(PaymentMethod.CASH,
                PaymentMethod.DEBIT_ORDER,
                PaymentMethod.STOP_ORDER);

        List<Long> productList = new ArrayList<Long>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L));

        List<PolicySavings> policySavings = new ArrayList<>();
        List<PolicyHolder> policyHolders = policyHolderService.findAll();
        for (int i = 0; i < policyHolders.size(); i++) {
            Random random = new Random();
            try {
                PolicyHolder policyHolder = policyHolders.get(i);
                SavingsProduct savingsProduct = savingsProductService.getOne(productList.get(random.nextInt(productList.size() - 1)));
                PolicySavings savingsPolicy = new PolicySavings();
                savingsPolicy.setSavingsProduct(savingsProduct);
                savingsPolicy.setPolicyHolder(policyHolder);
                savingsPolicy.setPaymentFrequency(paymentFrequencies.get(random.nextInt(paymentFrequencies.size() - 1)));
                savingsPolicy.setPaymentMethod(paymentMethods.get(random.nextInt(paymentMethods.size() - 1)));
                savingsPolicy.setPolicyNumber(OtherUtils.generatePolicyNumberForPrincipal());
                savingsPolicy.setApplicationForm_url("policyAccidentCreateDto.getFormLocation()");
                savingsPolicy.setAgent(getAgent());
                savingsPolicy.setNextInvoicingDate(getNextInvoicingDate());
                policySavings.add(savingsPolicy);
            } catch (Exception e) {
                log.info("Exception occurred " + e.getLocalizedMessage());
            }
        }
        policySavingsRepository.saveAll(policySavings);
    }

    private LocalDate getNextInvoicingDate() {
        LocalDate today = LocalDate.now();
        return today.plusMonths(1).with(firstDayOfMonth());
    }

    private Agent getAgent() {
        Random random = new Random();

        int x = 1 + random.nextInt(577564) % 50;
        log.info("Agent number: " + x);
        try {
            Agent agent = agentService.getOne((long) x);
            return agent;
        } catch (Exception e) {
            log.info("Agent could nto be found");
        }
        return null;
    }
    @Override
    public List<PolicySavings> findPolicySavingsByPolicyHolder_Id(Long id) {
        return policySavingsRepository.findPolicySavingsByPolicyHolder_Id(id);
    }

    @Override
    public PolicySavingsResultDTO upgradeProduct(Long policyId, Long savingsId) {

        PolicySavings policySavings = getOne(policyId);
        SavingsProduct savingsProduct = savingsProductService.getOne(savingsId);

        //check for premium before upgrading
        if(policySavings.getSavingsProduct().getMonthlyInvestmentPremium()>savingsProduct.getMonthlyInvestmentPremium()){
            throw  new BusinessValidationException("Cannot upgrade to a lower policy, consider downgrading instead.");
        }
        //upgrade if condition is satisfied
        policySavings.setSavingsProduct(savingsProduct);

        Set<BeneficiarySavings> spouses = policySavings.getBeneficiaryList()
                .stream()
                .filter(beneficiaryAccident -> beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("SPOUSE"))
                .collect(Collectors.toSet());
        for (BeneficiarySavings b : spouses
        ) {
            b.setSavings(savingsProduct);
        }
        policySavings.setPolicyUpgradeStatus(PolicyUpgradeStatus.UPGRADED);

        return toPolicySavingsDTO.convert(policySavingsRepository.save(policySavings));
    }

    @Override
    public PolicySavingsResultDTO downgradeProduct(Long policyId, Long savingsId) {

        PolicySavings policySavings = getOne(policyId);
        SavingsProduct savingsProduct = savingsProductService.getOne(savingsId);

        //check for premium before dowgrading
        if(savingsProduct.getMonthlyInvestmentPremium()>policySavings.getSavingsProduct().getMonthlyInvestmentPremium()){
            throw  new BusinessValidationException("Cannot downgrade to a higher policy, consider upgrading instead.");
        }
        //upgrade if condition is satisfied
        policySavings.setSavingsProduct(savingsProduct);

        Set<BeneficiarySavings> spouses = policySavings.getBeneficiaryList()
                .stream()
                .filter(beneficiaryAccident -> beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("SPOUSE"))
                .collect(Collectors.toSet());
        for (BeneficiarySavings b : spouses
        ) {
            b.setSavings(savingsProduct);
        }
        policySavings.setPolicyUpgradeStatus(PolicyUpgradeStatus.DOWNGRADED);

        return toPolicySavingsDTO.convert(policySavingsRepository.save(policySavings));
    }

}
