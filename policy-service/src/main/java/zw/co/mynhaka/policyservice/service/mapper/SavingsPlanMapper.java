package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.*;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanUpdateDTO;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.domain.model.SavingsPlan;
import zw.co.mynhaka.policyservice.service.utils.IgnoreAuditing;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavingsPlanMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "savingsPlanCreateDTO.name")
    SavingsPlan toSavingsPlan(SavingsPlanCreateDTO savingsPlanCreateDTO, Product product);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "savingsPlanReverseCreateDTO.name")
    @Mapping(target = "premiumWaiver", source = "savingsPlanReverseCreateDTO.monthlyInvestmentPremium", qualifiedByName = "savingsPremiumWaiver")
    SavingsPlan toSavingsPlan(SavingsPlanReverseCreateDTO savingsPlanReverseCreateDTO, Product product);

    @Mapping(target = "productId", source = "savingsPlan.id")
    SavingsPlanResultDTO toSavingsPlanResultDTO(SavingsPlan savingsPlan);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "product")
    @Mapping(target = "name", source = "savingsPlanUpdateDTO.name")
    void updateSavingsPlan(@MappingTarget SavingsPlan savingsPlan, SavingsPlanUpdateDTO savingsPlanUpdateDTO, Product product);

    @Named("savingsPremiumWaiver")
    default BigDecimal premiumWaiver(BigDecimal monthlyInvestmentPremium) {
        //TODO get premiumWaiverRate from properties file
        return monthlyInvestmentPremium.multiply(BigDecimal.valueOf(0.064));
    }
}
