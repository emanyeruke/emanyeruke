package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanUpdateDTO;
import zw.co.mynhaka.policyservice.domain.enums.ProductType;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.domain.model.StandardFuneralPlan;
import zw.co.mynhaka.policyservice.repository.StandardFuneralPlanRepository;
import zw.co.mynhaka.policyservice.service.ProductService;
import zw.co.mynhaka.policyservice.service.StandardFuneralPlanService;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;
import zw.co.mynhaka.policyservice.service.exceptions.InputValidationException;
import zw.co.mynhaka.policyservice.service.mapper.StandardFuneralPlanMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StandardFuneralPlanServiceImpl implements StandardFuneralPlanService {

    private final StandardFuneralPlanRepository standardFuneralPlanRepository;
    private final StandardFuneralPlanMapper standardFuneralPlanMapper;
    private final ProductService productService;

    @Override
    public List<StandardFuneralPlanResultDTO> findAll() {
        return standardFuneralPlanRepository.findAll().stream()
                .map(standardFuneralPlanMapper::toStandardFuneralPlanResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StandardFuneralPlanResultDTO findById(Long id) {
        return standardFuneralPlanMapper.toStandardFuneralPlanResultDTO(getOne(id));
    }

    @Override
    public StandardFuneralPlanResultDTO save(StandardFuneralPlanCreateDTO standardFuneralPlanCreateDTO) {
        Product product = productService.getOne(standardFuneralPlanCreateDTO.getProductId());

        if (!product.getProductType().equals(ProductType.FUNERAL))
            throw new InputValidationException("Invalid selected product type");

        StandardFuneralPlan standardFuneralPlan = standardFuneralPlanMapper.toStandardFuneralPlan(standardFuneralPlanCreateDTO, product);

        return standardFuneralPlanMapper.toStandardFuneralPlanResultDTO(standardFuneralPlanRepository.save(standardFuneralPlan));
    }

    @Override
    public StandardFuneralPlanResultDTO save(StandardFuneralPlanUpdateDTO standardFuneralPlanUpdateDTO) {
        Product product = productService.getOne(standardFuneralPlanUpdateDTO.getProductId());

        if (!product.getProductType().equals(ProductType.FUNERAL))
            throw new InputValidationException("Invalid selected product type");

        StandardFuneralPlan savedStandardFuneralPlan = getOne(standardFuneralPlanUpdateDTO.getFuneralPlanId());

        standardFuneralPlanMapper.updateStandardFuneralPlan(savedStandardFuneralPlan, standardFuneralPlanUpdateDTO, product);

        return standardFuneralPlanMapper.toStandardFuneralPlanResultDTO(standardFuneralPlanRepository.save(savedStandardFuneralPlan));
    }

    @Override
    public StandardFuneralPlanResultDTO saveReverse(StandardFuneralPlanReverseCreateDTO standardFuneralPlanReverseCreateDTO) {
        Product product = productService.getOne(standardFuneralPlanReverseCreateDTO.getProductId());

        if (!product.getProductType().equals(ProductType.FUNERAL))
            throw new InputValidationException("Invalid selected product type");

        StandardFuneralPlan standardFuneralPlan = standardFuneralPlanMapper.toStandardFuneralPlan(standardFuneralPlanReverseCreateDTO, product);

        return standardFuneralPlanMapper.toStandardFuneralPlanResultDTO(standardFuneralPlanRepository.save(standardFuneralPlan));
    }

    @Override
    public StandardFuneralPlan getOne(Long id) {
        return standardFuneralPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StandardFuneral", "id", id));
    }

    @Override
    public void deleteById(Long id) {
        standardFuneralPlanRepository.deleteById(id);
    }
}
