package zw.co.mynhaka.polad.service.impl;


import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentUpdateDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.enums.Channel;
import zw.co.mynhaka.polad.domain.enums.Position;
import zw.co.mynhaka.polad.domain.enums.Team;
import zw.co.mynhaka.polad.domain.model.Agent;
import zw.co.mynhaka.polad.domain.model.Manager;
import zw.co.mynhaka.polad.repository.*;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.iface.AgentService;
import zw.co.mynhaka.polad.service.iface.ManagerService;
import zw.co.mynhaka.polad.service.mapper.agent.AgentToAgentResultDTO;
import zw.co.mynhaka.polad.service.mapper.policies.PolicyAccidentToPolicyAccidentDTO;
import zw.co.mynhaka.polad.service.mapper.policies.PolicyComprehensiveToPolicyComprehensiveDTO;
import zw.co.mynhaka.polad.service.mapper.policies.PolicyFuneralToPolicyFuneralDTO;
import zw.co.mynhaka.polad.service.mapper.policies.PolicySavingsToPolicySavingsDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AgentServiceImpl implements AgentService {
    private final AgentToAgentResultDTO toAgentResultDTO;
    private final AgentRepository agentRepository;
    private final ManagerService managerService;
    private final PolicyAccidentToPolicyAccidentDTO toPolicyAccidentDTO;
    private final PolicyComprehensiveToPolicyComprehensiveDTO toPolicyComprehensiveDTO;
    private final PolicyFuneralToPolicyFuneralDTO toPolicyFuneralDTO;
    private final PolicySavingsToPolicySavingsDTO toPolicySavingsDTO;
    private final PolicyAccidentRepository policyAccidentRepository;
    private final PolicySavingsRepository policySavingsRepository;
    private final PolicyFuneralRepository policyFuneralRepository;
    private final PolicyComprehensiveRepository policyComprehensiveRepository;


    public AgentServiceImpl(final AgentToAgentResultDTO toAgentResultDTO,
                            final AgentRepository agentRepository,
                            final ManagerService managerService, PolicyAccidentToPolicyAccidentDTO toPolicyAccidentDTO, PolicyComprehensiveToPolicyComprehensiveDTO toPolicyComprehensiveDTO, PolicyFuneralToPolicyFuneralDTO toPolicyFuneralDTO, PolicySavingsToPolicySavingsDTO toPolicySavingsDTO, PolicyAccidentRepository policyAccidentRepository, PolicySavingsRepository policySavingsRepository, PolicyFuneralRepository policyFuneralRepository, PolicyComprehensiveRepository policyComprehensiveRepository) {
        this.toAgentResultDTO = toAgentResultDTO;
        this.agentRepository = agentRepository;
        this.managerService = managerService;
        this.toPolicyAccidentDTO = toPolicyAccidentDTO;
        this.toPolicyComprehensiveDTO = toPolicyComprehensiveDTO;
        this.toPolicyFuneralDTO = toPolicyFuneralDTO;
        this.toPolicySavingsDTO = toPolicySavingsDTO;
        this.policyAccidentRepository = policyAccidentRepository;
        this.policySavingsRepository = policySavingsRepository;
        this.policyFuneralRepository = policyFuneralRepository;
        this.policyComprehensiveRepository = policyComprehensiveRepository;
    }


//    @Override
//    public Page<AgentResultDTO> findAll(Pageable pageable) {
//        return agentRepository.findAll(pageable)
//                .map(toAgentResultDTO::convert);
//    }

    @Override
    public List<AgentResultDTO> findAll() {
        return agentRepository.findAll().stream()
                .map(toAgentResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public AgentResultDTO findById(Long id) {
        return toAgentResultDTO.convert(agentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Agent not found")));
    }

    @Override
    public AgentResultDTO save(AgentCreateDTO agentCreateDTO) {
        Manager manager = managerService.getOne(agentCreateDTO.getManagerId());
        Agent agent = new Agent();
        agent.setName(agentCreateDTO.getName());
        agent.setSurname(agentCreateDTO.getSurname());
        agent.setEmail(agentCreateDTO.getEmail());
        agent.setManager(manager);
        agent.setChannel(agentCreateDTO.getChannel());
        agent.setContactNumber(agentCreateDTO.getContactNumber());
        agent.setPosition(agentCreateDTO.getPosition());
        agent.setTeam(agentCreateDTO.getTeam());
        return toAgentResultDTO.convert(agentRepository.save(agent));
    }

    @Override
    public AgentResultDTO save(AgentUpdateDTO agentUpdateDTO) {
        Manager manager = managerService.getOne(agentUpdateDTO.getManagerId());
        Agent agent = agentRepository.getOne(agentUpdateDTO.getAgentId());
        agent.setName(agentUpdateDTO.getName());
        agent.setSurname(agentUpdateDTO.getSurname());
        agent.setChannel(agentUpdateDTO.getChannel());
        agent.setEmail(agentUpdateDTO.getEmail());
        agent.setManager(manager);
        agent.setContactNumber(agentUpdateDTO.getContactNumber());
        agent.setPosition(agentUpdateDTO.getPosition());
        agent.setTeam(agentUpdateDTO.getTeam());
        return toAgentResultDTO.convert(agentRepository.save(agent));
    }

    @Override
    public void deleteById(Long id) {
        agentRepository.deleteById(id);
    }

    @Override
    public Agent getOne(Long id) {
        return agentRepository.getOne(id);
    }

    @Override
    public void fakerAgent() {

        List<Team> teams = Arrays.asList(Team.CENTRAL,
                Team.NORTH,
                Team.SOUTH);

        List<Channel> channels = Arrays.asList(Channel.FEET_ON_STREET,
                Channel.OVER_PHONE,
                Channel.VOICE);

        List<Position> positions = Arrays.asList(Position.AGENT,
                Position.AGENT_EXECUTIVE,
                Position.REGIONAL_MANAGER,
                Position.TEAM_LEADER);

        final Random random = new Random();
        for (int i = 0; i < 50; i++) {
            Agent agent = new Agent();
            Faker faker = new Faker();
            int managerNumber = 1 + random.nextInt(5000) % 5;
            log.info("Number found: " + managerNumber);
            Manager manager = managerService.getOne((long) managerNumber);
            agent.setName(faker.name().firstName());
            agent.setSurname(faker.name().lastName());
            agent.setEmail(faker.internet().emailAddress());
            agent.setContactNumber(faker.phoneNumber().cellPhone());
            agent.setManager(manager);
            agent.setPosition(positions.get(random.nextInt(positions.size() - 1)));
            agent.setTeam(teams.get(random.nextInt(teams.size() - 1)));
            agent.setChannel(channels.get(random.nextInt(channels.size() - 1)));
            agentRepository.save(agent);
        }
    }

    @Override
    public List<PolicyAccidentResultDTO> findAllAccidentPolicies(Long id) {
        return policyAccidentRepository.findAllByAgent_Id(id)
                .stream()
                .map(toPolicyAccidentDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyComprehensiveResultDTO> findAllComprehensivePolicies(Long id) {
        return policyComprehensiveRepository.findAllByAgent_Id(id)
                .stream()
                .map(toPolicyComprehensiveDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyFuneralResultDTO> findAllFuneralPolicies(Long id) {
        return policyFuneralRepository.findAllByAgent_Id(id)
                .stream()
                .map(toPolicyFuneralDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicySavingsResultDTO> findAllSavingsPolicies(Long id) {
        return policySavingsRepository.findAllByAgent_Id(id)
                .stream()
                .map(toPolicySavingsDTO::convert)
                .collect(Collectors.toList());
    }
}
