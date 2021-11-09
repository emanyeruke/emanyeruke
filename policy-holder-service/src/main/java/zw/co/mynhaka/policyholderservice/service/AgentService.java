package zw.co.mynhaka.policyholderservice.service;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Agent;

import java.util.List;

public interface AgentService {
    List<AgentResultDTO> findAll();

    Page<AgentResultDTO> findAll(int page, int size);

    AgentResultDTO findById(Long id);

    AgentResultDTO save(AgentCreateDTO agentCreateDTO);

    AgentResultDTO save(AgentUpdateDTO agentUpdateDTO);

    void deleteById(Long id);

    Agent getOne(Long id);

    void fakerAgent();
}
