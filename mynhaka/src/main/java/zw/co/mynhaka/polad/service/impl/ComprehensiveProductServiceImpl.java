package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneral;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneralProduct;
import zw.co.mynhaka.polad.repository.ComprehensiveProductRepository;
import zw.co.mynhaka.polad.service.iface.ComprehensiveFuneralService;
import zw.co.mynhaka.polad.service.iface.ComprehensiveProductService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.ComprehensiveProductMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComprehensiveProductServiceImpl implements ComprehensiveProductService {

    private final ComprehensiveProductRepository comprehensiveProductRepository;
    private final ComprehensiveFuneralService comprehensiveFuneralService;
    private final ComprehensiveProductMapper comprehensiveProductMapper;

    @Override
    public List<ComprehensiveFuneralProductResultDTO> findAll() {
        return comprehensiveProductRepository.findAll()
                .stream()
                .map(comprehensiveProductMapper::toComprehensiveFuneralProductResultDTO)
                .sorted(Comparator.comparing(ComprehensiveFuneralProductResultDTO::getSumAssured))
                .collect(Collectors.toList());
    }

    @Override
    public ComprehensiveFuneralProductResultDTO findById(Long id) {
        return comprehensiveProductMapper.toComprehensiveFuneralProductResultDTO(comprehensiveProductRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id)));
    }

    @Override
    public ComprehensiveFuneralProductResultDTO save(ComprehensiveProductCreateDto comprehensiveProductCreateDto) {

        ComprehensiveFuneral comprehensive = comprehensiveFuneralService.getOne(comprehensiveProductCreateDto.getComprehensiveId());

        ComprehensiveFuneralProduct comprehensiveFuneralProduct = comprehensiveProductMapper
                .toComprehensiveFuneralProduct(comprehensive, comprehensiveProductCreateDto);

        return comprehensiveProductMapper.toComprehensiveFuneralProductResultDTO(comprehensiveProductRepository.save(comprehensiveFuneralProduct));
    }

    @Override
    public ComprehensiveFuneralProductResultDTO save(ComprehensiveProductUpdateDto comprehensiveProductUpdateDto) {

        ComprehensiveFuneralProduct comprehensiveFuneralProduct = getOne(comprehensiveProductUpdateDto.getComprehensiveId());

        comprehensiveProductMapper.updateComprehensiveFuneralProduct(comprehensiveFuneralProduct, comprehensiveProductUpdateDto);

        return comprehensiveProductMapper.toComprehensiveFuneralProductResultDTO(comprehensiveProductRepository.save(comprehensiveFuneralProduct));
    }

    @Override
    public void deleteById(Long id) {
        comprehensiveProductRepository.deleteById(id);
    }

    @Override
    public ComprehensiveFuneralProductResultDTO saveReverse(ComprehensiveFuneralProductReverseCreateDto comprehensiveProductCreateDto) {
        ComprehensiveFuneral comprehensive = comprehensiveFuneralService.getOne(comprehensiveProductCreateDto.getComprehensiveId());

        ComprehensiveFuneralProduct comprehensiveFuneralProduct = comprehensiveProductMapper
                .toComprehensiveFuneralProduct(comprehensive, comprehensiveProductCreateDto);

        return comprehensiveProductMapper.toComprehensiveFuneralProductResultDTO(comprehensiveProductRepository.save(comprehensiveFuneralProduct));
    }

    @Override
    public ComprehensiveFuneralProduct getOne(Long id) {
        return comprehensiveProductRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }
}
