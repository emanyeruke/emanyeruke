package zw.co.mynhaka.polad.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Savings;
import zw.co.mynhaka.polad.repository.SavingsProductRepository;
import zw.co.mynhaka.polad.repository.SavingsRepository;
import zw.co.mynhaka.polad.service.iface.SavingsService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.ProductMapper;
import zw.co.mynhaka.polad.service.mapper.savings.SavingsToSavingsResultDTO;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SavingsServiceImpl implements SavingsService {

    private final SavingsToSavingsResultDTO toSavingsResultDTO;
    private final SavingsRepository savingsRepository;
    private final SavingsProductRepository savingsProductRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResultDto> findAll() {
        return savingsRepository.findAll()
                .stream()
                .map(productMapper::savingsToProductResultDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResultDto findById(Long id) {
        return productMapper.savingsToProductResultDto(savingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found for id" + id)));
    }

    @Override
    public ProductResultDto save(ProductCreateDto productCreateDto) {
        final Savings savings = productMapper.toSavings(productCreateDto);
        return productMapper.savingsToProductResultDto(savingsRepository.save(savings));
    }

    @Override
    public ProductResultDto save(ProductUpdateDto productUpdateDto) {
        final Savings savings = getOne(productUpdateDto.getId());
        productMapper.updateSavings(savings, productUpdateDto);
        return productMapper.savingsToProductResultDto(savingsRepository.save(savings));
    }

    @Override
    public void deleteById(Long id) {
        savingsRepository.deleteById(id);
    }

    @Override
    public Savings getOne(Long id) {
        return savingsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }
}
