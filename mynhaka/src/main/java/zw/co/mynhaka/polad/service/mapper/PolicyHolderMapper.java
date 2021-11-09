package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderUpdateDto;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring")
public interface PolicyHolderMapper {
    @IgnoreAuditing
    @Mapping(target = "idNumber", expression = "java(policyHolderCreateDto.getIdNumber().replace(\"-\", \"\"))")
    PolicyHolder toPolicyHolder(PolicyHolderCreateDto policyHolderCreateDto);

    PolicyHolderResultDTO toPolicyHolderResultDTO(PolicyHolder policyHolder);

    @IgnoreAuditing
    void updatePolicyHolder(@MappingTarget PolicyHolder policyHolder, PolicyHolderUpdateDto policyHolderUpdateDto);
}
