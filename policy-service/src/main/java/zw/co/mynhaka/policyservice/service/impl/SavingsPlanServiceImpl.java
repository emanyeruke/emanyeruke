package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanUpdateDTO;
import zw.co.mynhaka.policyservice.domain.enums.ProductType;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.domain.model.SavingsPlan;
import zw.co.mynhaka.policyservice.repository.SavingsPlanRepository;
import zw.co.mynhaka.policyservice.service.ProductService;
import zw.co.mynhaka.policyservice.service.SavingsPlanService;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;
import zw.co.mynhaka.policyservice.service.exceptions.InputValidationException;
import zw.co.mynhaka.policyservice.service.mapper.SavingsPlanMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavingsPlanServiceImpl implements SavingsPlanService {

    private final SavingsPlanRepository savingsPlanRepository;
    private final SavingsPlanMapper savingsPlanMapper;
    private final ProductService productService;

    @Override
    public List<SavingsPlanResultDTO> findAll() {
        return savingsPlanRepository.findAll().stream()
                .map(savingsPlanMapper::toSavingsPlanResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SavingsPlanResultDTO findById(Long id) {
        return savingsPlanMapper.toSavingsPlanResultDTO(getOne(id));
    }

    @Override
    public SavingsPlanResultDTO save(SavingsPlanCreateDTO savingsProductCreateDto) {
        Product product = productService.getOne(savingsProductCreateDto.getProductId());

        if (!product.getProductType().equals(ProductType.SAVINGS))
            throw new InputValidationException("Invalid product type selected");

        SavingsPlan savingsPlan = savingsPlanMapper.toSavingsPlan(savingsProductCreateDto, product);

        return savingsPlanMapper.toSavingsPlanResultDTO(savingsPlanRepository.save(savingsPlan));
    }

    @Override
    public SavingsPlanResultDTO saveReverse(SavingsPlanReverseCreateDTO savingsPlanReverseCreateDTO) {
        Product product = productService.getOne(savingsPlanReverseCreateDTO.getProductId());

        if (!product.getProductType().equals(ProductType.SAVINGS))
            throw new InputValidationException("Invalid product type selected");

        SavingsPlan savingsPlan = savingsPlanMapper.toSavingsPlan(savingsPlanReverseCreateDTO, product);

        return savingsPlanMapper.toSavingsPlanResultDTO(savingsPlanRepository.save(savingsPlan));
    }

    @Override
    public SavingsPlanResultDTO save(SavingsPlanUpdateDTO savingsProductUpdateDto) {
        Product product = productService.getOne(savingsProductUpdateDto.getProductId());

        if (!product.getProductType().equals(ProductType.SAVINGS))
            throw new InputValidationException("Invalid product type selected");

        SavingsPlan savedSavingsPlan = getOne(savingsProductUpdateDto.getSavingsPlanId());

        savingsPlanMapper.updateSavingsPlan(savedSavingsPlan, savingsProductUpdateDto, product);

        return savingsPlanMapper.toSavingsPlanResultDTO(savingsPlanRepository.save(savedSavingsPlan));
    }

    @Override
    public SavingsPlan getOne(Long id) {
        return savingsPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavingsPlan", "id", id));
    }

    @Override
    public void deleteById(Long id) {
        savingsPlanRepository.deleteById(id);
    }
}
