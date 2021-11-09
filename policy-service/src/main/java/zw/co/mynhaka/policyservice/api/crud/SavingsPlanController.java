package zw.co.mynhaka.policyservice.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyservice.audit.Audit;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanUpdateDTO;
import zw.co.mynhaka.policyservice.service.SavingsPlanService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/savings-plan", produces = MediaType.APPLICATION_JSON_VALUE)
public class SavingsPlanController {

    private final SavingsPlanService savingsPlanService;

    @PostMapping("create-savings-plan")
    @Audit(resource = "Savings", action = "Create Savings plan")
    public ResponseEntity<SavingsPlanResultDTO> create (
            @RequestBody @Valid SavingsPlanCreateDTO savingsPlanCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(savingsPlanService.save(savingsPlanCreateDTO));
    }

    @PostMapping("create-savings-plan-reverse")
    @Audit(resource = "Savings", action = "Create Savings plan on the fly")
    public ResponseEntity<SavingsPlanResultDTO> create (
            @RequestBody @Valid SavingsPlanReverseCreateDTO savingsPlanReverseCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(savingsPlanService.saveReverse(savingsPlanReverseCreateDTO));
    }

    @PutMapping("edit-savings-plan")
    @Audit(resource = "Savings", action = "Edit Savings plan")
    public ResponseEntity<SavingsPlanResultDTO> edit (
            @RequestBody @Valid SavingsPlanUpdateDTO savingsPlanUpdateDTO
    ) {
        return ResponseEntity.ok(savingsPlanService.save(savingsPlanUpdateDTO));
    }

    @GetMapping("get-savings-plan/{id}")
    @Audit(resource = "Savings", action = "Get Savings plan")
    public ResponseEntity<SavingsPlanResultDTO> get (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(savingsPlanService.findById(id));
    }

    @GetMapping("get-all-savings-plans")
    @Audit(resource = "Savings", action = "Create Savings plans")
    public ResponseEntity<List<SavingsPlanResultDTO>> get () {
        return ResponseEntity.ok(savingsPlanService.findAll());
    }

    @DeleteMapping("delete-savings-plan/{id}")
    @Audit(resource = "Savings", action = "Delete Savings plan")
    public ResponseEntity<?> delete (
            @PathVariable("id") Long id
    ) {
        savingsPlanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
