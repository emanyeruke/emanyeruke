package zw.co.mynhaka.policyholderservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Manager;
import zw.co.mynhaka.policyholderservice.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManagerMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    Manager toManager(ManagerCreateDTO managerCreateDTO);

    ManagerResultDTO toManagerResultDTO(Manager manager);

    void updateManager(@MappingTarget Manager manager, ManagerUpdateDTO managerUpdateDTO);
}
