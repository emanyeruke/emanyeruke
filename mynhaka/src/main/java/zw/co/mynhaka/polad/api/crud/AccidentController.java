package zw.co.mynhaka.polad.api.crud;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.audit.Audit;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductReverse;
import zw.co.mynhaka.polad.domain.dtos.accident.AccidentProductUpdateDto;
import zw.co.mynhaka.polad.service.iface.AccidentProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/product/accident", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidentController {

    private final AccidentProductService accidentProductService;


    /*
    Products CRUD
     */
    // @CreatePolicyProduct
    @PostMapping("sub/add-accident-product")
    @Audit(resource = "Accident Plan", action = "Create Accident Plan")
    public ResponseEntity<AccidentProductResultDTO> createProduct(@Valid @RequestBody AccidentProductCreateDto accidentProductCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body((accidentProductService.save(accidentProductCreateDto)));
    }

    /**
     * Creates new product that did not exist in reverse by using sum assured divided by 1000 multipllied by cost per thousand.
     */
    @PostMapping("sub/add-accident-product-reverse")
    @Audit(resource = "Accident Plan", action = "Create Accident Plan on the fly")
    public ResponseEntity<AccidentProductResultDTO> createProductFromReverse(@Valid @RequestBody AccidentProductReverse accidentProductCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body((accidentProductService.saveReverse(accidentProductCreateDto)));
    }

    //@ViewPolicyProduct
    @GetMapping("sub/get-accident-product/{id}")
    @Audit(resource = "Accident Plan", action = "Get Accident Plan")
    public ResponseEntity<AccidentProductResultDTO> findByIdProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accidentProductService.findById(id));
    }

    //@UpdatePolicyProduct
    @PutMapping("sub/edit-accident-product")
    @Audit(resource = "Accident Plan", action = "Edit Accident Plan")
    public ResponseEntity<AccidentProductResultDTO> updateProduct(@Valid @RequestBody AccidentProductUpdateDto accidentProductUpdateDto) {
        return ResponseEntity.ok(accidentProductService.save(accidentProductUpdateDto));
    }

    //@DeletePolicyProduct
    @DeleteMapping("sub/delete-accident-product/{id}")
    @Audit(resource = "Accident Plan", action = "Delete Accident Plan")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        accidentProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //@ViewPolicyProduct
    @GetMapping("get-all-accident")
    @Audit(resource = "Accident Plan", action = "Get All Accident Plan")
    public ResponseEntity<List<AccidentProductResultDTO>> findAll() {
        return ResponseEntity.ok(accidentProductService.findAll());
    }
}
