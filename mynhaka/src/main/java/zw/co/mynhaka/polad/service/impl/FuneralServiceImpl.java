package zw.co.mynhaka.polad.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.ProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.ProductResultDto;
import zw.co.mynhaka.polad.domain.dtos.ProductUpdateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.Funeral;
import zw.co.mynhaka.polad.repository.FuneralProductRepository;
import zw.co.mynhaka.polad.repository.FuneralRepository;
import zw.co.mynhaka.polad.service.iface.FuneralService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.ProductMapper;
import zw.co.mynhaka.polad.service.mapper.funeral.FuneralToFuneralResultDTO;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FuneralServiceImpl implements FuneralService {


    private final FuneralToFuneralResultDTO toFuneralResultDTO;
    private final FuneralRepository funeralRepository;
    private final FuneralProductRepository funeralProductRepository;
    private final ProductMapper productMapper;

    @Override
    public List<FuneralResultDTO> findAll() {
        return funeralRepository.findAll()
                .stream()
                .map(toFuneralResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public FuneralResultDTO findById(Long id) {
        return toFuneralResultDTO.convert(
                funeralRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id)));
    }

    @Override
    public ProductResultDto save(ProductCreateDto productCreateDto) {
        final Funeral savedFuneral = funeralRepository.save(productMapper.toFuneral(productCreateDto));
        return productMapper.funeralToProductResultDto(savedFuneral);

    }

    @Override
    public ProductResultDto save(ProductUpdateDto productUpdateDto) {
        final Funeral funeral = getOne(productUpdateDto.getId());
        productMapper.updateFuneral(funeral, productUpdateDto);
        return productMapper.funeralToProductResultDto(funeralRepository.save(funeral));
    }

    @Override
    public void deleteById(Long id) {
        funeralRepository.deleteById(id);
    }

    @Override
    public Funeral getOne(Long id) {
        return funeralRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

    }
}
