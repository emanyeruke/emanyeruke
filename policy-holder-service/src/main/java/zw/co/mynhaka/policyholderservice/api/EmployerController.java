package zw.co.mynhaka.policyholderservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyholderservice.audit.Audit;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerCreateDT0;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerUpdateDTO;
import zw.co.mynhaka.policyholderservice.service.EmployerService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/employer", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController {

    private final EmployerService employerService;

    @PostMapping("create-employer")
    @Audit(resource = "Employer", action = "Create employer")
    public ResponseEntity<EmployerResultDTO> createEmployer (
            @RequestBody @Valid EmployerCreateDT0 employerCreateDT0
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(employerService.save(employerCreateDT0));
    }

    @PutMapping("edit-employer")
    @Audit(resource = "Employer", action = "Update employer")
    public ResponseEntity<EmployerResultDTO> editEmployer(
            @RequestBody @Valid EmployerUpdateDTO employerUpdateDTO
    ) {
        return ResponseEntity.ok(employerService.save(employerUpdateDTO));
    }

    @GetMapping("get-all-employers")
    @Audit(resource = "Employer", action = "View all employers")
    public ResponseEntity<List<EmployerResultDTO>> getAllEmployers() {
        return ResponseEntity.ok(employerService.findAll());
    }

    @GetMapping("get-all-employers/{page}/{size}")
    @Audit(resource = "Employer", action = "Get All Employers")
    public ResponseEntity<Page<EmployerResultDTO>> getAllEmployers(
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        return ResponseEntity.ok(employerService.findAll(page, size));
    }

    @GetMapping("get-employer/{id}")
    @Audit(resource = "Employer", action = "Get employer")
    public ResponseEntity<EmployerResultDTO> getEmployer(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(employerService.findById(id));
    }

    @DeleteMapping("delete-employer/{id}")
    @Audit(resource = "Employer", action = "Delete employer")
    public ResponseEntity<?> deleteEmployer(
            @PathVariable("id") Long id
    ) {
        employerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
