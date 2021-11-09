package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.allocation.AllocationCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.allocation.AllocationResultDTO;
import zw.co.mynhaka.polad.domain.enums.PaymentStatus;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.AllocationRepository;
import zw.co.mynhaka.polad.service.iface.*;
import zw.co.mynhaka.polad.service.mapper.allocation.AllocationCreateToAllocation;
import zw.co.mynhaka.polad.service.mapper.allocation.AllocationToAllocationResultDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class AllocationServiceImpl implements AllocationService {

    private final AllocationCreateToAllocation toAllocation;
    private final AllocationToAllocationResultDTO toAllocationResultDTO;
    private final PolicyAccidentService policyAccidentService;
    private final PolicyComprehensiveService policyComprehensiveService;
    private final PolicySavingsService policySavingsService;
    private final PolicyFuneralService policyFuneralService;
    private final PaymentService paymentService;
    private final AllocationRepository allocationRepository;

    @Override
    public AllocationResultDTO allocateToAccidentPolicy(AllocationCreateDTO allocationCreateDTO) {
        PolicyAccident policyAccident = policyAccidentService.findByPolicyNumber(allocationCreateDTO.getPolicyNumber());
        Payment payment = paymentService.findByIdAndPaymentStatus(allocationCreateDTO.getPaymentId(), PaymentStatus.VALIDATED.toString());
        Allocation allocation = toAllocation.convert(allocationCreateDTO);
        Transaction transaction = new Transaction();
        transaction.setPolicyNumber(policyAccident.getPolicyNumber());
        String year = "-" + LocalDate.now().getYear();
        transaction.setPolicyStatusId(LocalDate.now().getMonth().toString().concat(year));
        allocation.setTransaction(transaction);

//        if (allocationCreateDTO.getAmount().compareTo(
//                calculateAccidentPolicyTotal(policyAccident.getPolicyHolder().getPolicyAccidents())) > 0) {
//            policyAccident.setPolicyStatus(PolicyStatus.PAID_UP);
//            if (policyAccident.getFirstPaymentDate() == null) {
//                policyAccident.setFirstPaymentDate(LocalDate.ofInstant(payment.getCreatedDate(), ZoneId.systemDefault()));
//            }
//        }
        return toAllocationResultDTO.convert(allocationRepository.save(allocation));
    }

    @Override
    public AllocationResultDTO allocateToSavingsPolicy(AllocationCreateDTO allocationCreateDTO) {
        PolicySavings policySavings = policySavingsService.findByPolicyNumber(allocationCreateDTO.getPolicyNumber());
        Payment payment = paymentService.findByIdAndPaymentStatus(allocationCreateDTO.getPaymentId(), PaymentStatus.VALIDATED.toString());
        Allocation allocation = toAllocation.convert(allocationCreateDTO);
        Transaction transaction = new Transaction();
        transaction.setPolicyNumber(policySavings.getPolicyNumber());
        String year = "-" + LocalDate.now().getYear();
        transaction.setPolicyStatusId(LocalDate.now().getMonth().toString().concat(year));
        allocation.setTransaction(transaction);

//        if (allocationCreateDTO.getAmount().compareTo(
//                calculateAccidentPolicyTotal(policySavings.getPolicyHolder().getPolicyAccidents())) > 0) {
//            //  policySavings.balance.add(allocationCreateDTO.getAmount);
//            policySavings.setPolicyStatus(PolicyStatus.PAID_UP);
//            if (policySavings.getFirstPaymentDate() == null) {
//                policySavings.setFirstPaymentDate(LocalDate.ofInstant(payment.getCreatedDate(), ZoneId.systemDefault()));
//            }
//        }
        return toAllocationResultDTO.convert(allocationRepository.save(allocation));
    }

    @Override
    public AllocationResultDTO allocateToFuneralPolicy(AllocationCreateDTO allocationCreateDTO) {
        PolicyFuneral policyFuneral = policyFuneralService.findByPolicyNumber(allocationCreateDTO.getPolicyNumber());
        Payment payment = paymentService.findByIdAndPaymentStatus(allocationCreateDTO.getPaymentId(), PaymentStatus.VALIDATED.toString());
        Allocation allocation = toAllocation.convert(allocationCreateDTO);
        Transaction transaction = new Transaction();
        transaction.setPolicyNumber(policyFuneral.getPolicyNumber());
        String year = "-" + LocalDate.now().getYear();
        transaction.setPolicyStatusId(LocalDate.now().getMonth().toString().concat(year));
        allocation.setTransaction(transaction);

//        if (allocationCreateDTO.getAmount().compareTo(
//                calculateAccidentPolicyTotal(policyFuneral.getPolicyHolder().getPolicyAccidents())) > 0) {
//            policyFuneral.setPolicyStatus(PolicyStatus.PAID_UP);
//            if (policyFuneral.getFirstPaymentDate() == null) {
//                policyFuneral.setFirstPaymentDate(LocalDate.ofInstant(payment.getCreatedDate(), ZoneId.systemDefault()));
//            }
//        }
        return toAllocationResultDTO.convert(allocationRepository.save(allocation));
    }

    @Override
    public AllocationResultDTO allocateToComprehensivePolicy(AllocationCreateDTO allocationCreateDTO) {
        PolicyComprehensive policyComprehensive = policyComprehensiveService.findByPolicyNumber(allocationCreateDTO.getPolicyNumber());
        Payment payment = paymentService.findByIdAndPaymentStatus(allocationCreateDTO.getPaymentId(), PaymentStatus.VALIDATED.toString());
        Allocation allocation = toAllocation.convert(allocationCreateDTO);
        Transaction transaction = new Transaction();
        transaction.setPolicyNumber(policyComprehensive.getPolicyNumber());
        String year = "-" + LocalDate.now().getYear();
        transaction.setPolicyStatusId(LocalDate.now().getMonth().toString().concat(year));
        allocation.setTransaction(transaction);

//        if (allocationCreateDTO.getAmount().compareTo(
//                calculateAccidentPolicyTotal(policyComprehensive.getPolicyHolder().getPolicyAccidents())) > 0) {
//            policyComprehensive.setPolicyStatus(PolicyStatus.PAID_UP);
//            if (policyComprehensive.getFirstPaymentDate() == null) {
//                policyComprehensive.setFirstPaymentDate(LocalDate.ofInstant(payment.getCreatedDate(), ZoneId.systemDefault()));
//            }
//        }
        return toAllocationResultDTO.convert(allocationRepository.save(allocation));
    }

    private List<Double> calculateFuneralPolicyTotal(Set<PolicyFuneral> policyFunerals) {

        List<Double> policyAmount = new ArrayList<>();
        for (PolicyFuneral policyFuneral : policyFunerals) {
            policyAmount.add(policyFuneral.getFuneralProduct().getPremium());

            for (BeneficiaryFuneral beneficiary : policyFuneral.getBeneficiaryList()
            ) {
                policyAmount.add(beneficiary.getFuneral().getPremium());
            }
        }
        return policyAmount;
        /*.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

         */
    }

    private List<Double> calculateAccidentPolicyTotal(Set<PolicyAccident> policyAccidents) {

        List<Double> policyAmount = new ArrayList<>();
        for (PolicyAccident policyAccident : policyAccidents) {
            policyAmount.add(policyAccident.getAccidentProduct().getPremium());

            for (BeneficiaryAccident beneficiary : policyAccident.getBeneficiaryList()
            ) {
                policyAmount.add(beneficiary.getAccident().getPremium());
            }
        }
        return policyAmount;
        /*.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

         */
    }

    private List<Double> calculateSavingsPolicyTotal(Set<PolicySavings> policySavings) {

        List<Double> policyAmount = new ArrayList<>();
        for (PolicySavings policySaving : policySavings) {
            policyAmount.add(policySaving.getSavingsProduct().getPremiumWaiverRate());
            //.add(policySaving.getSavingsProduct().getMonthlyInvestmentPremium()));
        }
        return policyAmount;
        /*.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

         */
    }

    private List<Double> calculateComprehensivePolicyTotal(Set<PolicyComprehensive> policyComprehensives) {

        List<Double> policyAmount = new ArrayList<>();
        for (PolicyComprehensive policyComprehensive : policyComprehensives) {
            policyAmount.add(policyComprehensive.getComprehensiveFuneralProduct().getPremium());

            for (BeneficiaryComprehensive beneficiary : policyComprehensive.getBeneficiaryList()
            ) {
                policyAmount.add(beneficiary.getComprehensiveFuneral().getPremium());
            }
        }
        return policyAmount;
        /*.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

         */
    }
}
