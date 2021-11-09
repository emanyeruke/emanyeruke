package zw.co.mynhaka.policyservice.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyservice.audit.Audit;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanCreateReverse;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanUpdateDto;
import zw.co.mynhaka.policyservice.service.AccidentPlanService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/accident-plan", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidentPlanController {

    private final AccidentPlanService accidentPlanService;

    @PostMapping("create-accident-plan")
    @Audit(resource = "Accident", action = "Create Accident Plan")
    public ResponseEntity<AccidentPlanResultDTO> createAccidentPlan(
            @RequestBody @Valid AccidentPlanCreateDTO accidentPlanCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(accidentPlanService.save(accidentPlanCreateDTO));
    }

    @PostMapping("create-accident-plan-reverse")
    @Audit(resource = "Accident", action = "Create Accident plan on the fly")
    public ResponseEntity<AccidentPlanResultDTO> createAccidentPlan(
            @RequestBody @Valid AccidentPlanCreateReverse accidentPlanCreateReverse
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(accidentPlanService.saveReverse(accidentPlanCreateReverse));
    }

    @PutMapping("edit-product")
    @Audit(resource = "Accident", action = "Update Accident Plan")
    public ResponseEntity<AccidentPlanResultDTO> editAccidentPlan(
            @RequestBody @Valid AccidentPlanUpdateDto accidentPlanUpdateDto
    ) {
        return ResponseEntity.ok(accidentPlanService.save(accidentPlanUpdateDto));
    }

    @GetMapping("get-accident-plan/{id}")
    @Audit(resource = "Accident", action = "View Accident Plan")
    public ResponseEntity<AccidentPlanResultDTO> getAccidentPlan (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(accidentPlanService.findById(id));
    }

    @GetMapping("get-all-accident-plans")
    @Audit(resource = "Accident", action = "View all accident plans")
    public ResponseEntity<List<AccidentPlanResultDTO>> getAllAccidentPlans () {
        return ResponseEntity.ok(accidentPlanService.findAll());
    }

    @DeleteMapping("delete-accident-plan/{id}")
    @Audit(resource = "Accident", action = "Delete accident plan")
    public ResponseEntity<?> deleteAccidentPlan(
            @PathVariable("id") Long id
    ) {
        accidentPlanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
