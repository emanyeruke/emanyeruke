package zw.co.mynhaka.policyservice.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyservice.audit.Audit;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanUpdateDTO;
import zw.co.mynhaka.policyservice.service.StandardFuneralPlanService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/standard-funeral-plan", produces = MediaType.APPLICATION_JSON_VALUE)
public class StandardFuneralPlanController {

    private final StandardFuneralPlanService standardFuneralPlanService;

    @PostMapping("create-standard-funeral-plan")
    @Audit(resource = "Funeral Plan", action = "Create Standard Funeral Plan")
    public ResponseEntity<StandardFuneralPlanResultDTO> create (
            @RequestBody @Valid StandardFuneralPlanCreateDTO standardFuneralPlanCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(standardFuneralPlanService.save(standardFuneralPlanCreateDTO));
    }

    @PostMapping("create-standard-funeral-plan-reverse")
    @Audit(resource = "Funeral Plan", action = "Create Standard Funeral Plan on the fly")
    public ResponseEntity<StandardFuneralPlanResultDTO> create (
            @RequestBody @Valid StandardFuneralPlanReverseCreateDTO standardFuneralPlanReverseCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(standardFuneralPlanService.saveReverse(standardFuneralPlanReverseCreateDTO));
    }

    @PutMapping("edit-standard-funeral-plan")
    @Audit(resource = "Funeral Plan", action = "Update Standard Funeral Plan")
    public ResponseEntity<StandardFuneralPlanResultDTO> edit (
            @RequestBody @Valid StandardFuneralPlanUpdateDTO standardFuneralPlanUpdateDTO
    ) {
        return ResponseEntity.ok(standardFuneralPlanService.save(standardFuneralPlanUpdateDTO));
    }

    @GetMapping("get-standard-funeral-plan/{id}")
    @Audit(resource = "Funeral Plan", action = "View Standard Funeral Plan")
    public ResponseEntity<StandardFuneralPlanResultDTO> get (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(standardFuneralPlanService.findById(id));
    }

    @GetMapping("get-all-standard-funeral-plans")
    @Audit(resource = "Funeral Plan", action = "View Standard Funeral Plans")
    public ResponseEntity<List<StandardFuneralPlanResultDTO>> getAll () {
        return ResponseEntity.ok(standardFuneralPlanService.findAll());
    }

    @DeleteMapping("delete-standard-funeral-plan/{id}")
    @Audit(resource = "Funeral Plan", action = "Delete Standard Funeral Plan")
    public ResponseEntity<?> delete (
            @PathVariable("id") Long id
    ) {
        standardFuneralPlanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
