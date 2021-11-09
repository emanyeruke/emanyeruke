package zw.co.mynhaka.policyholderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Manager;
import zw.co.mynhaka.policyholderservice.repository.AgentRepository;
import zw.co.mynhaka.policyholderservice.repository.ManagerRepository;
import zw.co.mynhaka.policyholderservice.service.ManagerService;
import zw.co.mynhaka.policyholderservice.service.mapper.AgentMapper;
import zw.co.mynhaka.policyholderservice.service.mapper.ManagerMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;

    @Override
    public List<ManagerResultDTO> findAll() {
        return managerRepository.findAll().stream()
                .map(managerMapper::toManagerResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ManagerResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return managerRepository.findAll(pageRequest)
                .map(managerMapper::toManagerResultDTO);
    }

    @Override
    public List<AgentResultDTO> findAllAgents(Long id) {
        Manager manager = getOne(id);
        return agentRepository.findAllByManager(manager).stream()
                .map(agentMapper::toAgentResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AgentResultDTO> findAllAgents(Long id, int page, int size) {
        Manager manager = getOne(id);
        PageRequest pageRequest = PageRequest.of(page, size);
        return agentRepository.findAllByManager(manager, pageRequest)
                .map(agentMapper::toAgentResultDTO);
    }

    @Override
    public ManagerResultDTO findById(Long id) {
        return managerMapper.toManagerResultDTO(getOne(id));
    }

    @Override
    public ManagerResultDTO save(ManagerCreateDTO managerCreateDTO) {
        Manager manager = managerMapper.toManager(managerCreateDTO);
        return managerMapper.toManagerResultDTO(managerRepository.save(manager));
    }

    @Override
    public ManagerResultDTO save(ManagerUpdateDTO managerUpdateDTO) {
        Manager savedManager = getOne(managerUpdateDTO.getManagerId());
        managerMapper.updateManager(savedManager, managerUpdateDTO);
        return managerMapper.toManagerResultDTO(managerRepository.save(savedManager));
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public Manager getOne(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Manager with id: " + id + " not found"));
    }

    @Override
    public void fakerManager() {

    }
}

