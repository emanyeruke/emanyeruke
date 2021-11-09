package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.accident.ClaimAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.accident.PolicyAccidentUpdateCreateDto;
import zw.co.mynhaka.polad.domain.dtos.claim.ClaimAccidentCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.enums.PaymentFrequency;
import zw.co.mynhaka.polad.domain.enums.PaymentMethod;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.BeneficiaryAccidentRepository;
import zw.co.mynhaka.polad.repository.PolicyAccidentRepository;
import zw.co.mynhaka.polad.repository.PolicyRepository;
import zw.co.mynhaka.polad.service.iface.AccidentProductService;
import zw.co.mynhaka.polad.service.iface.AgentService;
import zw.co.mynhaka.polad.service.iface.PolicyAccidentService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.BeneficiaryMapper;
import zw.co.mynhaka.polad.service.mapper.PolicyMapper;
import zw.co.mynhaka.polad.service.mapper.beneficiary.BeneficiaryAccidentToBeneficiaryAccidentResultDTO;
import zw.co.mynhaka.polad.service.mapper.policies.PolicyAccidentToPolicyAccidentDTO;
import zw.co.mynhaka.polad.service.util.Constants;
import zw.co.mynhaka.polad.service.util.OtherUtils;
import zw.co.mynhaka.polad.service.util.PolicyBusinessRuleUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

@Service
@Slf4j
@RequiredArgsConstructor
public class PolicyAccidentServiceImpl implements PolicyAccidentService {


    private final BeneficiaryAccidentToBeneficiaryAccidentResultDTO beneficiaryToBeneficiaryResultDTO;
    private final PolicyAccidentToPolicyAccidentDTO policyAccidentToPolicyAccidentDTO;
    private final PolicyHolderService policyHolderService;
    private final PolicyAccidentRepository repository;
    private final BeneficiaryAccidentRepository beneficiaryRepository;
    private final AccidentProductService accidentProductService;
    private final AgentService agentService;
    private final ModelMapper modelMapper;
    private final PolicyRepository<Policy> policyRepository;
    private final PolicyRepository<PolicyAccident> accidentPolicyRepository;



    private final PolicyMapper policyMapper;
    private final BeneficiaryMapper beneficiaryMapper;

    @Override
    public String allocateProduct(PolicyAccident policyAccident) {

        log.info("#######################ABOUT TO ALLOCATE ACCIDENT POLICY");
        AccidentProduct accidentProduct = accidentProductService.getOne(policyAccident.getAccidentProduct().getId());

        policyAccident.setSumAssured(accidentProduct.getSumAssured());
        policyAccident.setPremium(accidentProduct.getPremium());
         policyRepository.save(policyAccident);

         return  policyAccident.getPolicyNumber().toString();

    }


    @Override
    public PolicyAccident getOne(Long id) {
        Optional<PolicyAccident> policyAccident = repository.findById(id);
        if (!policyAccident.isPresent()){
            throw new EntityNotFoundException("Policy Accident Not Found");
        }
        return policyAccident.get();
    }

    @Override
    public PolicyAccidentResultDTO findById(Long id) {
        return policyAccidentToPolicyAccidentDTO.convert(getOne(id));
    }

    @Override
    public List<PolicyAccident> findPolicyAccidentByPolicyHolder(PolicyHolder policyHolder) {
        return repository.findPolicyAccidentByPolicyHolder(policyHolder);
    }

    @Override
    public List<PolicyAccident> findPolicyAccidentByPolicyHolderAndNextInvoicingDate(PolicyHolder policyHolder, LocalDate invoicingDate) {
        return repository.findPolicyAccidentByPolicyHolderAndNextInvoicingDate(policyHolder, LocalDate.now());
    }

    @Override
    public List<PolicyAccidentResultDTO> findByPolicyHolderId(PolicyHolder policyHolder) {
        return repository.findPolicyAccidentByPolicyHolder(policyHolder)
                .stream()
                .map(policyAccidentToPolicyAccidentDTO::convert)
                .collect(Collectors.toList());
    }

    /*@Override
    public Page<PolicyAccidentResultDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(policyAccidentToPolicyAccidentDTO::convert);
    }

     */

    @Override
    public List<PolicyAccident> getAll() {
        List<PolicyAccident> policyAccidents = accidentPolicyRepository.findAll();
        if(policyAccidents.isEmpty()){
            throw new EntityNotFoundException("No Policy Accident Policies Available at the moment");
        }
        return policyAccidents;
    }



    @Override
    public void delete(Long id) {
        repository.delete(getOne(id));
    }

    @Override
    public PolicyAccident findByPolicyNumber(String policyNumber) {
        return (PolicyAccident) repository.findByPolicyNumber(policyNumber).orElseThrow(() ->
                new EntityNotFoundException("Policy not found")
        );
    }

