package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.*;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Savings;
import zw.co.mynhaka.polad.domain.model.SavingsProduct;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavingsProductMapper {
    @Mapping(target = "savingsId", source = "savingsProduct.savings.id")
    SavingsProductResultDTO toSavingsProductResultDTO(SavingsProduct savingsProduct);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "savingsProductCreateDto.name")
    SavingsProduct toSavingsProduct(Savings savings, SavingsProductCreateDto savingsProductCreateDto);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "savingsProductReverseCreateDto.name")
    @Mapping(target = "premiumWaiverRate", source = "savingsProductReverseCreateDto.monthlyInvestmentPremium", qualifiedByName = "savingsPremiumWaiver")
    SavingsProduct toSavingsProduct(Savings savings, SavingsProductReverseCreateDto savingsProductReverseCreateDto);

    @IgnoreAuditing
    @Mapping(target = "premiumWaiverRate", source = "savingsProductUpdateDto.monthlyInvestmentPremium", qualifiedByName = "savingsPremiumWaiver")
    void updateSavingsProduct(@MappingTarget SavingsProduct savingsProduct, SavingsProductUpdateDto savingsProductUpdateDto);

    @Named("savingsPremiumWaiver")
    default BigDecimal premiumWaiver(BigDecimal monthlyInvestmentPremium) {
        //TODO get premiumWaiverRate from properties file
        return monthlyInvestmentPremium.multiply(BigDecimal.valueOf(0.064));
    }
}
