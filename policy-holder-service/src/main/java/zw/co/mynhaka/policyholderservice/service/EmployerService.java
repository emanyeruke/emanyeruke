package zw.co.mynhaka.policyholderservice.service;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerCreateDT0;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.employer.EmployerUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Employer;

import java.util.List;

public interface EmployerService {
    List<EmployerResultDTO> findAll();

    Page<EmployerResultDTO> findAll(int page, int size);

    EmployerResultDTO findById(Long id);

    EmployerResultDTO save(EmployerCreateDT0 employerCreateDT0);

    EmployerResultDTO save(EmployerUpdateDTO employerUpdateDto);

    Employer save(Employer employer);

    List<Employer> saveAll(List<Employer> employers);

    void deleteById(Long id);

    Employer getOne(final Long id);

    void fakerEmployer();
}
