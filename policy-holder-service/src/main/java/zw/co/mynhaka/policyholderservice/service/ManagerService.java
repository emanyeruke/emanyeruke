package zw.co.mynhaka.policyholderservice.service;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Manager;

import java.util.List;

public interface ManagerService {
    List<ManagerResultDTO> findAll();

    Page<ManagerResultDTO> findAll(int page, int size);

    List<AgentResultDTO> findAllAgents(Long id);

    Page<AgentResultDTO> findAllAgents(Long id, int page, int size);

    ManagerResultDTO findById(Long id);

    ManagerResultDTO save(ManagerCreateDTO managerCreateDTO);

    ManagerResultDTO save(ManagerUpdateDTO managerUpdateDTO);

    void deleteById(Long id);

    Manager getOne(Long id);

    void fakerManager();
}
