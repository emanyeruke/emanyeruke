package zw.co.mynhaka.policyholderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Employer;
import zw.co.mynhaka.policyholderservice.domain.models.Representative;
import zw.co.mynhaka.policyholderservice.repository.RepresentativeRepository;
import zw.co.mynhaka.policyholderservice.service.EmployerService;
import zw.co.mynhaka.policyholderservice.service.RepresentativeService;
import zw.co.mynhaka.policyholderservice.service.mapper.RepresentativeMapper;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class RepresentativeServiceImpl implements RepresentativeService {

    private final RepresentativeRepository representativeRepository;
    private final RepresentativeMapper representativeMapper;
    private final EmployerService employerService;

    @Override
    public Page<RepresentativeResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return representativeRepository.findAll(pageRequest)
                .map(representativeMapper::toRepresentativeResultDTO);
    }

    @Override
    public Page<RepresentativeResultDTO> findAllByEmployer(Long employerId, int page, int size) {
        Employer employer = employerService.getOne(employerId);
        PageRequest pageRequest = PageRequest.of(page, size);
        return representativeRepository.findAllByEmployer(employer, pageRequest)
                .map(representativeMapper::toRepresentativeResultDTO);
    }

    @Override
    public RepresentativeResultDTO findById(Long id) {
        return representativeMapper.toRepresentativeResultDTO(getOne(id));
    }

    @Override
    public RepresentativeResultDTO save(RepresentativeCreateDTO representativeCreateDTO) {
        Representative representative = representativeMapper.toRepresentative(representativeCreateDTO);

        Employer employer = employerService.getOne(representativeCreateDTO.getEmployerId());
        representative.setEmployer(employer);

        return representativeMapper.toRepresentativeResultDTO(representativeRepository.save(representative));
    }

    @Override
    public RepresentativeResultDTO save(RepresentativeUpdateDTO representativeUpdateDto) {
        Representative savedRepresentative = getOne(representativeUpdateDto.getRepresentativeId());

        representativeMapper.updateRepresentative(savedRepresentative, representativeUpdateDto);

        Employer employer = employerService.getOne(representativeUpdateDto.getEmployerId());
        savedRepresentative.setEmployer(employer);

        return representativeMapper.toRepresentativeResultDTO(representativeRepository.save(savedRepresentative));
    }

    @Override
    public void deleteById(Long id) {
        representativeRepository.deleteById(id);
    }

    @Override
    public Representative getOne(Long id) {
        return representativeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Representative with id: " + id + " not found"));
    }

    @Override
    public void fakerRepresentative() {

    }
}
