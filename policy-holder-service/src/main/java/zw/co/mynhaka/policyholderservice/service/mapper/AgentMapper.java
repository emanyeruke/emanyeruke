package zw.co.mynhaka.policyholderservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Agent;
import zw.co.mynhaka.policyholderservice.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgentMapper {
    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    Agent toAgent(AgentCreateDTO agentCreateDTO);

    AgentResultDTO toAgentResultDTO(Agent agent);

    void updateAgent(@MappingTarget Agent agent, AgentUpdateDTO agentUpdateDTO);
}
