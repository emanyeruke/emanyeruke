package zw.co.mynhaka.polad.api.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.audit.Audit;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerResultDTO;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerUpdateDTO;
import zw.co.mynhaka.polad.permissions.manager.ViewManager;
import zw.co.mynhaka.polad.service.iface.ManagerService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/manager", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j

public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(final ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("managers/{page}/{size}")
    @ViewManager
    @Audit(resource = "View Manager", action = "View Managers")
    public ResponseEntity<Page<ManagerResultDTO>> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
        return ResponseEntity.ok(managerService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("managers")
    public ResponseEntity<List<ManagerResultDTO>> findAll() {
        return ResponseEntity.ok(managerService.findAll());
    }

    @PostMapping("manager")
    public ResponseEntity<ManagerResultDTO> create(@Valid @RequestBody ManagerCreateDTO managerCreateDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(managerService.save(managerCreateDTO));
    }

    @PostMapping("faker-manager")
    public ResponseEntity createFaker() {
        managerService.fakerManager();
        return ResponseEntity.ok().build();
    }

    @PutMapping("manager")
    public ResponseEntity<ManagerResultDTO> edit(@Valid @RequestBody ManagerUpdateDTO managerUpdateDTO) {
        return ResponseEntity.ok(managerService.save(managerUpdateDTO));
    }

    @GetMapping("manager/{id}")
    public ResponseEntity<ManagerResultDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(managerService.findById(id));
    }

    @DeleteMapping("manager/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        managerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

