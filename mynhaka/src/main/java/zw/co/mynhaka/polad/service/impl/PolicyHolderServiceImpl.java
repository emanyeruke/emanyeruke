package zw.co.mynhaka.polad.service.impl;


import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderUpdateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.domain.enums.Title;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.*;
import zw.co.mynhaka.polad.service.iface.EmployerService;
import zw.co.mynhaka.polad.service.iface.FinancialAdvisorService;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.PolicyHolderMapper;
import zw.co.mynhaka.polad.service.mapper.PolicyMapper;
import zw.co.mynhaka.polad.service.util.PolicyBusinessRuleUtils;

import java.sql.Date;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PolicyHolderServiceImpl implements PolicyHolderService {

    private final PolicyHolderRepository policyHolderRepository;
    private final PolicyAccidentRepository policyAccidentRepository;
    private final PolicyFuneralRepository policyFuneralRepository;
    private final PolicyComprehensiveRepository policyComprehensiveRepository;
    private final PolicySavingsRepository policySavingsRepository;
    private final LegalGuardianRepository legalGuardianRepository;
    private final FinancialAdvisorRepository financialAdvisorRepository;
    private final EmployerService employerService;

    private final PolicyHolderMapper policyHolderMapper;
    private final PolicyMapper policyMapper;
    private final FinancialAdvisorService financialAdvisorService;

   /* @Override
    public Page<PolicyHolderResultDTO> findAll(Pageable pageable) {
        return policyHolderRepository.findAllByOrderByLastnameAsc(pageable)
                .map(policyHolderMapper::toPolicyHolderResultDTO);
    }

    */

    @Override
    public PolicyHolderResultDTO findById(Long id) {
        return policyHolderMapper.toPolicyHolderResultDTO(getOne(id));
    }

    @Override
    public PolicyHolder add(PolicyHolder policyHolder) {

        log.info("########### I am about to save the policyholder");

        return  policyHolderRepository.save(policyHolder);
    }

    @Override
    public PolicyHolder update(PolicyHolder policyHolder) {

        Optional<PolicyHolder> policyHolderFromDatabase = policyHolderRepository.findById(policyHolder.getId());

        if(!policyHolderFromDatabase.isPresent())
            throw new EntityNotFoundException("No such Policy Holder exists!");

        return policyHolderRepository.save(policyHolder);
    }

    @Override
    public void deleteById(Long id) {
        policyHolderRepository.deleteById(id);
    }

    @Override
    public PolicyHolder getOne(final Long id) {
        log.debug("Request to get a member : {}", id);
        return policyHolderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Policyholder not found"));
    }

    @Override
    public PolicyHolder getOneByNationalID(String nationalId) {
        return policyHolderRepository.findByIdNumber(nationalId);
    }

    @Override
    public List<PolicyAccidentResultDTO> getAccidentPolicy(Long id) {
        return policyAccidentRepository.findPolicyAccidentByPolicyHolder(getOne(id)).stream()
                .map(policyMapper::toPolicyAccidentResultDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<PolicyFuneralResultDTO> getFuneralPolicy(Long id) {
        return policyFuneralRepository.findPolicyFuneralByPolicyHolder(getOne(id))
                .stream()
                .map(policyMapper::toPolicyFuneralResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyComprehensiveResultDTO> getComprehensivePolicy(Long id) {
        return policyComprehensiveRepository.findPolicyComprehensiveByPolicyHolder(getOne(id))
                .stream()
                .map(policyMapper::toPolicyComprehensiveResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicySavingsResultDTO> getSavingsPolicy(Long id) {
        return policySavingsRepository.findPolicySavingsByPolicyHolder(getOne(id))
                .stream()
                .map(policyMapper::toPolicySavingsResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyHolder> findAll() {
        List<PolicyHolder> policyHolders = policyHolderRepository.findAll();
        if(policyHolders.isEmpty()){
            throw  new EntityNotFoundException("No Policy Holders Available At The Moment!");
        }
        return policyHolders;
    }

    @Override
    public void saveFaker() {

        List<Title> titles = Arrays.asList(Title.values());
        List<Gender> genders = Arrays.asList(Gender.values());

        final Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Faker faker = new Faker();
            final PolicyHolder policyHolder = new PolicyHolder();  ///This is your entity--the data you want saved in the DB
            policyHolder.setTitle(titles.get(random.nextInt(titles.size() - 1)));
            policyHolder.setDateOfBirth(faker.date().between(Date.valueOf("1980-01-01"), Date.valueOf("2010-01-01")).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            policyHolder.setEmail(faker.internet().emailAddress());
            policyHolder.setFirstname(faker.name().firstName());
            policyHolder.setLastname(faker.name().lastName());
            policyHolder.setGender(genders.get(random.nextInt(genders.size() - 1)));
            policyHolder.setIdNumber(faker.idNumber().valid());
            policyHolder.setWorkTelephone(faker.phoneNumber().phoneNumber());
            policyHolder.setMobile(faker.phoneNumber().cellPhone());
            policyHolder.setEmployeeNumber(faker.commerce().promotionCode());
            policyHolder.setOccupation(faker.job().title());

            final Address physicalAddress = new Address();
            physicalAddress.setStreet(faker.address().streetAddress());
            physicalAddress.setCity(faker.address().cityName());
            physicalAddress.setSuburb(faker.address().streetName());

            final Address postalAddress = new Address();
            postalAddress.setStreet(faker.address().streetAddress());
            postalAddress.setCity(faker.address().cityName());
            postalAddress.setSuburb(faker.address().streetName());


            /*
            Set Employer
             */
            Long emp = getRandomNumberInRange(0, 1);
            if (emp.intValue() == 0) {
                int employerNumber = 1 + random.nextInt(3457) % 50;
                try {
                    Employer employer = employerService.getOne((long) employerNumber);
                    policyHolder.setEmployer(employer);
                } catch (EntityNotFoundException e) {
                    log.info("Exception occurred");
                }
            }

            /*
            Set Financial Advisor
             */
            FinancialAdvisor financialAdvisor = new FinancialAdvisor();
            Long fnc = getRandomNumberInRange(0, 1);
            if (fnc.intValue() == 0) {

                financialAdvisor.setContactNumber(faker.phoneNumber().cellPhone());
                financialAdvisor.setEmail(faker.internet().emailAddress());
                financialAdvisor.setName(faker.name().firstName());
                financialAdvisor.setSurname(faker.name().lastName());

            }

            /*
            Set Legal Guardian
             */
            final boolean isBelowAge = PolicyBusinessRuleUtils.isPrincipalAbove18(policyHolder.getDateOfBirth(), 18);
            LegalGuardian legalGuardian = new LegalGuardian();
            if (isBelowAge) {

                legalGuardian.setContactNumber(faker.phoneNumber().cellPhone());
                legalGuardian.setDateOfBirth(faker.date().between(Date.valueOf("1980-01-01"), Date.valueOf("2000-01-01")).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
                legalGuardian.setEmail(faker.internet().emailAddress());
                legalGuardian.setName(faker.name().firstName());
                legalGuardian.setSurname(faker.name().lastName());

            }


            policyHolder.setPostalAddress(postalAddress);
            policyHolder.setPhysicalAddress(physicalAddress);
            int x = 1 + random.nextInt(577564) % 50;
            log.info("Agent number: " + x);
            try {
                PolicyHolder savedPolicyHolder = policyHolderRepository.save(policyHolder);
                legalGuardian.setPolicyHolder(savedPolicyHolder);
                //financialAdvisor.setPolicyHolder(savedPolicyHolder);
                if (legalGuardian.getContactNumber() != null) {
                    legalGuardianRepository.save(legalGuardian);
                }
                if (financialAdvisor.getContactNumber() != null) {
                    financialAdvisorRepository.save(financialAdvisor);
                }
            } catch (Exception e) {
                log.info("Agent could nto be found");
            }
        }

    }

    @Override
    public Page<PolicyHolderResultDTO> searchAll(Specification msTitleRating, Pageable pageable) {
        return policyHolderRepository.findAll(msTitleRating, pageable)
                .map(object -> {
                    PolicyHolder policyHolder = (PolicyHolder) object;
                    return policyHolderMapper.toPolicyHolderResultDTO(policyHolder);
                });
    }

    private static Long getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return (long) (r.nextInt((max - min) + 1) + min);
    }

}
