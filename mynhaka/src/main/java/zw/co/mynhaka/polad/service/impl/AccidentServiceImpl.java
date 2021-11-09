package zw.co.mynhaka.polad.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Accident;
import zw.co.mynhaka.polad.repository.AccidentRepository;
import zw.co.mynhaka.polad.service.iface.AccidentService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AccidentServiceImpl implements AccidentService {

    private final AccidentRepository accidentRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResultDto> findAll() {
        return accidentRepository.findAll()
                .stream()
                .map(productMapper::accidentToProductResultDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResultDto findById(Long id) {

        return productMapper.accidentToProductResultDto(accidentRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found")));
    }

    @Override
    @Transactional
    public ProductResultDto save(ProductCreateDto productCreateDto) {
        final Accident accident = productMapper.toAccident(productCreateDto);
        return productMapper.accidentToProductResultDto(accidentRepository.save(accident));
    }

    @Override
    public ProductResultDto save(ProductUpdateDto productUpdateDto) {
        final Accident accident = getOne(productUpdateDto.getId());
        productMapper.updateAccident(accident, productUpdateDto);
        return productMapper.accidentToProductResultDto(accidentRepository.save(accident));
    }

    @Override
    public void deleteById(Long id) {
        accidentRepository.deleteById(id);
    }

    @Override
    public Accident getOne(Long id) {
        return accidentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

    }

}
