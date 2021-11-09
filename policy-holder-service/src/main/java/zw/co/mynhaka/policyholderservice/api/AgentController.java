package zw.co.mynhaka.policyholderservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyholderservice.audit.Audit;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentUpdateDTO;
import zw.co.mynhaka.policyholderservice.service.AgentService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/agent", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgentController {

    private final AgentService agentService;

    @PostMapping("create-agent")
    @Audit(resource = "Agent", action = "Create Agent")
    public ResponseEntity<AgentResultDTO> createAgent (
            @RequestBody @Valid AgentCreateDTO agentCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(agentService.save(agentCreateDTO));
    }

    @PutMapping("edit-agent")
    @Audit(resource = "Agent", action = "Edit Agent")
    public ResponseEntity<AgentResultDTO> editAgent (
            @RequestBody @Valid AgentUpdateDTO agentUpdateDTO
    ) {
        return ResponseEntity.ok(agentService.save(agentUpdateDTO));
    }

    @GetMapping("get-all-agents")
    @Audit(resource = "Agent", action = "Request Get all agents")
    public ResponseEntity<List<AgentResultDTO>> getAllAgents() {
        return ResponseEntity.ok(agentService.findAll());
    }

    @GetMapping("get-all-agents/{page}/{size}")
    public ResponseEntity<Page<AgentResultDTO>> getAllAgents(
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(agentService.findAll(page, size));
    }

    @GetMapping("get-agent/{id}")
    @Audit(resource = "Agent", action = "Request to get agent")
    public ResponseEntity<AgentResultDTO> getAgent(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(agentService.findById(id));
    }

    @DeleteMapping("delete-agent/{id}")
    @Audit(resource = "Agent", action = "Request To delete agent")
    public ResponseEntity<?> deleteAgent(
            @PathVariable("id") Long id
    ) {
        agentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
