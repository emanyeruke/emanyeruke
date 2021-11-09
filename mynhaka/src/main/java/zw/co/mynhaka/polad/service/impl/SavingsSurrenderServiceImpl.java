package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderResultDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderUpdateDTO;
import zw.co.mynhaka.polad.domain.enums.SurrenderStatus;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.domain.model.SavingsSurrender;
import zw.co.mynhaka.polad.repository.SavingsSurrenderRepository;
import zw.co.mynhaka.polad.service.iface.PolicySavingsService;
import zw.co.mynhaka.polad.service.iface.PremiumPaymentService;
import zw.co.mynhaka.polad.service.iface.SavingsSurrenderService;
import zw.co.mynhaka.polad.service.iface.SurrenderValueCalculatorService;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.SavingsSurrenderMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavingsSurrenderServiceImpl implements SavingsSurrenderService {

    private final SavingsSurrenderRepository savingsSurrenderRepository;
    private final SavingsSurrenderMapper savingsSurrenderMapper;
    private final PolicySavingsService policySavingsService;
    private final PremiumPaymentService premiumPaymentService;
    private final SurrenderValueCalculatorService surrenderValueCalculatorService;

    @Override
    public SavingsSurrenderResultDTO save(String policyNumber, SavingsSurrenderCreateDTO savingsSurrenderCreateDTO) {
        PolicySavings policySavings = policySavingsService.findByPolicyNumber(policyNumber);
        boolean canBeSurrendered = ChronoUnit.MONTHS.between(policySavings.getCommencementDate(), LocalDate.now()) >= 12
                && premiumPaymentService.getAllValidatedPaymentsByPolicyNumber(policyNumber).size() >= 12;

        if (!canBeSurrendered)
            throw new BusinessValidationException("Policy can not be surrendered");

        SavingsSurrender savingsSurrender = savingsSurrenderMapper.toSavingsSurrender(savingsSurrenderCreateDTO, policySavings);

        BigDecimal surrenderValue = surrenderValueCalculatorService.calculateValue(premiumPaymentService.getAllValidatedPaymentsByPolicyNumber(policyNumber));
        savingsSurrender.setSurrenderValue(surrenderValue);

        return savingsSurrenderMapper.toSavingsSurrenderResultDTO(savingsSurrenderRepository.save(savingsSurrender));
    }

    @Override
    public List<SavingsSurrenderResultDTO> save(List<SavingsSurrenderUpdateDTO> savingsSurrenderUpdateDTOList) {
        List<SavingsSurrender> savingsSurrenders = savingsSurrenderUpdateDTOList.stream().map(savingsSurrenderUpdateDTO -> {
            SavingsSurrender savingsSurrender = getOne(savingsSurrenderUpdateDTO.getId());
            savingsSurrenderMapper.updateSavingsSurrender(savingsSurrender, savingsSurrenderUpdateDTO);

            return savingsSurrender;
        }).collect(Collectors.toList());

        //TODO: Record payment if status is paid
        return savingsSurrenderRepository.saveAll(savingsSurrenders).stream()
                .map(savingsSurrenderMapper::toSavingsSurrenderResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SavingsSurrender getOne(Long id) {
        return savingsSurrenderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavingsSurrender with id: " + id + " not found"));
    }

    @Override
    public Page<SavingsSurrenderResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return savingsSurrenderRepository.findAll(pageRequest)
                .map(savingsSurrenderMapper::toSavingsSurrenderResultDTO);
    }

    @Override
    public Page<SavingsSurrenderResultDTO> findAllByStatus(int page, int size, String surrenderStatus) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return savingsSurrenderRepository.findAllBySurrenderStatus(SurrenderStatus.valueOf(surrenderStatus), pageRequest)
                .map(savingsSurrenderMapper::toSavingsSurrenderResultDTO);
    }

    @Override
    public void deleteById(Long id) {
        savingsSurrenderRepository.deleteById(id);
    }
}