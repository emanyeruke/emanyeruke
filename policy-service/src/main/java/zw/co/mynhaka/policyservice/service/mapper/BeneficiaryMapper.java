package zw.co.mynhaka.policyservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyservice.domain.dto.beneficiary.BeneficiaryCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.beneficiary.BeneficiaryResultDTO;
import zw.co.mynhaka.policyservice.domain.model.Beneficiary;
import zw.co.mynhaka.policyservice.domain.model.Policy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BeneficiaryMapper {
    @Mapping(target = "id", ignore = true)
    Beneficiary toBeneficiary(BeneficiaryCreateDTO beneficiaryCreateDTO, Policy policy);

    @Mapping(target = "savingsPolicyId", source = "beneficiary.policy.id")
    @Mapping(target = "policyNumber", source = "beneficiary.policy.policyNumber")
    @Mapping(target = "beneficiaryId", source = "beneficiary.id")
    BeneficiaryResultDTO toBeneficiaryResultDTO(Beneficiary beneficiary);
}
