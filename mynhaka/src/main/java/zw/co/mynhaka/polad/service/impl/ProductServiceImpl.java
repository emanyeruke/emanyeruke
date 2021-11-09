package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Product;
import zw.co.mynhaka.polad.repository.ProductRepository;
import zw.co.mynhaka.polad.service.iface.*;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AccidentService accidentService;
    private final ComprehensiveFuneralService comprehensiveFuneralService;
    private final FuneralService funeralService;
    private final SavingsService savingsService;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResultDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResultDto)
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " not found."));
    }

    @Override
    public ProductResultDto save(ProductCreateDto productCreateDto) {
        log.info(productCreateDto.toString());
        switch (productCreateDto.getProductType()) {
            case FUNERAL:
                if (productCreateDto.getAdminFee() == null)
                    throw new BusinessValidationException("Admin fee is required.");

                return funeralService.save(productCreateDto);

            case SAVINGS:
                if (productCreateDto.getAdminFee() == null)
                    throw new BusinessValidationException("Admin fee is required.");

                return savingsService.save(productCreateDto);

            case ACCIDENT:
                if (productCreateDto.getAdminFee() == null)
                    throw new BusinessValidationException("Admin fee is required.");

                return accidentService.save(productCreateDto);

            case COMPREHENSIVE_FUNERAL:
                return comprehensiveFuneralService.save(productCreateDto);

            default:
                throw new BusinessValidationException("Invalid product type");
        }
    }

    @Override
    public ProductResultDto save(ProductUpdateDto productUpdateDto) {
        switch (productUpdateDto.getProductType()) {
            case COMPREHENSIVE_FUNERAL:
                return comprehensiveFuneralService.save(productUpdateDto);
            case ACCIDENT:
                return accidentService.save(productUpdateDto);
            case SAVINGS:
                return savingsService.save(productUpdateDto);
            case FUNERAL:
                return funeralService.save(productUpdateDto);
            default:
                throw new BusinessValidationException("Invalid product type");
        }
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
