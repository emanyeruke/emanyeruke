package zw.co.mynhaka.polad.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneral;
import zw.co.mynhaka.polad.repository.ComprehensiveFuneralRepository;
import zw.co.mynhaka.polad.service.iface.ComprehensiveFuneralService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComprehensiveServiceImpl implements ComprehensiveFuneralService {

    private final ComprehensiveFuneralRepository comprehensiveFuneralRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResultDto> findAll() {
        return comprehensiveFuneralRepository.findAll()
                .stream()
                .map(productMapper::comprehensiveFuneralToProductResultDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResultDto findById(Long id) {
        return productMapper.comprehensiveFuneralToProductResultDto(comprehensiveFuneralRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id:" + id)));
    }

    @Override
    public ProductResultDto save(ProductCreateDto productCreateDto) {
        ComprehensiveFuneral comprehensiveFuneral = productMapper.toComprehensiveFuneral(productCreateDto);
        return productMapper.comprehensiveFuneralToProductResultDto(comprehensiveFuneralRepository.save(comprehensiveFuneral));
    }

    @Override
    public ProductResultDto save(ProductUpdateDto productUpdateDto) {
        ComprehensiveFuneral comprehensiveFuneral = getOne(productUpdateDto.getId());
        productMapper.updateComprehensiveFuneral(comprehensiveFuneral, productUpdateDto);
        return productMapper.comprehensiveFuneralToProductResultDto(comprehensiveFuneralRepository.save(comprehensiveFuneral));
    }

    @Override
    public void deleteById(Long id) {
        comprehensiveFuneralRepository.deleteById(id);
    }

    @Override
    public ComprehensiveFuneral getOne(Long id) {
        return comprehensiveFuneralRepository.getOne(id);
    }
}
