package zw.co.mynhaka.polad.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.funeral.BeneficiaryFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.ClaimFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralBeneficiaryCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralDeathBeneficiaryCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.enums.PaymentFrequency;
import zw.co.mynhaka.polad.domain.enums.PaymentMethod;
import zw.co.mynhaka.polad.domain.enums.PolicyUpgradeStatus;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.BeneficiaryFuneralRepository;
import zw.co.mynhaka.polad.repository.PolicyFuneralRepository;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.iface.AgentService;
import zw.co.mynhaka.polad.service.iface.FuneralProductService;
import zw.co.mynhaka.polad.service.iface.PolicyFuneralService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.mapper.beneficiary.BeneficiaryFuneralToBeneficiaryFuneralResultDTO;
import zw.co.mynhaka.polad.service.mapper.policies.PolicyFuneralToPolicyFuneralDTO;
import zw.co.mynhaka.polad.service.util.OtherUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class PolicyFuneralServiceImpl implements PolicyFuneralService {

    private final BeneficiaryFuneralToBeneficiaryFuneralResultDTO toBeneficiaryResultDTO;
    private final BeneficiaryFuneralRepository beneficiaryRepository;
    private final PolicyFuneralToPolicyFuneralDTO toPolicyFuneralDTO;
    private final PolicyHolderService policyHolderService;
    private final PolicyFuneralRepository policyFuneralRepository;
    private final FuneralProductService funeralProductService;
    private final AgentService agentService;

    public PolicyFuneralServiceImpl(final BeneficiaryFuneralToBeneficiaryFuneralResultDTO toBeneficiaryResultDTO,
                                    final BeneficiaryFuneralRepository beneficiaryRepository,
                                    final PolicyFuneralToPolicyFuneralDTO toPolicyFuneralDTO,
                                    final PolicyHolderService policyHolderService,
                                    final PolicyFuneralRepository policyFuneralRepository,
                                    final FuneralProductService funeralProductService,
                                    final AgentService agentService) {
        this.toBeneficiaryResultDTO = toBeneficiaryResultDTO;
        this.beneficiaryRepository = beneficiaryRepository;
        this.toPolicyFuneralDTO = toPolicyFuneralDTO;
        this.policyHolderService = policyHolderService;
        this.policyFuneralRepository = policyFuneralRepository;
        this.funeralProductService = funeralProductService;
        this.agentService = agentService;
    }

    @Override
    public String allocateProduct(PolicyFuneral policyFuneral) {
            log.info("###################ABOUT TO SAVE FUNERAL POLICY");
            FuneralProduct funeralProduct = funeralProductService.getOne(policyFuneral.getFuneralProduct().getId());

            policyFuneral.setSumAssured(funeralProduct.getSumAssured());
            policyFuneral.setPremium(funeralProduct.getPremium());
            policyFuneralRepository.save(policyFuneral);

            return  policyFuneral.getPolicyNumber().toString();
    }

    @Override
    public PolicyFuneral getOne(Long id) {
        return policyFuneralRepository.getOne(id);
    }

    @Override
    public PolicyFuneralResultDTO findById(Long id) {
        return toPolicyFuneralDTO.convert(getOne(id));
    }

    @Override
    public List<BeneficiaryFuneralResultDTO> addBeneficiary(List<FuneralBeneficiaryCreateDto> funeralBeneficiaryCreateDto) {

        List<BeneficiaryFuneral> beneficiaryFunerals = new ArrayList<>();
        PolicyFuneral policyFuneral = getOne(funeralBeneficiaryCreateDto.get(0).getPolicyFuneralId());
        for (FuneralBeneficiaryCreateDto beneficiaryCreateDto : funeralBeneficiaryCreateDto
        ) {
            BeneficiaryFuneral beneficiary = new BeneficiaryFuneral();
            beneficiary.setPersonType(beneficiaryCreateDto.getPersonType());
            beneficiary.setName(beneficiaryCreateDto.getName());
            beneficiary.setIdNumber(beneficiaryCreateDto.getIdNumber());
            beneficiary.setGender(beneficiaryCreateDto.getGender());
            beneficiary.setSurname(beneficiaryCreateDto.getSurname());
            beneficiary.setDateOfBirth(beneficiaryCreateDto.getDateOfBirth());
            beneficiary.setPersonType(beneficiaryCreateDto.getPersonType());
            beneficiary.setPolicyFuneral(policyFuneral);
            beneficiary.setFuneral(policyFuneral.getFuneralProduct());
            beneficiary.setPolicyNumber(policyFuneral.getPolicyNumber());
            beneficiaryFunerals.add(beneficiary);
        }

        List<BeneficiaryFuneral> beneficiaryFunerals1 = beneficiaryRepository.saveAll(beneficiaryFunerals);
        return beneficiaryFunerals1
                .stream()
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeneficiaryFuneralResultDTO> addDeathBeneficiary(List<FuneralDeathBeneficiaryCreateDto> deathBeneficiaryCreateDto) {
        List<BeneficiaryFuneral> beneficiaryFunerals = new ArrayList<>();
        PolicyFuneral policyFuneral = getOne(deathBeneficiaryCreateDto.get(0).getPolicyFuneralId());
        for (FuneralDeathBeneficiaryCreateDto funeralBeneficiaryCreateDto : deathBeneficiaryCreateDto
        ) {
            BeneficiaryFuneral beneficiary = new BeneficiaryFuneral();
            beneficiary.setName(funeralBeneficiaryCreateDto.getName());
            beneficiary.setIdNumber(funeralBeneficiaryCreateDto.getIdNumber());
            beneficiary.setGender(funeralBeneficiaryCreateDto.getGender());
            beneficiary.setSurname(funeralBeneficiaryCreateDto.getSurname());
            beneficiary.setDateOfBirth(funeralBeneficiaryCreateDto.getDateOfBirth());
            beneficiary.setPolicyFuneral(policyFuneral);
            beneficiary.setPolicyNumber(policyFuneral.getPolicyNumber());
            beneficiaryFunerals.add(beneficiary);
        }
        List<BeneficiaryFuneral> beneficiaryFunerals1 = beneficiaryRepository.saveAll(beneficiaryFunerals);
        return beneficiaryFunerals1
                .stream()
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());
    }


    @Override
    public List<PolicyFuneral> findAll() {
      List<PolicyFuneral> policyFunerals = policyFuneralRepository.findAll();
      if (policyFunerals.isEmpty()){
          throw new EntityNotFoundException("No Funeral Policies at the moment.");
      }
      return  policyFunerals;
    }

    @Override
    public List<PolicyFuneral> findPolicyFuneralByPolicyHolder(PolicyHolder policyHolder) {
        return policyFuneralRepository.findPolicyFuneralByPolicyHolder(policyHolder);
    }

    @Override
    public List<PolicyFuneral> findPolicyFuneralByPolicyHolderAndNextInvoicingDate(PolicyHolder policyHolder, LocalDate localDate) {
        return policyFuneralRepository.findPolicyFuneralByPolicyHolderAndNextInvoicingDate(policyHolder, localDate);
    }

    @Override
    public List<PolicyFuneralResultDTO> findByPolicyHolderId(Long id) {
        return policyFuneralRepository.findAllByPolicyHolder_Id(id)
                .stream()
                .map(toPolicyFuneralDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public PolicyFuneral findByPolicyNumber(String policyNumber) {
        return policyFuneralRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(javax.persistence.EntityNotFoundException::new);
    }

    @Override
    public List<BeneficiaryFuneralResultDTO> getBeneficiariesById(Long policyId) {
        return beneficiaryRepository.findAllByPolicyFuneral_Id(policyId)
                .stream()
                .filter(beneficiaryAccident -> nonNull(beneficiaryAccident.getPersonType()) && beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("DEPENDENT"))
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeneficiaryFuneralResultDTO> getBeneficiariesByPolicyNumber(String policyNumber) {
        return beneficiaryRepository.findAllByPolicyNumber(policyNumber)
                .stream()
                .filter(beneficiaryAccident -> nonNull(beneficiaryAccident.getPersonType()) && beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("DEPENDENT"))
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());
    }
    @Override
    public PolicyFuneralResultDTO upgradeProduct(Long policyId, Long funeralId) {

        PolicyFuneral policyFuneral = getOne(policyId);
        FuneralProduct funeralProduct = funeralProductService.getOne(funeralId);


        //check for premium before upgrading
        if(policyFuneral.getFuneralProduct().getPremium()>funeralProduct.getPremium()){
            throw  new BusinessValidationException("Cannot upgrade to a lower policy, consider dowgrading instead.");
        }
        //upgrade if condition is satisfied
        policyFuneral.setFuneralProduct(funeralProduct);

        Set<BeneficiaryFuneral> spouses = policyFuneral.getBeneficiaryList()
                .stream()
                .filter(beneficiaryAccident -> beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("SPOUSE"))
                .collect(Collectors.toSet());
        for (BeneficiaryFuneral b : spouses
        ) {
            b.setFuneral(funeralProduct);
        }
        policyFuneral.setPolicyUpgradeStatus(PolicyUpgradeStatus.UPGRADED);

        return toPolicyFuneralDTO.convert(policyFuneralRepository.save(policyFuneral));
    }


    @Override
    public PolicyFuneralResultDTO downgradeProduct(Long policyId, Long funeralId) {

        PolicyFuneral policyFuneral = getOne(policyId);
        FuneralProduct funeralProduct = funeralProductService.getOne(funeralId);

        //check for premium before dowgrading
        if(funeralProduct.getPremium()>policyFuneral.getFuneralProduct().getPremium()){
            throw  new BusinessValidationException("Cannot downgrade to a higher policy, consider upgrading instead.");
        }
        //upgrade if condition is satisfied
        policyFuneral.setFuneralProduct(funeralProduct);

        Set<BeneficiaryFuneral> spouses = policyFuneral.getBeneficiaryList()
                .stream()
                .filter(beneficiaryAccident -> beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("SPOUSE"))
                .collect(Collectors.toSet());
        for (BeneficiaryFuneral b : spouses
        ) {
            b.setFuneral(funeralProduct);
        }
        policyFuneral.setPolicyUpgradeStatus(PolicyUpgradeStatus.DOWNGRADED);

        return toPolicyFuneralDTO.convert(policyFuneralRepository.save(policyFuneral));
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
    public ClaimFuneralResultDTO addClaim(DeathClaimCreateDto deathClaimCreateDto) {
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

        List<Long> productList = new ArrayList<Long>(Arrays.asList(1L, 6L, 11L, 16L, 21L));

        List<PolicyFuneral> policyFunerals = new ArrayList<>();
        List<PolicyHolder> policyHolders = policyHolderService.findAll();
        for (int i = 0; i < policyHolders.size(); i++) {
            Random random = new Random();
            try {
                PolicyHolder policyHolder = policyHolders.get(i);
                FuneralProduct funeralProduct = funeralProductService.getOne(productList.get(random.nextInt(productList.size() - 1)));
                PolicyFuneral policyFuneral = new PolicyFuneral();
                policyFuneral.setFuneralProduct(funeralProduct);
                policyFuneral.setPolicyHolder(policyHolder);
                policyFuneral.setPaymentFrequency(paymentFrequencies.get(random.nextInt(paymentFrequencies.size() - 1)));
                policyFuneral.setPaymentMethod(paymentMethods.get(random.nextInt(paymentMethods.size() - 1)));
                policyFuneral.setPolicyNumber(OtherUtils.generatePolicyNumberForPrincipal());
                policyFuneral.setApplicationForm_url("policyAccidentCreateDto.getFormLocation()");
                policyFuneral.setAgent(getAgent());
                policyFuneral.setNextInvoicingDate(getNextInvoicingDate());
                policyFunerals.add(policyFuneral);
            } catch (Exception e) {
                log.info("Exception occurred " + e.getLocalizedMessage());
            }
        }
        policyFuneralRepository.saveAll(policyFunerals);
    }
    @Override
    public List<PolicyFuneral> findPolicyFuneralByPolicyHolder_Id(Long id) {
        return policyFuneralRepository.findPolicyFuneralByPolicyHolder_Id(id);
    }

    @Override
    public List<BeneficiaryFuneralResultDTO> getDependentsById(Long id) {
        return beneficiaryRepository.findAllByPolicyFuneral_Id(id)
                .stream()
                .filter(beneficiaryAccident -> !beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("DEPENDENT"))
                .map(toBeneficiaryResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeneficiaryFuneralResultDTO> getDependentsByPolicyNumber(String policynumber) {
        return beneficiaryRepository.findAllByPolicyNumber(policynumber)
                .stream()
                .filter(beneficiaryAccident -> !beneficiaryAccident.getPersonType().toString().equalsIgnoreCase("DEPENDENT"))
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
            Agent agent = agentService.getOne((long) x);
            return agent;
        } catch (Exception e) {
            log.info("Agent could nto be found");
        }
        return null;
    }


}
