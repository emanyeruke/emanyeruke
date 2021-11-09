package zw.co.mynhaka.policyservice.service;

import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.comprehensiveplan.ComprehensiveFuneralPlanUpdateDTO;
import zw.co.mynhaka.policyservice.domain.model.ComprehensiveFuneralPlan;

import java.util.List;

public interface ComprehensiveFuneralPlanService {
    List<ComprehensiveFuneralPlanResultDTO> findAll();

    ComprehensiveFuneralPlanResultDTO findById(Long id);

    ComprehensiveFuneralPlanResultDTO save(ComprehensiveFuneralPlanCreateDTO comprehensiveFuneralPlanCreateDto);

    ComprehensiveFuneralPlanResultDTO save(ComprehensiveFuneralPlanUpdateDTO comprehensiveFuneralPlanUpdateDto);

    ComprehensiveFuneralPlanResultDTO saveReverse(ComprehensiveFuneralPlanReverseCreateDTO comprehensiveFuneralPlanReverseCreateDto);

    ComprehensiveFuneralPlan getOne(Long id);

    void deleteById(Long id);
}
