package zw.co.mynhaka.policyholderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerCreateDT0;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Employer;
import zw.co.mynhaka.policyholderservice.repository.EmployerRepository;
import zw.co.mynhaka.policyholderservice.service.EmployerService;
import zw.co.mynhaka.policyholderservice.service.mapper.EmployerMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;
    private final EmployerMapper employerMapper;

    @Override
    public List<EmployerResultDTO> findAll() {
        return employerRepository.findAll().stream()
                .map(employerMapper::toEmployerResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EmployerResultDTO> findAll(int page, int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);

        return employerRepository.findAll(pageRequest)
                .map(employerMapper::toEmployerResultDTO);
    }

    @Override
    public EmployerResultDTO findById(Long id) {
        return employerMapper.toEmployerResultDTO(getOne(id));
    }

    @Override
    public EmployerResultDTO save(EmployerCreateDT0 employerCreateDT0) {
        Employer employer = employerMapper.toEmployer(employerCreateDT0);
        return employerMapper.toEmployerResultDTO(employerRepository.save(employer));
    }

    @Override
    public EmployerResultDTO save(EmployerUpdateDTO employerUpdateDto) {
        Employer savedEmployer = getOne(employerUpdateDto.getEmployerId());
        employerMapper.updateEmployer(savedEmployer, employerUpdateDto);
        return employerMapper.toEmployerResultDTO(employerRepository.save(savedEmployer));
    }

    @Override
    public Employer save(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public List<Employer> saveAll(List<Employer> employers) {
        return employerRepository.saveAll(employers);
    }

    @Override
    public void deleteById(Long id) {
        employerRepository.deleteById(id);
    }

    @Override
    public Employer getOne(Long id) {
        return employerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employer with id: " + id + " not found"));
    }

    @Override
    public void fakerEmployer() {

    }
}
