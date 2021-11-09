package zw.co.mynhaka.polad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.domain.model.SavingsMaturity;
import zw.co.mynhaka.polad.repository.SavingsMaturityRepository;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.PolicySavingsService;
import zw.co.mynhaka.polad.service.iface.PremiumPaymentService;
import zw.co.mynhaka.polad.service.iface.SavingsMaturityService;
import zw.co.mynhaka.polad.service.iface.SurrenderValueCalculatorService;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SavingsMaturityServiceImpl implements SavingsMaturityService {

    private final SavingsMaturityRepository savingsMaturityRepository;
    private final PolicySavingsService policySavingsService;
    private final PremiumPaymentService premiumPaymentService;
    private final SurrenderValueCalculatorService surrenderValueCalculatorService;

    @Autowired
    public SavingsMaturityServiceImpl(SavingsMaturityRepository savingsMaturityRepository, PolicySavingsService policySavingsService, PremiumPaymentService premiumPaymentService, SurrenderValueCalculatorService surrenderValueCalculatorService) {
        this.savingsMaturityRepository = savingsMaturityRepository;
        this.policySavingsService = policySavingsService;
        this.premiumPaymentService = premiumPaymentService;
        this.surrenderValueCalculatorService = surrenderValueCalculatorService;
    }


    @Override
    public SavingsMaturity save(String policyNumber, SavingsMaturity savingsMaturity) {
        PolicySavings policySavings = policySavingsService.findByPolicyNumber(policyNumber);
        boolean canBeSurrendered = ChronoUnit.MONTHS.between(policySavings.getCommencementDate(), LocalDate.now()) >= 12
                && premiumPaymentService.getAllValidatedPaymentsByPolicyNumber(policyNumber).size() >= 12;

        if (!canBeSurrendered)
            throw new BusinessValidationException("Policy cannot mature");

        BigDecimal maturityValue = surrenderValueCalculatorService.calculateValue(premiumPaymentService.getAllValidatedPaymentsByPolicyNumber(policyNumber));
        savingsMaturity.setMaturityValue(maturityValue);

        return savingsMaturityRepository.save(savingsMaturity);
    }

    @Override
    public SavingsMaturity update(SavingsMaturity savingsMaturity) {
        Optional<SavingsMaturity> savingsMaturityFromDatabase = savingsMaturityRepository.findById(savingsMaturity.getId());
        if (!savingsMaturityFromDatabase.isPresent())
            throw  new EntityNotFoundException("Savings Maturity Policy Does Not Exist");
        savingsMaturity.setCreatedDate(savingsMaturityFromDatabase.get().getCreatedDate());

        savingsMaturityRepository.save(savingsMaturity);

        return savingsMaturity;
    }


    @Override
    public SavingsMaturity getOne(Long id) {
        Optional<SavingsMaturity> savingsMaturity = savingsMaturityRepository.findById(id);
        if (!savingsMaturity.isPresent())
            throw new EntityNotFoundException("Savings Maturity With Id: "+ id + "Does Not Exist");
        return savingsMaturity.get();
    }

    @Override
    public List<SavingsMaturity> findAll() {
        List<SavingsMaturity> savingsMaturities = savingsMaturityRepository.findAll();
        if (savingsMaturities.isEmpty()) throw new EntityNotFoundException("No Savings Maturity Policies Available");
        return savingsMaturities;
    }

    @Override
    public List<SavingsMaturity> findAllByStatus(String maturityStatus) {
        List<SavingsMaturity> savingsMaturities = savingsMaturityRepository.findAllByMaturityStatus(maturityStatus);
        if (savingsMaturities.isEmpty())
            throw new EntityNotFoundException("Savings Maturity Policies With Status: "+ maturityStatus + " Not Found");
        return savingsMaturities;
    }

    @Override
    public void deleteById(Long id) {
        savingsMaturityRepository.deleteById(id);

    }
}
