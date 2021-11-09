package zw.co.mynhaka.policyservice.service;

import zw.co.mynhaka.policyservice.domain.dto.product.ProductCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductUpdateDTO;
import zw.co.mynhaka.policyservice.domain.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResultDTO> findAll();

    ProductResultDTO findById(Long id);

    Product getOne(Long id);

    ProductResultDTO save(ProductCreateDTO productCreateDto);

    ProductResultDTO save(ProductUpdateDTO productUpdateDto);

    void deleteById(Long id);
}
