package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorCreateDto;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorResultDTO;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorUpdateDto;
import zw.co.mynhaka.polad.domain.model.FinancialAdvisor;


public interface FinancialAdvisorService {
    Page<FinancialAdvisorResultDTO> findAll(Pageable pageable);

    FinancialAdvisorResultDTO findById(Long id);

    FinancialAdvisorResultDTO save(FinancialAdvisorCreateDto financialAdvisorCreateDto);

    FinancialAdvisorResultDTO update(FinancialAdvisorUpdateDto financialAdvisorUpdateDto);

    void deleteById(Long id);

    FinancialAdvisor getOne(final Long id);
}
