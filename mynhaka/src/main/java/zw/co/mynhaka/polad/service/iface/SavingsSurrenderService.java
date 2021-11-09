package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderResultDTO;
import zw.co.mynhaka.polad.domain.dtos.SavingsSurrenderUpdateDTO;
import zw.co.mynhaka.polad.domain.model.SavingsSurrender;

import java.util.List;

public interface SavingsSurrenderService {
    SavingsSurrenderResultDTO save(String policyNumber, SavingsSurrenderCreateDTO savingsSurrenderCreateDTO);

    List<SavingsSurrenderResultDTO> save(List<SavingsSurrenderUpdateDTO> savingsSurrenderUpdateDTOList);

    SavingsSurrender getOne(Long id);

    Page<SavingsSurrenderResultDTO> findAll(int page, int size);

    Page<SavingsSurrenderResultDTO> findAllByStatus(int page, int size, String surrenderStatus);

    void deleteById(Long id);
}
