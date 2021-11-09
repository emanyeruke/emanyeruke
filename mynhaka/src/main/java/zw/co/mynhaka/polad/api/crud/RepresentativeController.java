package zw.co.mynhaka.polad.api.crud;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.api.response.ApiResponse;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeResultDTO;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeUpdateDto;
import zw.co.mynhaka.polad.service.iface.RepresentativeService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)

@Slf4j
public class RepresentativeController {

    private final RepresentativeService representativeService;

    public RepresentativeController(final RepresentativeService representativeService) {
        this.representativeService = representativeService;
    }

    @GetMapping("representatives")
    public ApiResponse findAll() {
        return new ApiResponse(200,"SUCCESS",representativeService.findAll());
    }

    @PostMapping("representative")
    public ResponseEntity<RepresentativeResultDTO> create(@Valid @RequestBody RepresentativeCreateDTO representativeCreateDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(representativeService.save(representativeCreateDTO));
    }

    @PostMapping("faker-representaive")
    public ResponseEntity createFaker() {
        representativeService.fakerRepresentative();
        return ResponseEntity.ok().build();
    }

    @PutMapping("representative")
    public ResponseEntity<RepresentativeResultDTO> edit(@Valid @RequestBody RepresentativeUpdateDto representativeUpdateDto) {
        return ResponseEntity.ok(representativeService.save(representativeUpdateDto));
    }

    @GetMapping("representative/{id}")
    public ResponseEntity<RepresentativeResultDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(representativeService.findById(id));
    }

    @DeleteMapping("representative/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        representativeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
