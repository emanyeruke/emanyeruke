package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Funeral;
import zw.co.mynhaka.polad.domain.model.FuneralProduct;
import zw.co.mynhaka.polad.repository.FuneralProductRepository;
import zw.co.mynhaka.polad.service.iface.FuneralProductService;
import zw.co.mynhaka.polad.service.iface.FuneralService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.FuneralProductMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuneralProductServiceImpl implements FuneralProductService {

    private final FuneralProductRepository funeralProductRepository;
    private final FuneralService funeralService;
    private final FuneralProductMapper funeralProductMapper;

    @Override
    public List<FuneralProductResultDTO> findAll() {
        return funeralProductRepository.findAll()
                .stream()
                .map(funeralProductMapper::toFuneralProductResultDTO)
                .sorted(Comparator.comparing(FuneralProductResultDTO::getSumAssured))
                .collect(Collectors.toList());
    }

    @Override
    public FuneralProductResultDTO findById(Long id) {
        return funeralProductMapper.toFuneralProductResultDTO(funeralProductRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id)));
    }

    @Override
    public FuneralProductResultDTO save(FuneralProductCreateDto funeralProductCreateDto) {
        Funeral funeral = funeralService.getOne(funeralProductCreateDto.getFuneralId());

        FuneralProduct funeralProduct = funeralProductMapper.toFuneralProduct(funeral, funeralProductCreateDto);

        return funeralProductMapper.toFuneralProductResultDTO(funeralProductRepository.save(funeralProduct));
    }

    @Override
    public FuneralProductResultDTO save(FuneralProductUpdateDto funeralProductUpdateDto) {
        FuneralProduct funeralProduct = getOne(funeralProductUpdateDto.getFuneralProductId());

        funeralProductMapper.updateFuneralProduct(funeralProduct, funeralProductUpdateDto);

        return funeralProductMapper.toFuneralProductResultDTO(funeralProductRepository.save(funeralProduct));
    }

    @Override
    public void deleteById(Long id) {
        funeralProductRepository.deleteById(id);
    }

    @Override
    public FuneralProductResultDTO saveReverse(FuneralProductReverseCreateDto funeralProductCreateDto) {
        Funeral funeral = funeralService.getOne(funeralProductCreateDto.getFuneralId());

        FuneralProduct funeralProduct = funeralProductMapper.toFuneralProduct(funeral, funeralProductCreateDto);

        return funeralProductMapper.toFuneralProductResultDTO(funeralProductRepository.save(funeralProduct));
    }

    @Override
    public FuneralProduct getOne(Long id) {
        return funeralProductRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funeral Policy Product not found"));
    }
}
