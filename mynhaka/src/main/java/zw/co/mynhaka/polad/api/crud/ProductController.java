package zw.co.mynhaka.polad.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.service.iface.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    @GetMapping("get-all-products")
    public List<ProductResultDto> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("create-product")
    public ProductResultDto create(@RequestBody @Valid ProductCreateDto productCreateDto) {
        return productService.save(productCreateDto);
    }

    @PutMapping("edit-product")
    public ProductResultDto update(@RequestBody @Valid ProductUpdateDto productUpdateDto) {
        return productService.save(productUpdateDto);
    }

//    @DeleteMapping("delete-product/{id}")
//    public void delete(@PathVariable("id") long id) {
//        productService.delete(id);
//    }
}
