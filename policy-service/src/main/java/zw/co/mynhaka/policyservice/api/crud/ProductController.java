package zw.co.mynhaka.policyservice.api.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.mynhaka.policyservice.audit.Audit;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductUpdateDTO;
import zw.co.mynhaka.policyservice.service.ProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    @PostMapping("create-product")
    @Audit(resource = "Product", action = "Create Product")
    public ResponseEntity<ProductResultDTO> createProduct(
            @RequestBody @Valid ProductCreateDTO productCreateDTO
    ) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri)
                .body(productService.save(productCreateDTO));
    }

    @PutMapping("edit-product")
    @Audit(resource = "Product", action = "Edit Product")
    public ResponseEntity<ProductResultDTO> editProduct (
            @RequestBody @Valid ProductUpdateDTO productUpdateDTO
    ) {
        return ResponseEntity.ok(productService.save(productUpdateDTO));
    }

    @GetMapping("get-product/{id}")
    @Audit(resource = "Product", action = "Get product")
    public ResponseEntity<ProductResultDTO> getProduct (
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("get-all-products")
    @Audit(resource = "Product", action = "Get all products")
    public ResponseEntity<List<ProductResultDTO>> getAllProducts () {
        return ResponseEntity.ok(productService.findAll());
    }

    @DeleteMapping("delete-product/{id}")
    @Audit(resource = "Product", action = "Delete product")
    public ResponseEntity<?> deleteProduct(
            @PathVariable("id") Long id
    ) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
