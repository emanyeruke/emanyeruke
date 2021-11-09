package zw.co.mynhaka.policyholderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Agent;
import zw.co.mynhaka.policyholderservice.domain.models.Manager;
import zw.co.mynhaka.policyholderservice.repository.AgentRepository;
import zw.co.mynhaka.policyholderservice.service.AgentService;
import zw.co.mynhaka.policyholderservice.service.ManagerService;
import zw.co.mynhaka.policyholderservice.service.mapper.AgentMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final ManagerService managerService;
    private final AgentMapper agentMapper;

    @Override
    public List<AgentResultDTO> findAll() {
        return agentRepository.findAll().stream()
                .map(agentMapper::toAgentResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AgentResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return agentRepository.findAll(pageRequest)
                .map(agentMapper::toAgentResultDTO);
    }

    @Override
    public AgentResultDTO findById(Long id) {
        return agentMapper.toAgentResultDTO(getOne(id));
    }

    @Override
    public AgentResultDTO save(AgentCreateDTO agentCreateDTO) {
        Manager manager = managerService.getOne(agentCreateDTO.getManagerId());

        Agent agent = agentMapper.toAgent(agentCreateDTO);
        agent.setManager(manager);

        return agentMapper.toAgentResultDTO(agentRepository.save(agent));
    }

    @Override
    public AgentResultDTO save(AgentUpdateDTO agentUpdateDTO) {
        Agent savedAgent = getOne(agentUpdateDTO.getAgentId());

        agentMapper.updateAgent(savedAgent, agentUpdateDTO);

        if (!savedAgent.getManager().getId().equals(agentUpdateDTO.getManagerId())) {
            Manager manager = managerService.getOne(agentUpdateDTO.getManagerId());
            savedAgent.setManager(manager);
        }

        return agentMapper.toAgentResultDTO(agentRepository.save(savedAgent));
    }

    @Override
    public void deleteById(Long id) {
        agentRepository.deleteById(id);
    }

    @Override
    public Agent getOne(Long id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent with id: " + id + " not found"));
    }

    @Override
    public void fakerAgent() {

    }
}
