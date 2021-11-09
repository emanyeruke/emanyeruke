package zw.co.mynhaka.policyholderservice.service.mapper;

import org.mapstruct.*;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.PolicyHolder;
import zw.co.mynhaka.policyholderservice.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PolicyHolderMapper {
    PolicyHolderResultDTO toPolicyHolderResultDTO(PolicyHolder policyHolder);

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idNumber", source = "idNumber", qualifiedByName = "idNumberFormatter")
    PolicyHolder toPolicyHolder(PolicyHolderCreateDTO policyHolderCreateDTO);

    void updatePolicyHolder(@MappingTarget PolicyHolder policyHolder, PolicyHolderUpdateDTO policyHolderUpdateDTO);

    @Named("idNumberFormatter")
    static String idNumberFormatter(String idNumber) {
        return idNumber.toUpperCase().replace("-","");
    }
}
