package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductCreateDto;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductResultDTO;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductReverseCreateDto;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsProductUpdateDto;
import zw.co.mynhaka.polad.domain.model.Savings;
import zw.co.mynhaka.polad.domain.model.SavingsProduct;
import zw.co.mynhaka.polad.repository.SavingsProductRepository;
import zw.co.mynhaka.polad.service.iface.SavingsProductService;
import zw.co.mynhaka.polad.service.iface.SavingsService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.SavingsProductMapper;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class SavingsProductServiceImpl implements SavingsProductService {

    @Value("${savings.for.every.thousand}")
    private BigDecimal costPerThousand;

    private final SavingsProductRepository savingsRepository;
    private final SavingsProductMapper savingsProductMapper;
    private final SavingsService savingsService;

    @Override
    public List<SavingsProductResultDTO> findAll() {
        return savingsRepository.findAll()
                .stream()
                .map(savingsProductMapper::toSavingsProductResultDTO)
                .sorted(Comparator.comparing(SavingsProductResultDTO::getMonthlyInvestmentPremium))
                .collect(Collectors.toList());
    }

    @Override
    public SavingsProductResultDTO findById(Long id) {
        return savingsProductMapper.toSavingsProductResultDTO(savingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found for id " + id)));
    }

    @Override
    public SavingsProductResultDTO save(SavingsProductCreateDto savingsProductCreateDto) {
        Savings savings = savingsService.getOne(savingsProductCreateDto.getSavingsId());
        SavingsProduct savingsProduct = savingsProductMapper.toSavingsProduct(savings, savingsProductCreateDto);
        return savingsProductMapper.toSavingsProductResultDTO(savingsRepository.save(savingsProduct));

    }

    @Override
    public SavingsProductResultDTO save(SavingsProductUpdateDto savingsProductUpdateDto) {
        SavingsProduct savingsProduct = getOne(savingsProductUpdateDto.getSavingsProductId());
        savingsProductMapper.updateSavingsProduct(savingsProduct, savingsProductUpdateDto);
        return savingsProductMapper.toSavingsProductResultDTO(savingsRepository.save(savingsProduct));
    }

    @Override
    public void deleteById(Long id) {
        savingsRepository.deleteById(id);
    }

    @Override
    public SavingsProductResultDTO saveReverse(SavingsProductReverseCreateDto savingsProductCreateDto) {
        Savings savings = savingsService.getOne(savingsProductCreateDto.getSavingsId());
        SavingsProduct savingsProduct = savingsProductMapper.toSavingsProduct(savings, savingsProductCreateDto);
        return savingsProductMapper.toSavingsProductResultDTO(savingsRepository.save(savingsProduct));
    }

    @Override
    public SavingsProduct getOne(Long id) {
        return savingsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Savings Policy Product not found"));
    }
}
