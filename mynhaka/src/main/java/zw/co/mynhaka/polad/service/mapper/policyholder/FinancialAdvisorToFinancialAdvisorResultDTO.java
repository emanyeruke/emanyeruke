package zw.co.mynhaka.polad.service.mapper.policyholder;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorResultDTO;
import zw.co.mynhaka.polad.domain.model.FinancialAdvisor;


@Component
public class FinancialAdvisorToFinancialAdvisorResultDTO implements Converter<FinancialAdvisor, FinancialAdvisorResultDTO> {
    @Override
    public FinancialAdvisorResultDTO convert(FinancialAdvisor financialAdvisor) {
        FinancialAdvisorResultDTO resultDTO = new FinancialAdvisorResultDTO();
        resultDTO.setContactNumber(financialAdvisor.getContactNumber());
        resultDTO.setEmail(financialAdvisor.getEmail());
        resultDTO.setId(financialAdvisor.getId());
        resultDTO.setName(financialAdvisor.getName());
        resultDTO.setSurname(financialAdvisor.getSurname());

        return resultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
