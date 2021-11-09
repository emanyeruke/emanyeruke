package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.PolicyResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.*;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;
import zw.co.mynhaka.polad.service.util.OtherUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PolicyMapper {

    @Mapping(target = "agent", expression = "java(policyAccident.getAgent().getName() + \" \" + policyAccident.getAgent().getSurname())")
    @Mapping(target = "accidentProductId", source = "policyAccident.accidentProduct.id")
    @Mapping(target = "accidentProductName", source = "policyAccident.accidentProduct.name")
    @Mapping(target = "id", source = "policyAccident.id")
    @Mapping(target = "policyHolderFirstName", source = "policyAccident.policyHolder.firstname")
    @Mapping(target = "policyHolderLastName", source = "policyAccident.policyHolder.lastname")
    @Mapping(target = "sumAssured", source = "policyAccident.accidentProduct.sumAssured")
    @Mapping(target = "premium", source = "policyAccident.accidentProduct.premium")
    @Mapping(target = "policyHolderId", source = "policyAccident.policyHolder.id")
    PolicyAccidentResultDTO toPolicyAccidentResultDTO(PolicyAccident policyAccident);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accidentProduct", source = "accidentProduct")
    @Mapping(target = "policyHolder", source = "policyHolder")
    @Mapping(target = "applicationForm_url", source = "policyAccidentCreateDto.applicationForm_url")
    @Mapping(target = "agent", source = "agent")
    @Mapping(target = "policyNumber", source = "policyAccidentCreateDto.policyHolderId", qualifiedByName = "policyNumberGenerator")
    PolicyAccident fromPolicyAccidentCreateDto(PolicyAccidentCreateDto policyAccidentCreateDto, AccidentProduct accidentProduct, PolicyHolder policyHolder, Agent agent);

    @Mapping(target = "agent", expression = "java(policyFuneral.getAgent().getName() + \" \" + policyFuneral.getAgent().getSurname())")
    PolicyFuneralResultDTO toPolicyFuneralResultDTO(PolicyFuneral policyFuneral);

    @Mapping(target = "agent", expression = "java(policyComprehensive.getAgent().getName() + \" \" + policyComprehensive.getAgent().getSurname())")
    PolicyComprehensiveResultDTO toPolicyComprehensiveResultDTO(PolicyComprehensive policyComprehensive);

    @Mapping(target = "agent", expression = "java(policySavings.getAgent().getName() + \" \" + policySavings.getAgent().getSurname())")
    PolicySavingsResultDTO toPolicySavingsResultDTO(PolicySavings policySavings);

    @Mapping(target = "agentId", source = "policy.agent.id")
    @Mapping(target = "policyHolderId", source = "policy.policyHolder.id")
    PolicyResultDTO toPolicyResultDTO(Policy policy);

    @Named("policyNumberGenerator")
    default String getPolicyNumber(Long policyHolderId) {
        return OtherUtils.generatePolicyNumberForPrincipal();
    }
}
