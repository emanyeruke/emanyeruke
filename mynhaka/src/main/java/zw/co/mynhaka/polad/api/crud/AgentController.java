package zw.co.mynhaka.polad.api.crud;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentUpdateDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.service.iface.AgentService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/agent", produces = MediaType.APPLICATION_JSON_VALUE)

@Slf4j
public class AgentController {

    private final AgentService agentService;

    public AgentController(final AgentService agentService) {
        this.agentService = agentService;
    }

    //@ViewAgent
//    @GetMapping("get-all-agent/{page}/{size}")
//    public ResponseEntity<Page<AgentResultDTO>> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
//        return ResponseEntity.ok(agentService.findAll(PageRequest.of(page, size)));
//    }

    //@ViewAgent
    @GetMapping("agents")
    public ResponseEntity<List<AgentResultDTO>> findAll() {
        return ResponseEntity.ok(agentService.findAll());
    }

    //@CreateAgent
    @PostMapping("add-agent")
    public ResponseEntity<AgentResultDTO> create(@Valid @RequestBody AgentCreateDTO agentCreateDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(agentService.save(agentCreateDTO));
    }

    //@PostMapping("faker-agent")
    public ResponseEntity createFaker() {
        agentService.fakerAgent();
        return ResponseEntity.ok().build();
    }

    //@UpdateAgent
    @PutMapping("agent")
    public ResponseEntity<AgentResultDTO> edit(@Valid @RequestBody AgentUpdateDTO updateDTO) {
        return ResponseEntity.ok(agentService.save(updateDTO));
    }

    //@ViewAgent
    @GetMapping("get-agent/{id}")
    public ResponseEntity<AgentResultDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agentService.findById(id));
    }

    //@ViewAgent
    //@ViewPolicy
    @GetMapping("get-agent/accident/{id}")
    public ResponseEntity<List<PolicyAccidentResultDTO>> findAgentAllAccidentPolicies(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agentService.findAllAccidentPolicies(id));
    }

    //@ViewAgent
    //@ViewPolicy
    @GetMapping("get-agent/comprehensive/{id}")
    public ResponseEntity<List<PolicyComprehensiveResultDTO>> findAgentAllComprehensivePolicies(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agentService.findAllComprehensivePolicies(id));
    }

    //@ViewAgent
    //@ViewPolicy
    @GetMapping("get-agent/funeral/{id}")
    public ResponseEntity<List<PolicyFuneralResultDTO>> findAgentAllFuneralPolicies(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agentService.findAllFuneralPolicies(id));
    }

    //@ViewAgent
    //@ViewPolicy
    @GetMapping("get-agent/savings/{id}")
    public ResponseEntity<List<PolicySavingsResultDTO>> findAgentAllSavingsPolicies(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agentService.findAllSavingsPolicies(id));
    }

    //@DeleteAgent
    @DeleteMapping("delete-agent/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        agentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
