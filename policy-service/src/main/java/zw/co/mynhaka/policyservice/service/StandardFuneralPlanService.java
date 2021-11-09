package zw.co.mynhaka.policyservice.service;

import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.funeralplan.StandardFuneralPlanUpdateDTO;
import zw.co.mynhaka.policyservice.domain.model.StandardFuneralPlan;

import java.util.List;

public interface StandardFuneralPlanService {
    List<StandardFuneralPlanResultDTO> findAll();

    StandardFuneralPlanResultDTO findById(Long id);

    StandardFuneralPlanResultDTO save(StandardFuneralPlanCreateDTO standardFuneralPlanCreateDTO);

    StandardFuneralPlanResultDTO save(StandardFuneralPlanUpdateDTO standardFuneralPlanUpdateDTO);

    StandardFuneralPlanResultDTO saveReverse(StandardFuneralPlanReverseCreateDTO standardFuneralPlanReverseCreateDTO);

    StandardFuneralPlan getOne(Long id);

    void deleteById(Long id);
}
