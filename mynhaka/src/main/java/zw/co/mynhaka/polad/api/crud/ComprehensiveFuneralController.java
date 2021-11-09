package zw.co.mynhaka.polad.api.crud;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveProductUpdateDto;
import zw.co.mynhaka.polad.service.iface.ComprehensiveProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/product/comprehensivefuneral", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComprehensiveFuneralController {

    private final ComprehensiveProductService comprehensiveProductService;

    /**
     * Comprehensive Funeral CRUD
     */

//
//    @PostMapping("add-comprehensive")
//    public ResponseEntity<ComprehensiveFuneralResultDTO> create(@Valid @RequestBody ComprehensiveCreateDto comprehensiveCreateDto) {
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
//        return ResponseEntity.created(uri)
//                .body(comprehensiveFuneralService.save(comprehensiveCreateDto));
//    }
//
//    @GetMapping("get-comprehensive/{id}")
//    public ResponseEntity<ComprehensiveFuneralResultDTO> findById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(comprehensiveFuneralService.findById(id));
//    }
//
//    @PutMapping("edit-comprehensive")
//    public ResponseEntity<ComprehensiveFuneralResultDTO> update(@Valid @RequestBody ComprehensiveUpdateDto comprehensiveUpdateDto) {
//        return ResponseEntity.ok(comprehensiveFuneralService.save(comprehensiveUpdateDto));
//    }
//
//    @DeleteMapping("delete-comprehensive/{id}")
//    public ResponseEntity delete(@PathVariable("id") Long id) {
//        comprehensiveFuneralService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
    @GetMapping("get-all-comprehensive")
    public ResponseEntity<List<ComprehensiveFuneralProductResultDTO>> findAll() {
        return ResponseEntity.ok(comprehensiveProductService.findAll());
    }


    @PostMapping("sub/add-comprehensive-product")
    public ResponseEntity<ComprehensiveFuneralProductResultDTO> createProduct(@Valid @RequestBody ComprehensiveProductCreateDto comprehensiveProductCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(comprehensiveProductService.save(comprehensiveProductCreateDto));
    }

    /**
     * Creates new product that did not exist in reverse by using sum assured divided by 1000 multipllied by cost per thousand.
     */
    @PostMapping("sub/add-comprehensive-product-reverse")
    public ResponseEntity<ComprehensiveFuneralProductResultDTO> createProductFromReverse(@Valid @RequestBody ComprehensiveFuneralProductReverseCreateDto comprehensiveProductCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body((comprehensiveProductService.saveReverse(comprehensiveProductCreateDto)));
    }

    @GetMapping("sub/get-comprehensive-product/{id}")
    public ResponseEntity<ComprehensiveFuneralProductResultDTO> findByIdProduct(@PathVariable Long id) {
        return ResponseEntity.ok(comprehensiveProductService.findById(id));
    }

    @PutMapping("sub/edit-comprehensive-product")
    public ResponseEntity<ComprehensiveFuneralProductResultDTO> updateProduct(@Valid @RequestBody ComprehensiveProductUpdateDto comprehensiveProductUpdateDto) {
        return ResponseEntity.ok(comprehensiveProductService.save(comprehensiveProductUpdateDto));
    }

    @DeleteMapping("sub/delete-comprehensive-product/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        comprehensiveProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
