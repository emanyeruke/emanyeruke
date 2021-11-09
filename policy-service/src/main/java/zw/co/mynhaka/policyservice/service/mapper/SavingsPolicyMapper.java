package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyservice.domain.dto.savingspolicy.SavingsPolicyCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingspolicy.SavingsPolicyResultDTO;
import zw.co.mynhaka.policyservice.domain.model.SavingsPlan;
import zw.co.mynhaka.policyservice.domain.model.SavingsPolicy;
import zw.co.mynhaka.policyservice.service.utils.BusinessUtils;
import zw.co.mynhaka.policyservice.service.utils.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavingsPolicyMapper {

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applicationForm_url", source = "savingsPolicyCreateDTO.formLocation")
    @Mapping(target = "policyNumber", expression = "java(SavingsPolicyMapper.policyNumberGenerator())")
    SavingsPolicy toSavingsPolicy(SavingsPolicyCreateDTO savingsPolicyCreateDTO, SavingsPlan savingsPlan);

    @Mapping(target = "formLocation", source = "applicationForm_url")
    @Mapping(target = "monthlyInvestmentPremium", source = "savingsPolicy.savingsPlan.monthlyInvestmentPremium")
    @Mapping(target = "premiumWaiver", source = "savingsPolicy.savingsPlan.premiumWaiver")
    @Mapping(target = "savingsPlanId", source = "savingsPolicy.savingsPlan.id")
    SavingsPolicyResultDTO toSavingsPolicyResultDTO(SavingsPolicy savingsPolicy);

    static String policyNumberGenerator() {
        return BusinessUtils.generatePolicyNumber();
    }
}
