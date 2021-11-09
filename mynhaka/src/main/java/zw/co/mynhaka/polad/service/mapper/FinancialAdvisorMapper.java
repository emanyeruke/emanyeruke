package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorCreateDto;
import zw.co.mynhaka.polad.domain.model.FinancialAdvisor;

@Mapper(componentModel = "spring")
public interface FinancialAdvisorMapper {
    FinancialAdvisor fromFinancialAdvisorCreateDto(FinancialAdvisorCreateDto financialAdvisorCreateDto);
}
