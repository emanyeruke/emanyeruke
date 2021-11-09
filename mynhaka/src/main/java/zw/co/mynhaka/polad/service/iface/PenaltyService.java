package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyResultDTO;
import zw.co.mynhaka.polad.domain.dtos.penalty.PenaltyUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Penalty;

import java.util.List;

public interface PenaltyService {

    List<PenaltyResultDTO> save(List<PenaltyCreateDTO> penaltyCreateDTOs);

    PenaltyResultDTO save(PenaltyCreateDTO penaltyCreateDTO);

    PenaltyResultDTO update(Long id, PenaltyUpdateDTO penaltyUpdateDTO);

    List<PenaltyResultDTO> update(List<PenaltyUpdateDTO> penaltyUpdateDTOs);

    PenaltyResultDTO getOne(Long id);

    Penalty findOne(Long id);

    Page<PenaltyResultDTO> findAll(int page, int size);

    Penalty findPenaltyByMonth(int month);

    void deleteById(Long id);

    void penaltyFaker();
}
