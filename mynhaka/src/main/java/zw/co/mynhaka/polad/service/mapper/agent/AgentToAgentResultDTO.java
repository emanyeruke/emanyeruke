package zw.co.mynhaka.polad.service.mapper.agent;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.agent.ManagerAgentResultDTO;
import zw.co.mynhaka.polad.domain.model.Agent;


@Component
public class AgentToAgentResultDTO implements Converter<Agent, AgentResultDTO> {
    @Override
    public AgentResultDTO convert(Agent agent) {
        AgentResultDTO resultDTO = new AgentResultDTO();
        ManagerAgentResultDTO managerAgentResultDTO = new ManagerAgentResultDTO();
        resultDTO.setEmail(agent.getEmail());
        resultDTO.setId(agent.getId());
        resultDTO.setName(agent.getName());
        resultDTO.setChannel(agent.getChannel().toString());
        resultDTO.setSurname(agent.getSurname());
        resultDTO.setPosition(agent.getPosition().toString());
        resultDTO.setTeam(agent.getTeam().toString());
        resultDTO.setContactNumber(agent.getContactNumber());

        managerAgentResultDTO.setId(agent.getManager().getId());
        managerAgentResultDTO.setName(agent.getManager().getName());
        managerAgentResultDTO.setSurname(agent.getManager().getSurname());
        managerAgentResultDTO.setEmail(agent.getManager().getEmail());
        resultDTO.setManagerAgentResultDTO(managerAgentResultDTO);
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
