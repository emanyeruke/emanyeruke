package zw.co.mynhaka.policyservice.service;

import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanResultDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanReverseCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingsplan.SavingsPlanUpdateDTO;
import zw.co.mynhaka.policyservice.domain.model.SavingsPlan;

import java.util.List;

public interface SavingsPlanService {
    List<SavingsPlanResultDTO> findAll();

    SavingsPlanResultDTO findById(Long id);

    SavingsPlanResultDTO save(SavingsPlanCreateDTO savingsProductCreateDto);

    SavingsPlanResultDTO saveReverse(SavingsPlanReverseCreateDTO savingsProductCreateDto);

    SavingsPlanResultDTO save(SavingsPlanUpdateDTO savingsProductUpdateDto);

    SavingsPlan getOne(Long id);

    void deleteById(Long id);
}
