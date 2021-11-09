package zw.co.mynhaka.policyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanCreateReverse;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanUpdateDto;
import zw.co.mynhaka.policyservice.domain.enums.ProductType;
import zw.co.mynhaka.policyservice.domain.model.AccidentPlan;
import zw.co.mynhaka.policyservice.domain.model.Product;
import zw.co.mynhaka.policyservice.repository.AccidentPlanRepository;
import zw.co.mynhaka.policyservice.service.AccidentPlanService;
import zw.co.mynhaka.policyservice.service.ProductService;
import zw.co.mynhaka.policyservice.service.exceptions.EntityNotFoundException;
import zw.co.mynhaka.policyservice.service.exceptions.InputValidationException;
import zw.co.mynhaka.policyservice.service.mapper.AccidentPlanMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccidentPlanServiceImpl implements AccidentPlanService {

    private final AccidentPlanRepository accidentPlanRepository;
    private final AccidentPlanMapper accidentPlanMapper;
    private final ProductService productService;

    @Override
    public List<AccidentPlanResultDTO> findAll() {
        return accidentPlanRepository.findAll().stream()
                .map(accidentPlanMapper::toAccidentPlanResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccidentPlanResultDTO findById(Long id) {
        return accidentPlanMapper.toAccidentPlanResultDTO(getOne(id));
    }

    @Override
    public AccidentPlanResultDTO save(AccidentPlanCreateDTO accidentProductCreateDto) {
        Product product = productService.getOne(accidentProductCreateDto.getProductId());

        if (!product.getProductType().equals(ProductType.ACCIDENT))
            throw new InputValidationException("Invalid accident product id");

        AccidentPlan accidentPlan = accidentPlanMapper.toAccidentPlan(accidentProductCreateDto, product);

        return accidentPlanMapper.toAccidentPlanResultDTO(accidentPlanRepository.save(accidentPlan));
    }

    @Override
    public AccidentPlanResultDTO saveReverse(AccidentPlanCreateReverse accidentPlanCreateReverse) {
        Product product = productService.getOne(accidentPlanCreateReverse.getProductId());

        if (!product.getProductType().equals(ProductType.ACCIDENT))
            throw new InputValidationException("Invalid accident product id");

        AccidentPlan accidentPlan = accidentPlanMapper.toAccidentPlan(accidentPlanCreateReverse, product);

        return accidentPlanMapper.toAccidentPlanResultDTO(accidentPlanRepository.save(accidentPlan));
    }

    @Override
    public AccidentPlanResultDTO save(AccidentPlanUpdateDto accidentProductUpdateDto) {
        Product product = productService.getOne(accidentProductUpdateDto.getProductId());

        if (!product.getProductType().equals(ProductType.ACCIDENT))
            throw new InputValidationException("Invalid accident product id");

        AccidentPlan savedAccidentPlan = getOne(accidentProductUpdateDto.getAccidentPlanId());

        accidentPlanMapper.updateAccidentPlan(savedAccidentPlan, accidentProductUpdateDto, product);

        return accidentPlanMapper.toAccidentPlanResultDTO(accidentPlanRepository.save(savedAccidentPlan));
    }

    @Override
    public AccidentPlan getOne(Long id) {
        return accidentPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccidentPlan", "id", id));
    }

    @Override
    public void deleteById(Long id) {
        accidentPlanRepository.deleteById(id);
    }
}
