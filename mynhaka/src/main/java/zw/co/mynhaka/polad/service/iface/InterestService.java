package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.polad.domain.dtos.InterestCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.InterestResultDTO;
import zw.co.mynhaka.polad.domain.dtos.InterestUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Interest;

import java.util.List;

public interface InterestService {
    List<InterestResultDTO> save(List<InterestCreateDTO> interestCreateDTOs);

    InterestResultDTO save(InterestCreateDTO interestCreateDTO);

    InterestResultDTO update(Long id, InterestUpdateDTO interestUpdateDTO);

    List<InterestResultDTO> update(List<InterestUpdateDTO> interestUpdateDTO);

    InterestResultDTO getOne(Long id);

    Interest findOne(Long id);

    Page<InterestResultDTO> findAll(int page, int size);

    Interest findInterestByMonth(int month);

    void deleteById(Long id);

    void interestsFaker();
}
