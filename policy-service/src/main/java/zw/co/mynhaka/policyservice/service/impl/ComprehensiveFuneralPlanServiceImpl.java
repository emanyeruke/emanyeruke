package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanUpdateDTO;
import zw.co.mynhaka.policyservice.domain.enums.ProductType;
import zw.co.mynhaka.policyservice.domain.model.ComprehensiveFuneralPlan;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.repository.ComprehensiveFuneralPlanRepository;
import zw.co.mynhaka.policyservice.service.ComprehensiveFuneralPlanService;
import zw.co.mynhaka.policyservice.service.ProductService;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;
import zw.co.mynhaka.policyservice.service.exceptions.InputValidationException;
import zw.co.mynhaka.policyservice.service.mapper.ComprehensiveFuneralPlanMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComprehensiveFuneralPlanServiceImpl implements ComprehensiveFuneralPlanService {

    private final ComprehensiveFuneralPlanRepository comprehensiveFuneralPlanRepository;
    private final ComprehensiveFuneralPlanMapper comprehensiveFuneralPlanMapper;
    private final ProductService productService;

    @Override
    public List<ComprehensiveFuneralPlanResultDTO> findAll() {
        return comprehensiveFuneralPlanRepository.findAll().stream()
                .map(comprehensiveFuneralPlanMapper::toComprehensiveFuneralPlanProductResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ComprehensiveFuneralPlanResultDTO findById(Long id) {
        return comprehensiveFuneralPlanMapper.toComprehensiveFuneralPlanProductResultDTO(getOne(id));
    }

    @Override
    public ComprehensiveFuneralPlanResultDTO save(ComprehensiveFuneralPlanCreateDTO comprehensiveFuneralPlanCreateDto) {
        Product product = productService.getOne(comprehensiveFuneralPlanCreateDto.getProductId());

        if (!product.getProductType().equals(ProductType.COMPREHENSIVE_FUNERAL))
            throw new InputValidationException("Invalid selected product type");

        ComprehensiveFuneralPlan comprehensiveFuneralPlan = comprehensiveFuneralPlanMapper.toComprehensiveFuneralPlan(comprehensiveFuneralPlanCreateDto, product);

        return comprehensiveFuneralPlanMapper.toComprehensiveFuneralPlanProductResultDTO(comprehensiveFuneralPlanRepository.save(comprehensiveFuneralPlan));
    }

    @Override
    public ComprehensiveFuneralPlanResultDTO save(ComprehensiveFuneralPlanUpdateDTO comprehensiveFuneralPlanUpdateDto) {
        Product product = productService.getOne(comprehensiveFuneralPlanUpdateDto.getProductId());

        if (!product.getProductType().equals(ProductType.COMPREHENSIVE_FUNERAL))
            throw new InputValidationException("Invalid selected product type");

        ComprehensiveFuneralPlan savedComprehensiveFuneralPlan = getOne(comprehensiveFuneralPlanUpdateDto.getComprehensivePlanId());

        comprehensiveFuneralPlanMapper.updateAccidentPlan(savedComprehensiveFuneralPlan, comprehensiveFuneralPlanUpdateDto, product);

        return comprehensiveFuneralPlanMapper.toComprehensiveFuneralPlanProductResultDTO(comprehensiveFuneralPlanRepository.save(savedComprehensiveFuneralPlan));
    }

    @Override
    public ComprehensiveFuneralPlanResultDTO saveReverse(ComprehensiveFuneralPlanReverseCreateDTO comprehensiveFuneralPlanReverseCreateDto) {
        Product product = productService.getOne(comprehensiveFuneralPlanReverseCreateDto.getProductId());

        if (!product.getProductType().equals(ProductType.COMPREHENSIVE_FUNERAL))
            throw new InputValidationException("Invalid selected product type");

        ComprehensiveFuneralPlan comprehensiveFuneralPlan = comprehensiveFuneralPlanMapper.toComprehensiveFuneralPlan(comprehensiveFuneralPlanReverseCreateDto, product);

        return comprehensiveFuneralPlanMapper.toComprehensiveFuneralPlanProductResultDTO(comprehensiveFuneralPlanRepository.save(comprehensiveFuneralPlan));
    }

    @Override
    public ComprehensiveFuneralPlan getOne(Long id) {
        return comprehensiveFuneralPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ComprehensivePlan", "id", id));
    }

    @Override
    public void deleteById(Long id) {
        comprehensiveFuneralPlanRepository.deleteById(id);
    }
}
