package zw.co.mynhaka.polad.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianCreateDto;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianResultDTO;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianUpdateDto;
import zw.co.mynhaka.polad.domain.model.LegalGuardian;
import zw.co.mynhaka.polad.repository.LegalGuardianRepository;
import zw.co.mynhaka.polad.service.iface.LegalGuardianService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.policyholder.LegalGuardianToLegalGuardianResultDTO;


@Service
@Slf4j
public class LegalGuardianServiceImpl implements LegalGuardianService {

    private final LegalGuardianToLegalGuardianResultDTO toLegalGuardianResultDTO;
    private final LegalGuardianRepository legalGuardianRepository;

    public LegalGuardianServiceImpl(final LegalGuardianToLegalGuardianResultDTO toLegalGuardianResultDTO,
                                    final LegalGuardianRepository legalGuardianRepository) {
        this.toLegalGuardianResultDTO = toLegalGuardianResultDTO;
        this.legalGuardianRepository = legalGuardianRepository;
    }

    @Override
    public Page<LegalGuardianResultDTO> findAll(Pageable pageable) {
        return legalGuardianRepository.findAllBy(pageable)
                .map(toLegalGuardianResultDTO::convert);
    }

    @Override
    public LegalGuardianResultDTO findById(Long id) {
        return toLegalGuardianResultDTO.convert(legalGuardianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Legal guardian not found for id: " + id)));
    }

    @Override
    public LegalGuardianResultDTO save(LegalGuardianCreateDto legalGuardianCreateDto) {
        LegalGuardian legalGuardian = new LegalGuardian();
        legalGuardian.setName(legalGuardianCreateDto.getName());
        legalGuardian.setSurname(legalGuardianCreateDto.getSurname());
        legalGuardian.setContactNumber(legalGuardianCreateDto.getContactNumber());
        legalGuardian.setDateOfBirth(legalGuardianCreateDto.getDateOfBirth());
        legalGuardian.setEmail(legalGuardianCreateDto.getEmail());
        return toLegalGuardianResultDTO.convert(legalGuardianRepository.save(legalGuardian));
    }

    @Override
    public LegalGuardianResultDTO save(LegalGuardianUpdateDto legalGuardianUpdateDto) {
        LegalGuardian legalGuardian = getOne(legalGuardianUpdateDto.getLegalGuardianId());
        legalGuardian.setName(legalGuardianUpdateDto.getName());
        legalGuardian.setSurname(legalGuardianUpdateDto.getSurname());
        legalGuardian.setContactNumber(legalGuardianUpdateDto.getContactNumber());
        legalGuardian.setDateOfBirth(legalGuardianUpdateDto.getDateOfBirth());
        legalGuardian.setEmail(legalGuardianUpdateDto.getEmail());
        return toLegalGuardianResultDTO.convert(legalGuardianRepository.save(legalGuardian));
    }

    @Override
    public void deleteById(Long id) {
        legalGuardianRepository.deleteById(id);
    }

    @Override
    public LegalGuardian getOne(Long id) {
        log.debug("Request to get a legal guardian : {}", id);
        return legalGuardianRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Legal guardian not found"));
    }
}
