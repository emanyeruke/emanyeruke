package zw.co.mynhaka.policyservice.service;

import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanCreateReverse;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.accidentplan.AccidentPlanUpdateDto;
import zw.co.mynhaka.policyservice.domain.model.AccidentPlan;

import java.util.List;

public interface AccidentPlanService {
    List<AccidentPlanResultDTO> findAll();

    AccidentPlanResultDTO findById(Long id);

    AccidentPlanResultDTO save(AccidentPlanCreateDTO accidentProductCreateDto);

    AccidentPlanResultDTO saveReverse(AccidentPlanCreateReverse accidentPlanCreateReverse);

    AccidentPlanResultDTO save(AccidentPlanUpdateDto accidentProductUpdateDto);

    AccidentPlan getOne(Long id);

    void deleteById(Long id);
}
