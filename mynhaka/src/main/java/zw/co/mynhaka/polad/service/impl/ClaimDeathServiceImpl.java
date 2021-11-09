package zw.co.mynhaka.polad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.mynhaka.polad.domain.dtos.funeral.ClaimFuneralResultDTO;
import zw.co.mynhaka.polad.domain.enums.ClaimStatus;
import zw.co.mynhaka.polad.domain.enums.DeathCause;
import zw.co.mynhaka.polad.domain.model.ClaimSavingsDeath;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.repository.ClaimFuneralRepository;
import zw.co.mynhaka.polad.repository.ClaimSavingsDeathRepository;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.*;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClaimDeathServiceImpl implements ClaimSavingsDeathService {

    private final ClaimSavingsDeathRepository repository;
    private final PolicyHolderService policyHolderService;
    private final PolicySavingsService policySavingsService;
    private final PremiumPaymentService premiumPaymentService;
    private final SurrenderValueCalculatorService surrenderValueCalculatorService;

    @Autowired
    public ClaimDeathServiceImpl(ClaimSavingsDeathRepository repository, PolicyHolderService policyHolderService, PolicySavingsService policySavingsService, PremiumPaymentService premiumPaymentService, SurrenderValueCalculatorService surrenderValueCalculatorService) {
        this.repository = repository;
        this.policyHolderService = policyHolderService;
        this.policySavingsService = policySavingsService;
        this.premiumPaymentService = premiumPaymentService;
        this.surrenderValueCalculatorService = surrenderValueCalculatorService;
    }



    @Override
    public String add(ClaimSavingsDeath claimSavingsDeath) {
        PolicySavings policySavings = policySavingsService.findByPolicyNumber(claimSavingsDeath.getPolicyNumber());
        if (claimSavingsDeath.getDeathCause()== DeathCause.SICKNESS || claimSavingsDeath.getDeathCause()==DeathCause.OTHER){
            boolean canBeSurrendered = ChronoUnit.MONTHS.between(policySavings.getCommencementDate(), LocalDate.now()) >= 6
                    && premiumPaymentService.getAllValidatedPaymentsByPolicyNumber(claimSavingsDeath.getPolicyNumber()).size() >= 6;

            if (!canBeSurrendered)
                throw new BusinessValidationException("Policy not yet mature");

            BigDecimal amount = surrenderValueCalculatorService.calculateValue(premiumPaymentService.getAllValidatedPaymentsByPolicyNumber(claimSavingsDeath.getPolicyNumber()));
            claimSavingsDeath.setAmount(amount);
        } else
        repository.save(claimSavingsDeath);
        return "Claim Has Been Successfully Initiated";
    }

    @Override
    public List<ClaimSavingsDeath> findAll() {
        List<ClaimSavingsDeath> claimSavingsDeaths = repository.findAll();
        if (claimSavingsDeaths.isEmpty()) throw  new EntityNotFoundException("No Savings Death Claims");
        return claimSavingsDeaths;
    }

    @Override
    public List<ClaimSavingsDeath> findAllByPolicyNumber(String policyNumber) {
        List<ClaimSavingsDeath> claimSavingsDeaths = repository.findByPolicyNumber(policyNumber);

        if (claimSavingsDeaths.isEmpty())
            throw  new EntityNotFoundException("Savings Death Claims by policy number: "+ policyNumber+"Not Found");
        return null;
    }



    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    @Override
    public ClaimSavingsDeath getOne(Long id) {
        Optional<ClaimSavingsDeath> claimSavingsDeath = repository.findById(id);

        if (!claimSavingsDeath.isPresent())
            throw new EntityNotFoundException("Savings Death Claim with Id: "+ id + "Not Found");
        return claimSavingsDeath.get();
    }

    @Override
    public ClaimSavingsDeath validateClaim(Long id) {
       ClaimSavingsDeath claimSavingsDeath = repository.getOne(id);

        if (claimSavingsDeath.getClaimStatus() == ClaimStatus.INITIATED) {
            claimSavingsDeath.setClaimStatus(ClaimStatus.VALIDATED);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimSavingsDeath.getClaimStatus() + " status");
        }
        return repository.save(claimSavingsDeath);
    }

    @Override
    public ClaimSavingsDeath authorizeClaim(Long id) {
        ClaimSavingsDeath claimSavingsDeath = repository.getOne(id);

        if (claimSavingsDeath.getClaimStatus() == ClaimStatus.VALIDATED) {
            claimSavingsDeath.setClaimStatus(ClaimStatus.AUTHORISED_FOR_PAYMENT);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimSavingsDeath.getClaimStatus() + " status");
        }
        return repository.save(claimSavingsDeath);
    }

    @Override
    public ClaimSavingsDeath approveClaim(Long id) {
        ClaimSavingsDeath claimSavingsDeath = repository.getOne(id);
        if (claimSavingsDeath.getClaimStatus() == ClaimStatus.AUTHORISED_FOR_PAYMENT) {
            claimSavingsDeath.setClaimStatus(ClaimStatus.AWAITING_PAYMENT);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimSavingsDeath.getClaimStatus() + " status");
        }
        return repository.save(claimSavingsDeath);
    }

    @Override
    public ClaimSavingsDeath payClaim(Long id) {
        ClaimSavingsDeath claimSavingsDeath = repository.getOne(id);
        if (claimSavingsDeath.getClaimStatus() == ClaimStatus.AWAITING_PAYMENT) {
            claimSavingsDeath.setClaimStatus(ClaimStatus.PAID);
            claimSavingsDeath.setDateClaimPaid(Instant.now());
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimSavingsDeath.getClaimStatus() + " status");
        }
        return repository.save(claimSavingsDeath);
    }

    @Override
    public ClaimSavingsDeath cancelClaim(Long id) {
        ClaimSavingsDeath claimSavingsDeath = repository.getOne(id);
        if (claimSavingsDeath.getClaimStatus() != ClaimStatus.PAID) {
            claimSavingsDeath.setClaimStatus(ClaimStatus.REJECTED);
        } else {
            throw new BusinessValidationException("Claim cannot be validated as it is in " + claimSavingsDeath.getClaimStatus() + " status");
        }
        return repository.save(claimSavingsDeath);
    }

    @Override
    public ClaimSavingsDeath getOneDto(Long id) {
        return null;
    }

    @Override
    public List<ClaimSavingsDeath> findAllByPolicyHolder(Long id) {
        PolicyHolder policyHolder = policyHolderService.getOne(id);
        List<ClaimSavingsDeath> claimSavingsDeath = repository.findAllByPolicyHolder(policyHolder);
        if (claimSavingsDeath.isEmpty())
            throw new EntityNotFoundException("Claims for policy holder with Id: "+ id + "Not Found");

        return claimSavingsDeath;
    }
}