    @Override
    public Transaction cancelPolicy(String policyNumber) {
        return null;
    }

    @Override
    public Transaction refundPolicy(String policyNumber) {
        return null;
    }

    @Override
    public ClaimAccidentResultDTO addClaim(ClaimAccidentCreateDto claimAccidentCreateDto) {
        return null;
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

        List<Long> productList = new ArrayList<>(Arrays.asList(1L, 3L, 5L, 7L, 9L));

        List<PolicyAccident> policyAccidentList = new ArrayList<>();
        List<PolicyHolder> policyHolders = policyHolderService.findAll();
        for (PolicyHolder holder : policyHolders) {
            Random random = new Random();
            try {
                AccidentProduct accidentProduct = accidentProductService.getOne(productList.get(random.nextInt(productList.size() - 1)));
                PolicyAccident policyAccident = new PolicyAccident();
                policyAccident.setAccidentProduct(accidentProduct);
                policyAccident.setPolicyHolder(holder);
                policyAccident.setPaymentFrequency(paymentFrequencies.get(random.nextInt(paymentFrequencies.size() - 1)));
                policyAccident.setPaymentMethod(paymentMethods.get(random.nextInt(paymentMethods.size() - 1)));
                policyAccident.setPolicyNumber(OtherUtils.generatePolicyNumberForPrincipal());
                policyAccident.setApplicationForm_url("policyAccidentCreateDto.getFormLocation()");
                policyAccident.setAgent(getAgent());
                policyAccident.setNextInvoicingDate(getNextInvoicingDate());
                policyAccidentList.add(policyAccident);
            } catch (Exception e) {
                log.info("Exception occurred " + e.getLocalizedMessage());
            }
        }
        repository.saveAll(policyAccidentList);
    }

    @Override
    public List<PolicyAccident> findPolicyAccidentByPolicyHolder_Id(Long id) {
        return repository.findPolicyAccidentByPolicyHolder_Id(id);
    }

    @Override
    public PolicyAccidentResultDTO changePaymentMethodAndFrequency(PolicyAccidentUpdateCreateDto policyAccidentCreateDto) {
        PolicyAccident policyAccident = getOne(policyAccidentCreateDto.getPolicyId());
        policyAccident.setPaymentFrequency(policyAccidentCreateDto.getPaymentFrequency());
        policyAccident.setPaymentMethod(policyAccidentCreateDto.getPaymentMethod());
        return policyAccidentToPolicyAccidentDTO.convert(repository.save(policyAccident));
    }

    @Override
    public PolicyAccidentResultDTO upgradeProduct(Long policyId, Long accidentId) {
        PolicyAccident policyAccident = getOne(policyId);
        AccidentProduct accidentProduct = accidentProductService.getOne(accidentId);

        //check for premium before upgrading
        if(policyAccident.getAccidentProduct().getPremium()>accidentProduct.getPremium()){
            throw  new BusinessValidationException("Cannot upgrade to a lower policy, consider downgrading instead.");
        }
        //upgrade if condition is satisfied
        policyAccident.setAccidentProduct(accidentProduct);

        Set<BeneficiaryAccident> spouses = policyAccident.getBeneficiaryList()
                .stream()
                .filter(beneficiaryAccident -> beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("SPOUSE"))
                .collect(Collectors.toSet());
        for (BeneficiaryAccident b : spouses
        ) {
            b.setAccident(accidentProduct);
        }
        policyAccident.setPolicyUpgradeStatus(PolicyUpgradeStatus.UPGRADED);

        return policyAccidentToPolicyAccidentDTO.convert(repository.save(policyAccident));
    }


    @Override
    public PolicyAccidentResultDTO downgradeProduct(Long policyId, Long accidentId) {

        PolicyAccident policyAccident = getOne(policyId);
        AccidentProduct accidentProduct = accidentProductService.getOne(accidentId);

        //check for premium before dowgrading
        if(accidentProduct.getPremium()>policyAccident.getAccidentProduct().getPremium()){
            throw  new BusinessValidationException("Cannot downgrade to a higher policy, consider upgrading instead.");
        }
        //upgrade if condition is satisfied
        policyAccident.setAccidentProduct(accidentProduct);

        Set<BeneficiaryAccident> spouses = policyAccident.getBeneficiaryList()
                .stream()
                .filter(beneficiaryAccident -> beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("SPOUSE"))
                .collect(Collectors.toSet());
        for (BeneficiaryAccident b : spouses
        ) {
            b.setAccident(accidentProduct);
        }
        policyAccident.setPolicyUpgradeStatus(PolicyUpgradeStatus.DOWNGRADED);

        return policyAccidentToPolicyAccidentDTO.convert(repository.save(policyAccident));
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
            return agentService.getOne((long) x);
        } catch (Exception e) {
            log.info("Agent could not be found");
        }
        return null;
    }

}
