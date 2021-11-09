package zw.co.mynhaka.policyholderservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyholderservice.audit.Audit;
import zw.co.mynhaka.policyholderservice.domain.dto.AgentResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.ManagerUpdateDTO;
import zw.co.mynhaka.policyholderservice.service.ManagerService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/manager", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("create-manager")
    @Audit(resource = "Manager", action = "Create Manager")
    public ResponseEntity<ManagerResultDTO> createManager (
            @RequestBody @Valid ManagerCreateDTO managerCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(managerService.save(managerCreateDTO));
    }

    @PutMapping("edit-manager")
    @Audit(resource = "Manager", action = "Edit Manager")
    public ResponseEntity<ManagerResultDTO> editManager (
            @RequestBody @Valid ManagerUpdateDTO managerUpdateDTO
    ) {
        return ResponseEntity.ok(managerService.save(managerUpdateDTO));
    }

    @GetMapping("get-manager/{id}")
    @Audit(resource = "Manager", action = "View Manager")
    public ResponseEntity<ManagerResultDTO> getManager(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(managerService.findById(id));
    }

    @GetMapping("get-all-managers")
    @Audit(resource = "Manager", action = "View Managers")
    public ResponseEntity<List<ManagerResultDTO>> getAllManagers() {
        return ResponseEntity.ok(managerService.findAll());
    }

    @GetMapping("get-all-managers/{page}/{size}")
    @Audit(resource = "Manager", action = "View Managers")
    public ResponseEntity<Page<ManagerResultDTO>> getAllManagers(
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(managerService.findAll(page, size));
    }

    @GetMapping("get-all-agents/{managerId}/agents")
    @Audit(resource = "Manager", action = "View Manager for Agents")
    public ResponseEntity<List<AgentResultDTO>> getAllAgentsByManager(
            @PathVariable("managerId") Long managerId
    ) {
        return ResponseEntity.ok(managerService.findAllAgents(managerId));
    }

    @GetMapping("get-all-agents/{managerId}/agents/{page}/{size}")
    @Audit(resource = "Manager", action = "View Manager for Agents")
    public ResponseEntity<Page<AgentResultDTO>> getAllAgentsByManager(
            @PathVariable("managerId") Long managerId,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(managerService.findAllAgents(managerId, page, size));
    }

    @DeleteMapping("delete-manager/{id}")
    @Audit(resource = "Manager", action = "Delete manager")
    public ResponseEntity<?> deleteAgent(
            @PathVariable("id") Long id
    ) {
        managerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
