package zw.co.mynhaka.polad.service.mapper.agent;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentCreateDTO;
import zw.co.mynhaka.polad.domain.model.Agent;


@Component
public class AgentCreateDtoToAgent implements Converter<AgentCreateDTO, Agent> {
    @Override
    public Agent convert(AgentCreateDTO agentCreateDTO) {
        Agent agent = new Agent();
        agent.setName(agentCreateDTO.getName());
        agent.setSurname(agentCreateDTO.getSurname());
        agent.setEmail(agentCreateDTO.getEmail());
        return agent;
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
