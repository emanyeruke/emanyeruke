package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.agent.AgentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentUpdateDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.model.Agent;

import java.util.List;

public interface AgentService {

    List<AgentResultDTO> findAll();

    //Page<AgentResultDTO> findAll(Pageable pageable);

    AgentResultDTO findById(Long id);

    AgentResultDTO save(AgentCreateDTO agentCreateDTO);

    AgentResultDTO save(AgentUpdateDTO agentUpdateDTO);

    void deleteById(Long id);

    Agent getOne(Long id);

    void fakerAgent();

    List<PolicyAccidentResultDTO> findAllAccidentPolicies(Long id);

    List<PolicyComprehensiveResultDTO> findAllComprehensivePolicies(Long id);

    List<PolicyFuneralResultDTO> findAllFuneralPolicies(Long id);

    List<PolicySavingsResultDTO> findAllSavingsPolicies(Long id);
}
