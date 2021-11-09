package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryResultDTO;
import zw.co.mynhaka.polad.domain.dtos.BeneficiaryUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Beneficiary;
import zw.co.mynhaka.polad.domain.model.Policy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BeneficiaryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "policy", source = "policy")
    Beneficiary toBeneficiary(BeneficiaryCreateDTO beneficiaryCreateDTO, Policy policy);

    @Mapping(target = "policyId", source = "beneficiary.policy.id")
    @Mapping(target = "policyNumber", source = "beneficiary.policy.policyNumber")
    @Mapping(target = "beneficiaryId", source = "beneficiary.id")
    @Mapping(target = "policyType", source = "policy.policyType")
    BeneficiaryResultDTO toBeneficiaryResultDTO(Beneficiary beneficiary);

    @Mapping(target = "policy", source = "policy")
    void updateBeneficiary(@MappingTarget Beneficiary beneficiary, Policy policy, BeneficiaryUpdateDTO beneficiaryUpdateDTO);
}
