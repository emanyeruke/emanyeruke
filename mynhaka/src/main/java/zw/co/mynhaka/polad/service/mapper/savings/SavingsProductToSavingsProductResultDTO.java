package zw.co.mynhaka.polad.service.mapper.savings;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductResultDTO;
import zw.co.mynhaka.polad.domain.model.SavingsProduct;


@Component
public class SavingsProductToSavingsProductResultDTO implements Converter<SavingsProduct, SavingsProductResultDTO> {
    @Override
    public SavingsProductResultDTO convert(SavingsProduct savingsProduct) {
        SavingsProductResultDTO resultDTO = new SavingsProductResultDTO();
        resultDTO.setId(savingsProduct.getId());
        resultDTO.setMonthlyInvestmentPremium(savingsProduct.getMonthlyInvestmentPremium());
        resultDTO.setPremiumWaiverRate(savingsProduct.getPremiumWaiverRate());
        resultDTO.setName(savingsProduct.getName());
        resultDTO.setPersonType(savingsProduct.getPersonType().toString());
        resultDTO.setSavingsId(savingsProduct.getSavings().getId());
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
