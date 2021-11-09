package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.product.ProductUpdateDTO;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.repository.ProductRepository;
import zw.co.mynhaka.policyservice.service.ProductService;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;
import zw.co.mynhaka.policyservice.service.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResultDTO> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResultDTO findById(Long id) {
        return productMapper.toProductResultDTO(getOne(id));
    }

    @Override
    public ProductResultDTO save(ProductCreateDTO productCreateDto) {
        Product product = productMapper.toProduct(productCreateDto);
        return productMapper.toProductResultDTO(productRepository.save(product));
    }

    @Override
    public ProductResultDTO save(ProductUpdateDTO productUpdateDto) {
        Product savedProduct = getOne(productUpdateDto.getId());
        productMapper.updateProduct(savedProduct, productUpdateDto);
        return productMapper.toProductResultDTO(productRepository.save(savedProduct));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getOne(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", "id", id));
    }
}
