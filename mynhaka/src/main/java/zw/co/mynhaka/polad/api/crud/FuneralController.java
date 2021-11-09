package zw.co.mynhaka.polad.api.crud;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductUpdateDto;
import zw.co.mynhaka.polad.service.iface.FuneralProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/product/funeral", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuneralController {

    private final FuneralProductService funeralProductService;

    @GetMapping("get-all-funeral")
    public ResponseEntity<List<FuneralProductResultDTO>> findAll() {
        return ResponseEntity.ok(funeralProductService.findAll());
    }

    @PostMapping("sub/add-funeral-product")
    public ResponseEntity<FuneralProductResultDTO> createProduct(@Valid @RequestBody FuneralProductCreateDto funeralProductCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(funeralProductService.save(funeralProductCreateDto));
    }


    /**
     * Creates new product that did not exist in reverse by using sum assured divided by 1000 multipllied by cost per thousand.
     */
    @PostMapping("sub/add-funeral-product-reverse")
    public ResponseEntity<FuneralProductResultDTO> createProductFromReverse(@Valid @RequestBody FuneralProductReverseCreateDto funeralProductCreateDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body((funeralProductService.saveReverse(funeralProductCreateDto)));
    }

    @GetMapping("sub/get-funeral-product/{id}")
    public ResponseEntity<FuneralProductResultDTO> findByIdProduct(@PathVariable Long id) {
        return ResponseEntity.ok(funeralProductService.findById(id));
    }


    @PutMapping("sub/edit-funeral-product")
    public ResponseEntity<FuneralProductResultDTO> updateProduct(@Valid @RequestBody FuneralProductUpdateDto funeralProductUpdateDto) {
        return ResponseEntity.ok(funeralProductService.save(funeralProductUpdateDto));
    }

    @DeleteMapping("sub/delete-funeral-product/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        funeralProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
