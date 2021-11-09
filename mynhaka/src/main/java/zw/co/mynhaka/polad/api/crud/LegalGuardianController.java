package zw.co.mynhaka.polad.api.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianResultDTO;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianUpdateDto;
import zw.co.mynhaka.polad.service.iface.LegalGuardianService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/legalguardian", produces = MediaType.APPLICATION_JSON_VALUE)

public class LegalGuardianController {

    private final LegalGuardianService legalGuardianService;

    public LegalGuardianController(LegalGuardianService legalGuardianService) {
        this.legalGuardianService = legalGuardianService;
    }

    @GetMapping("get-legal-guardians/{page}/{size}")
    public ResponseEntity<Page<LegalGuardianResultDTO>> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
        return ResponseEntity.ok(legalGuardianService.findAll(PageRequest.of(page, size)));
    }


    @GetMapping("/get-legal-guardian/{id}")
    public ResponseEntity<LegalGuardianResultDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(legalGuardianService.findById(id));
    }

    @PutMapping("/edit-legal-guardian")
    public ResponseEntity<LegalGuardianResultDTO> editLegalGuardian(@RequestBody @Valid LegalGuardianUpdateDto legalGuardianUpdateDto) {
        return ResponseEntity.ok(legalGuardianService.save(legalGuardianUpdateDto));
    }

    @DeleteMapping("/delete-legal-guardian/{id}")
    public ResponseEntity deleteLegalGuardian(@PathVariable("id") Long id) {
        legalGuardianService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}