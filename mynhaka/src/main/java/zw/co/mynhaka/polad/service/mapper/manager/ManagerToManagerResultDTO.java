package zw.co.mynhaka.polad.service.mapper.manager;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerResultDTO;
import zw.co.mynhaka.polad.domain.model.Manager;
import zw.co.mynhaka.polad.service.mapper.agent.AgentToAgentResultDTO;


@Component
public class ManagerToManagerResultDTO implements Converter<Manager, ManagerResultDTO> {
    private final AgentToAgentResultDTO agentToAgentResultDTO;

    public ManagerToManagerResultDTO(final AgentToAgentResultDTO agentToAgentResultDTO) {
        this.agentToAgentResultDTO = agentToAgentResultDTO;
    }

    @Override
    public ManagerResultDTO convert(Manager manager) {
        ManagerResultDTO resultDTO = new ManagerResultDTO();
        resultDTO.setEmail(manager.getEmail());
        resultDTO.setId(manager.getId());
        resultDTO.setName(manager.getName());
        resultDTO.setSurname(manager.getSurname());
        return resultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
