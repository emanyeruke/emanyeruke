package zw.co.mynhaka.policyservice.service;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.policyservice.domain.dto.savingssurrender.SavingsSurrenderCreateDTO;
import zw.co.mynhaka.policyservice.domain.dto.savingssurrender.SavingsSurrenderResultDTO;
import zw.co.mynhaka.policyservice.domain.model.SavingsSurrender;

public interface SavingsSurrenderService {
    SavingsSurrenderResultDTO save(String policyNumber, SavingsSurrenderCreateDTO savingsSurrenderCreateDTO);

    SavingsSurrenderResultDTO approveSurrender(Long surrenderId);

    SavingsSurrenderResultDTO approveSurrenderPayment(Long surrenderId);

    SavingsSurrender getOne(Long id);

    Page<SavingsSurrenderResultDTO> findAll(int page, int size);

    Page<SavingsSurrenderResultDTO> findAllByStatus(int page, int size, String surrenderStatus);

    void deleteById(Long id);
}
