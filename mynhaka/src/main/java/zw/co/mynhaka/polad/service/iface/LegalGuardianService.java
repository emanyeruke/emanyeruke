package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianCreateDto;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianResultDTO;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianUpdateDto;
import zw.co.mynhaka.polad.domain.model.LegalGuardian;


public interface LegalGuardianService {

    Page<LegalGuardianResultDTO> findAll(Pageable pageable);

    LegalGuardianResultDTO findById(Long id);

    LegalGuardianResultDTO save(LegalGuardianCreateDto legalGuardianCreateDto);

    LegalGuardianResultDTO save(LegalGuardianUpdateDto legalGuardianUpdateDto);

    void deleteById(Long id);

    LegalGuardian getOne(final Long id);
}
