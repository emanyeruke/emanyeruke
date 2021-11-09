package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsCreateDto;
import zw.co.mynhaka.polad.domain.model.Agent;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.domain.model.SavingsProduct;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;
import zw.co.mynhaka.polad.service.util.OtherUtils;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PolicySavingsMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "agent", source = "agent")
    @Mapping(target = "policyHolder", source = "policyHolder")
    @Mapping(target = "policyNumber", expression = "java(generatePolicyNumber())")
    @Mapping(target = "nextInvoicingDate", source = "policySavingsCreateDto.commencementDate", qualifiedByName = "nextInvoicingDate")
    @Mapping(target = "applicationForm_url", source = "policySavingsCreateDto.applicationForm_url")
    PolicySavings toPolicySavings(PolicySavingsCreateDto policySavingsCreateDto, PolicyHolder policyHolder, SavingsProduct savingsProduct, Agent agent);

    @Named("generatePolicyNumber")
    default String generatePolicyNumber() {
        return OtherUtils.generatePolicyNumberForPrincipal();
    }

    @Named("nextInvoicingDate")
    default LocalDate nextInvoicingDate(LocalDate commencementDate) {
        return commencementDate.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
    }
}
