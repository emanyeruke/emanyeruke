package zw.co.mynhaka.policyservice.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyservice.audit.Audit;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanUpdateDTO;
import zw.co.mynhaka.policyservice.service.ComprehensiveFuneralPlanService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/comprehensive-funeral-plan", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComprehensiveFuneralPlanController {

    private final ComprehensiveFuneralPlanService comprehensiveFuneralPlanService;

    @PostMapping("create-comprehensive-funeral-plan")
    @Audit(resource = "Comprehensive", action = "Create Comprehensive plan")
    public ResponseEntity<ComprehensiveFuneralPlanResultDTO> create(
            @RequestBody @Valid ComprehensiveFuneralPlanCreateDTO comprehensiveFuneralPlanCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(comprehensiveFuneralPlanService.save(comprehensiveFuneralPlanCreateDTO));
    }

    @PostMapping("create-comprehensive-funeral-plan-reverse")
    @Audit(resource = "Comprehensive", action = "Create comprehensive plan on the fly")
    public ResponseEntity<ComprehensiveFuneralPlanResultDTO> create(
            @RequestBody @Valid ComprehensiveFuneralPlanReverseCreateDTO comprehensiveFuneralPlanReverseCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(comprehensiveFuneralPlanService.saveReverse(comprehensiveFuneralPlanReverseCreateDTO));
    }

    @PutMapping("edit-comprehensive-funeral-plan")
    @Audit(resource = "Comprehensive", action = "Update comprehensive funeral plan")
    public ResponseEntity<ComprehensiveFuneralPlanResultDTO> edit (
            @RequestBody @Valid ComprehensiveFuneralPlanUpdateDTO comprehensiveFuneralPlanUpdateDTO
    ) {
        return ResponseEntity.ok(comprehensiveFuneralPlanService.save(comprehensiveFuneralPlanUpdateDTO));
    }

    @GetMapping("get-comprehensive-funeral-plan/{id}")
    @Audit(resource = "Comprehensive", action = "Get comprehensive plan")
    public ResponseEntity<ComprehensiveFuneralPlanResultDTO> get (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(comprehensiveFuneralPlanService.findById(id));
    }

    @GetMapping("get-comprehensive-funeral-plans")
    @Audit(resource = "Comprehensive", action = "Get comprehensive plans")
    public ResponseEntity<List<ComprehensiveFuneralPlanResultDTO>> getAll() {
        return ResponseEntity.ok(comprehensiveFuneralPlanService.findAll());
    }

    @DeleteMapping("delete-comprehensive-funeral-plan/{id}")
    @Audit(resource = "Comprehensive", action = "Delete comprehensive plan")
    public ResponseEntity<?> delete (
            @PathVariable("id") Long id
    ) {
        comprehensiveFuneralPlanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
