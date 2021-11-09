package zw.co.mynhaka.polad.domain.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerResultDTO;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Manager;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManagerMapper {
    @IgnoreAuditing
    Manager toManager(ManagerCreateDTO managerCreateDTO);

    ManagerResultDTO toManagerResultDTO(Manager manager);

    void updateManager(@MappingTarget Manager manager, ManagerUpdateDTO managerUpdateDTO);
}
