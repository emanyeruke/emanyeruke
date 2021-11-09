package zw.co.mynhaka.policyholderservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyholderservice.audit.Audit;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeUpdateDTO;
import zw.co.mynhaka.policyholderservice.service.RepresentativeService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/representative", produces = MediaType.APPLICATION_JSON_VALUE)
public class RepresentativeController {

    private final RepresentativeService representativeService;

    @PostMapping("create-representative")
    @Audit(resource = "Representative", action = "Create Representative")
    public ResponseEntity<RepresentativeResultDTO> createRepresentative(
            @RequestBody @Valid RepresentativeCreateDTO representativeCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(representativeService.save(representativeCreateDTO));
    }

    @PutMapping("edit-representative")
    @Audit(resource = "Representative", action = "Edit Representative")
    public ResponseEntity<RepresentativeResultDTO> editRepresentative(
            @RequestBody @Valid RepresentativeUpdateDTO representativeUpdateDTO
    ) {
        return ResponseEntity.ok(representativeService.save(representativeUpdateDTO));
    }

    @GetMapping("get-representative/{id}")
    @Audit(resource = "Representative", action = "Get Representative")
    public ResponseEntity<RepresentativeResultDTO> getRepresentative(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(representativeService.findById(id));
    }

    @GetMapping("get-all-representatives/{page}/{size}")
    @Audit(resource = "Representative", action = "Get all Representatives")
    public ResponseEntity<Page<RepresentativeResultDTO>> getAllRepresentative(
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(representativeService.findAll(page, size));
    }

    @GetMapping("get-all-representatives/{employerId}/representatives/{page}/{size}")
    @Audit(resource = "Representative", action = "Get all Representatives")
    public ResponseEntity<Page<RepresentativeResultDTO>> getAllRepresentativesByEmployer(
            @PathVariable("employerId") Long employerId,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(representativeService.findAllByEmployer(employerId, page, size));
    }

    @DeleteMapping("delete-representative/{id}")
    @Audit(resource = "Representative", action = "Delete Representative")
    public ResponseEntity<?> deleteRepresentative(
            @PathVariable("id") Long id
    ) {
        representativeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
