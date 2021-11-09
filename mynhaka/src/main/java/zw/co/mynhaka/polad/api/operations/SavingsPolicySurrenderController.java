package zw.co.mynhaka.polad.api.operations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.audit.Audit;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderResultDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderUpdateDTO;
import zw.co.mynhaka.polad.service.iface.SavingsSurrenderService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/savings/surrenders", produces = MediaType.APPLICATION_JSON_VALUE)
public class SavingsPolicySurrenderController {

    private final SavingsSurrenderService savingsSurrenderService;

    @PostMapping("{policyNumber}")
    @Audit(resource = "Savings Policy", action = "Submit Surrender Savings Policy")
    public ResponseEntity<SavingsSurrenderResultDTO> create(
            @PathVariable("policyNumber") String policyNumber,
            @RequestBody @Valid SavingsSurrenderCreateDTO savingsSurrenderCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(savingsSurrenderService.save(policyNumber, savingsSurrenderCreateDTO));
    }

    @PutMapping
    @Audit(resource = "Savings Policy", action = "Approve surrender Savings Policy")
    public ResponseEntity<List<SavingsSurrenderResultDTO>> approve(
            @RequestBody @Valid List<SavingsSurrenderUpdateDTO> savingsSurrenderUpdateDTOList
    ) {
        return ResponseEntity.ok(savingsSurrenderService.save(savingsSurrenderUpdateDTOList));
    }

    @GetMapping("{page}/{size}")
    @Audit(resource = "Savings Policy", action = "Get All surrendered Savings Policy")
    public ResponseEntity<Page<SavingsSurrenderResultDTO>> findAll(
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(savingsSurrenderService.findAll(page, size));
    }

    @DeleteMapping("{id}")
    @Audit(resource = "Savings Policy", action = "Delete surrender Savings Policy")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id
    ) {
        savingsSurrenderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
