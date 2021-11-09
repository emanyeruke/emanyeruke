package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerCreateDto;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerResultDTO;
import zw.co.mynhaka.polad.domain.model.Employer;

import java.util.List;

public interface EmployerService {

    List<Employer> findAll();

    Page<EmployerResultDTO> findAll(Pageable page);

    EmployerResultDTO findById(Long id);

    EmployerResultDTO save(EmployerCreateDto employerCreateDto);

    Employer add(Employer employer);

    String update(Employer employer);

    void deleteById(Long id);

    Employer getOne(final Long id);

    void fakerEmployer();
}
