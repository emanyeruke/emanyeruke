package zw.co.mynhaka.polad.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorCreateDto;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorResultDTO;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorUpdateDto;
import zw.co.mynhaka.polad.domain.model.FinancialAdvisor;
import zw.co.mynhaka.polad.repository.FinancialAdvisorRepository;
import zw.co.mynhaka.polad.service.iface.FinancialAdvisorService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.policyholder.FinancialAdvisorToFinancialAdvisorResultDTO;


@Service
@Slf4j
public class FinancialAdvisorServiceImpl implements FinancialAdvisorService {

    private final FinancialAdvisorToFinancialAdvisorResultDTO toFinancialAdvisorResultDTO;
    private final FinancialAdvisorRepository financialAdvisorRepository;

    public FinancialAdvisorServiceImpl(final FinancialAdvisorToFinancialAdvisorResultDTO toFinancialAdvisorResultDTO,
                                       final FinancialAdvisorRepository financialAdvisorRepository) {
        this.toFinancialAdvisorResultDTO = toFinancialAdvisorResultDTO;
        this.financialAdvisorRepository = financialAdvisorRepository;
    }


    @Override
    public Page<FinancialAdvisorResultDTO> findAll(Pageable pageable) {
        return financialAdvisorRepository.findAllBy(pageable)
                .map(toFinancialAdvisorResultDTO::convert);
    }

    @Override
    public FinancialAdvisorResultDTO findById(Long id) {
        return toFinancialAdvisorResultDTO.convert(
                financialAdvisorRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Financial advisro not found with id " + id)));
    }

    @Override
    public FinancialAdvisorResultDTO save(FinancialAdvisorCreateDto financialAdvisorCreateDto) {
        FinancialAdvisor financialAdvisor = new FinancialAdvisor();
        financialAdvisor.setName(financialAdvisorCreateDto.getName());
        financialAdvisor.setSurname(financialAdvisorCreateDto.getSurname());
        financialAdvisor.setContactNumber(financialAdvisorCreateDto.getContactNumber());
        financialAdvisor.setEmail(financialAdvisorCreateDto.getEmail());
        return toFinancialAdvisorResultDTO.convert(financialAdvisorRepository.save(financialAdvisor));
    }

    @Override
    public FinancialAdvisorResultDTO update(FinancialAdvisorUpdateDto financialAdvisorUpdateDto) {
        FinancialAdvisor financialAdvisor = getOne(financialAdvisorUpdateDto.getFinancialAdvisorId());
        financialAdvisor.setName(financialAdvisorUpdateDto.getName());
        financialAdvisor.setSurname(financialAdvisorUpdateDto.getSurname());
        financialAdvisor.setContactNumber(financialAdvisorUpdateDto.getContactNumber());
        financialAdvisor.setEmail(financialAdvisorUpdateDto.getEmail());
        return toFinancialAdvisorResultDTO.convert(financialAdvisorRepository.save(financialAdvisor));
    }

    @Override
    public void deleteById(Long id) {
        financialAdvisorRepository.deleteById(id);
    }

    @Override
    public FinancialAdvisor getOne(Long id) {
        log.debug("Request to get a financial advisor : {}", id);
        return financialAdvisorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Financial advisor not found"));
    }
}
