package zw.co.mynhaka.polad.api.crud;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductUpdateDto;
import zw.co.mynhaka.polad.service.iface.SavingsProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/product/savings", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class SavingController {

    private final SavingsProductService savingsProductService;


    //    /**
//     * Savings CRUD
//     *
//     * @param savingsCreateDto
//     * @return
//     */
//
//    @PostMapping("add-savings")
//    public ResponseEntity<SavingsResultDTO> create(@Valid @RequestBody SavingsCreateDto savingsCreateDto) {
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
//        return ResponseEntity.created(uri)
//                .body(savingsService.save(savingsCreateDto));
//    }
//
//    @GetMapping("get-savings{id}")
//    public ResponseEntity<SavingsResultDTO> findById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(savingsService.findById(id));
//    }
//
//    @PutMapping("edit-savings")
//    public ResponseEntity<SavingsResultDTO> update(@Valid @RequestBody SavingsUpdateDto savingsUpdateDto) {
//        return ResponseEntity.ok(savingsService.save(savingsUpdateDto));
//    }
//
//    @DeleteMapping("/savings{id}")
//    public ResponseEntity delete(@PathVariable("id") Long id) {
//        savingsService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

    /**
     * Savings Products
     *
     * @return
     */

    @GetMapping("get-all-savings")
    public ResponseEntity<List<SavingsProductResultDTO>> findAll() {
        return ResponseEntity.ok(savingsProductService.findAll());
    }

    @PostMapping("sub/add-savings-product")
    public ResponseEntity<SavingsProductResultDTO> createProduct(@Valid @RequestBody SavingsProductCreateDto savingsProductCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(savingsProductService.save(savingsProductCreateDto));
    }


    /**
     * Creates new product that did not exist in reverse by using sum assured divided by 1000 multipllied by cost per thousand.
     */
    @PostMapping("sub/add-savings-product-reverse")
    public ResponseEntity<SavingsProductResultDTO> createProductFromReverse(@Valid @RequestBody SavingsProductReverseCreateDto savingsProductCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body((savingsProductService.saveReverse(savingsProductCreateDto)));
    }

    @GetMapping("sub/get-savings-product/{id}")
    public ResponseEntity<SavingsProductResultDTO> findByIdProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(savingsProductService.findById(id));
    }

    @PutMapping("sub/edit-savings-product")
    public ResponseEntity<SavingsProductResultDTO> updateProduct(@Valid @RequestBody SavingsProductUpdateDto savingsProductUpdateDto) {

        return ResponseEntity.ok(savingsProductService.save(savingsProductUpdateDto));
    }

    @DeleteMapping("sub/delete-savings-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        savingsProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
