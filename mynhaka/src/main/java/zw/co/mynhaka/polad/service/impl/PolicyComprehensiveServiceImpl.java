package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.comprehensive.BeneficiaryComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ClaimComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveBeneficiaryDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.DeathBeneficiaryDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.enums.PaymentFrequency;
import zw.co.mynhaka.polad.domain.enums.PaymentMethod;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.BeneficiaryComprehensiveRepository;
import zw.co.mynhaka.polad.repository.PolicyComprehensiveRepository;
import zw.co.mynhaka.polad.service.iface.AgentService;
import zw.co.mynhaka.polad.service.iface.ComprehensiveProductService;
import zw.co.mynhaka.polad.service.iface.PolicyComprehensiveService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.PolicyComprehensiveMapper;
import zw.co.mynhaka.polad.service.mapper.beneficiary.BeneficiaryComprehensiveToBeneficiaryComprehensiveResultDTO;
import zw.co.mynhaka.polad.service.mapper.policies.PolicyComprehensiveToPolicyComprehensiveDTO;
import zw.co.mynhaka.polad.service.util.Constants;
import zw.co.mynhaka.polad.service.util.OtherUtils;
import zw.co.mynhaka.polad.service.util.PolicyBusinessRuleUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class PolicyComprehensiveServiceImpl implements PolicyComprehensiveService {

    private final BeneficiaryComprehensiveToBeneficiaryComprehensiveResultDTO toBeneficiaryResultDTO;
    private final PolicyComprehensiveToPolicyComprehensiveDTO toPolicyComprehensiveDTO;
    private final PolicyHolderService policyHolderService;
    private final PolicyComprehensiveRepository policyComprehensiveRepository;
    private final BeneficiaryComprehensiveRepository beneficiaryRepository;
    private final ComprehensiveProductService comprehensiveProductService;
    private final AgentService agentService;
    private final PolicyComprehensiveMapper policyComprehensiveMapper;


    @Override
    public String allocateProduct(PolicyComprehensive policyComprehensive) {

        log.info("###########About to save comprehensive policy......");
        ComprehensiveFuneralProduct comprehensiveFuneralProduct = comprehensiveProductService.getOne(policyComprehensive.getComprehensiveFuneralProduct().getId());
        policyComprehensive.setSumAssured(comprehensiveFuneralProduct.getSumAssured());
        policyComprehensive.setPremium(comprehensiveFuneralProduct.getPremium());
        policyComprehensiveRepository.save(policyComprehensive);

        return  policyComprehensive.getPolicyNumber().toString();
    }

    @Override
    public PolicyComprehensive getOne(Long id) {
        return policyComprehensiveRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Policy not found"));
    }

    @Override
    public PolicyComprehensiveResultDTO findById(Long id) {
        return policyComprehensiveMapper.toPolicyComprehensiveResultDTO(getOne(id));
    }

    @Override
    public List<BeneficiaryComprehensiveResultDTO> addBeneficiary(List<ComprehensiveBeneficiaryDto> comprehensiveBeneficiaryDto) {

        List<BeneficiaryComprehensive> beneficiaryComprehensives = new ArrayList<>();


        for (ComprehensiveBeneficiaryDto beneficiaryDto : comprehensiveBeneficiaryDto
        ) {
            PolicyComprehensive policyComprehensive = getOne(beneficiaryDto.getPolicyComprehensiveId());
            BeneficiaryComprehensive beneficiary = new BeneficiaryComprehensive();
            beneficiary.setName(beneficiaryDto.getName());
            beneficiary.setIdNumber(beneficiaryDto.getIdNumber());
            beneficiary.setGender(beneficiaryDto.getGender());
            beneficiary.setSurname(beneficiaryDto.getSurname());
            beneficiary.setDateOfBirth(beneficiaryDto.getDateOfBirth());
            beneficiary.setPolicyComprehensive(policyComprehensive);
            beneficiary.setPersonType(beneficiaryDto.getPersonType());
            beneficiary.setComprehensiveFuneral(policyComprehensive.getComprehensiveFuneralProduct());
            beneficiary.setPolicyNumber(policyComprehensive.getPolicyNumber());
            beneficiaryComprehensives.add(beneficiary);
        }

        List<BeneficiaryComprehensive> beneficiaryComprehensives1 = beneficiaryRepository.saveAll(beneficiaryComprehensives);
        //log.info("################ Response to save dependents: {}", beneficiaryComprehensives1.toString());
        return beneficiaryComprehensives1
                .stream()
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());

    }


    @Override
    public List<BeneficiaryComprehensiveResultDTO> addDeathBeneficiary(List<DeathBeneficiaryDto> deathBeneficiaryDto) {
        List<BeneficiaryComprehensive> beneficiaryComprehensives = new ArrayList<>();
        PolicyComprehensive policyComprehensive = getOne(deathBeneficiaryDto.get(0).getPolicyComprehensiveId());

        for (DeathBeneficiaryDto beneficiaryDto : deathBeneficiaryDto
        ) {
            BeneficiaryComprehensive beneficiary = new BeneficiaryComprehensive();
            beneficiary.setName(beneficiaryDto.getName());
            beneficiary.setIdNumber(beneficiaryDto.getIdNumber());
            beneficiary.setGender(beneficiaryDto.getGender());
            beneficiary.setSurname(beneficiaryDto.getSurname());
            beneficiary.setDateOfBirth(beneficiaryDto.getDateOfBirth());
            beneficiary.setPolicyComprehensive(policyComprehensive);
            beneficiary.setPolicyNumber(policyComprehensive.getPolicyNumber());
            beneficiaryComprehensives.add(beneficiary);
        }

        List<BeneficiaryComprehensive> beneficiaryComprehensives1 = beneficiaryRepository.saveAll(beneficiaryComprehensives);
        return beneficiaryComprehensives1
                .stream()
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());
    }


    @Override
    public List<PolicyComprehensive> findAll() {
        return policyComprehensiveRepository.findAll();
    }

    @Override
    public List<PolicyComprehensive> findPolicyComprehensiveByPolicyHolder(PolicyHolder policyHolder) {
        return policyComprehensiveRepository.findPolicyComprehensiveByPolicyHolder(policyHolder);
    }

    @Override
    public List<PolicyComprehensive> findPolicyComprehensiveByPolicyHolderAndNextInvoicingDate(PolicyHolder policyHolder, LocalDate localDate) {
        return policyComprehensiveRepository.findPolicyComprehensiveByPolicyHolderAndNextInvoicingDate(policyHolder, localDate);
    }

    @Override
    public List<PolicyComprehensiveResultDTO> findByPolicyHolderId(Long id) {
        return policyComprehensiveRepository.findAllByPolicyHolder_Id(id)
                .stream()
                .map(policyComprehensiveMapper::toPolicyComprehensiveResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PolicyComprehensive findByPolicyNumber(String policyNumber) {
        return policyComprehensiveRepository.findByPolicyNumber(policyNumber).orElseThrow(
                javax.persistence.EntityNotFoundException::new);
    }

    @Override
    public List<BeneficiaryComprehensiveResultDTO> getBeneficiariesById(Long id) {
        return beneficiaryRepository.findAllByComprehensiveFuneral_Id(id)
                .stream()
                .filter(beneficiaryAccident -> nonNull(beneficiaryAccident.getPersonType()) && beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("BENEFICIARY"))
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeneficiaryComprehensiveResultDTO> getBeneficiariesByPolicyNumber(String policyNumber) {
        return beneficiaryRepository.findAllByPolicyNumber(policyNumber)
                .stream()
                .filter(beneficiaryAccident -> nonNull(beneficiaryAccident.getPersonType()) && beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("BENEFICIARY"))
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public PolicyComprehensiveResultDTO upgradeProduct(Long policyId, Long comprehensiveId) {

        PolicyComprehensive policyComprehensive = getOne(policyId);
        ComprehensiveFuneralProduct comprehensiveFuneralProduct = comprehensiveProductService.getOne(comprehensiveId);

        //check for premium before upgrading
        if(policyComprehensive.getComprehensiveFuneralProduct().getPremium()>comprehensiveFuneralProduct.getPremium()){
            throw  new BusinessValidationException("Cannot upgrade to a lower policy, consider downgrading instead.");
        }
        //upgrade if condition is satisfied
        policyComprehensive.setComprehensiveFuneralProduct(comprehensiveFuneralProduct);

        Set<BeneficiaryComprehensive> spouses = policyComprehensive.getBeneficiaryList()
                .stream()
                .filter(beneficiaryAccident -> beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("SPOUSE"))
                .collect(Collectors.toSet());
        for (BeneficiaryComprehensive b : spouses
        ) {
            b.setComprehensiveFuneral(comprehensiveFuneralProduct);
        }
        policyComprehensive.setPolicyUpgradeStatus(PolicyUpgradeStatus.UPGRADED);

        return toPolicyComprehensiveDTO.convert(policyComprehensiveRepository.save(policyComprehensive));
    }


    @Override
    public PolicyComprehensiveResultDTO downgradeProduct(Long policyId, Long comprehensiveId) {

        PolicyComprehensive policyComprehensive = getOne(policyId);
        ComprehensiveFuneralProduct comprehensiveFuneralProduct = comprehensiveProductService.getOne(comprehensiveId);

        //check for premium before upgrading
        if(comprehensiveFuneralProduct.getPremium()>policyComprehensive.getComprehensiveFuneralProduct().getPremium()){
            throw  new BusinessValidationException("Cannot downgrade to a higher policy, consider upgrading instead.");
        }
        //upgrade if condition is satisfied
        policyComprehensive.setComprehensiveFuneralProduct(comprehensiveFuneralProduct);

        Set<BeneficiaryComprehensive> spouses = policyComprehensive.getBeneficiaryList()
                .stream()
                .filter(beneficiaryAccident -> beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("SPOUSE"))
                .collect(Collectors.toSet());
        for (BeneficiaryComprehensive b : spouses
        ) {
            b.setComprehensiveFuneral(comprehensiveFuneralProduct);
        }
        policyComprehensive.setPolicyUpgradeStatus(PolicyUpgradeStatus.DOWNGRADED);

        return toPolicyComprehensiveDTO.convert(policyComprehensiveRepository.save(policyComprehensive));
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
    public ClaimComprehensiveResultDTO addClaim(DeathClaimCreateDto deathClaimCreateDto) {
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

        List<Long> productList = new ArrayList<>(Arrays.asList(1L, 6L, 11L, 16L));

        List<PolicyComprehensive> policyComprehensives = new ArrayList<>();
        List<PolicyHolder> policyHolders = policyHolderService.findAll();
        IntStream.range(0, policyHolders.size()).forEach(i -> {
            Random random = new Random();
            try {
                PolicyHolder policyHolder = policyHolders.get(i);
                ComprehensiveFuneralProduct comprehensiveFuneralProduct = comprehensiveProductService.getOne(productList.get(random.nextInt(productList.size() - 1)));
                PolicyComprehensive policyComprehensive = new PolicyComprehensive();
                policyComprehensive.setComprehensiveFuneralProduct(comprehensiveFuneralProduct);
                policyComprehensive.setPolicyHolder(policyHolder);
                policyComprehensive.setPaymentFrequency(paymentFrequencies.get(random.nextInt(paymentFrequencies.size() - 1)));
                policyComprehensive.setPaymentMethod(paymentMethods.get(random.nextInt(paymentMethods.size() - 1)));
                policyComprehensive.setPolicyNumber(OtherUtils.generatePolicyNumberForPrincipal());
                policyComprehensive.setApplicationForm_url("policyAccidentCreateDto.getFormLocation()");
                policyComprehensive.setAgent(getAgent());
                policyComprehensive.setNextInvoicingDate(getNextInvoicingDate());
                policyComprehensives.add(policyComprehensive);
            } catch (Exception e) {
                log.info("Exception occurred " + e.getLocalizedMessage());
            }
        });
        policyComprehensiveRepository.saveAll(policyComprehensives);
    }
    @Override
    public List<PolicyComprehensive> findPolicyComprehensiveByPolicyHolder_Id(Long id) {
        return policyComprehensiveRepository.findPolicyComprehensiveByPolicyHolder_Id(id);
    }

    @Override
    public List<BeneficiaryComprehensiveResultDTO> getDependentsById(Long id) {


        return beneficiaryRepository.findAllByComprehensiveFuneral_Id(id)
                .stream()
                .filter(beneficiaryAccident -> !beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("BENEFICIARY"))
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());


    }

    @Override
    public List<BeneficiaryComprehensiveResultDTO> getDependentsByPolicyNumber(String policynumber) {
        return beneficiaryRepository.findAllByPolicyNumber(policynumber)
                .stream()
                .filter(beneficiaryAccident -> !beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("BENEFICIARY"))
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBeneficiaryOrDependent(Long beneficiaryId) {
        beneficiaryRepository.deleteById(beneficiaryId);
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
            log.info("Agent could nto be found");
        }
        return null;
    }

}
