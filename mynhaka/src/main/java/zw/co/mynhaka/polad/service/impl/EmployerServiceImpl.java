package zw.co.mynhaka.polad.service.impl;


import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerCreateDto;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerResultDTO;
import zw.co.mynhaka.polad.domain.model.Address;
import zw.co.mynhaka.polad.domain.model.Employer;
import zw.co.mynhaka.polad.repository.EmployerRepository;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.exception.RecordNotFoundException;
import zw.co.mynhaka.polad.service.iface.EmployerService;
import zw.co.mynhaka.polad.service.mapper.employer.EmployerToEmployerResultDTO;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployerServiceImpl implements EmployerService {

    private final EmployerToEmployerResultDTO toEmployerResultDTO;
    private final EmployerRepository employerRepository;

    public EmployerServiceImpl(final EmployerToEmployerResultDTO toEmployerResultDTO,
                               final EmployerRepository employerRepository) {
        this.toEmployerResultDTO = toEmployerResultDTO;
        this.employerRepository = employerRepository;
    }


    @Override
    public Page<EmployerResultDTO> findAll(Pageable pageable) {
        return employerRepository.findAll(pageable)
                .map(toEmployerResultDTO::convert);
    }

    @Override
    public List<Employer> findAll() {
        List<Employer> employer = employerRepository.findAll();
        if (employer.isEmpty()){
            throw new EntityNotFoundException("NO employers available at this time!");
        }
        return employer;
    }

    @Override
    public EmployerResultDTO findById(Long id) {
        return toEmployerResultDTO.convert(getOne(id));
    }

    @Override
    public EmployerResultDTO save(EmployerCreateDto employerCreateDto) {
        Employer employer = new Employer();
        employer.setName(employerCreateDto.getName());
       /* employer.setRepresentative(employerCreateDto.getRepresentative());*/
        employer.setContactNumber(employerCreateDto.getContactNumber());
        employer.setEmail(employerCreateDto.getEmail());
        return toEmployerResultDTO.convert(employerRepository.save(employer));
    }

    @Override
    public String update(Employer employer) {
        Optional<Employer> employerFromDatabase = employerRepository.findById(employer.getId());
        if(!employerFromDatabase.isPresent()){
            throw new EntityNotFoundException("Employer not found!");
        }
        employerRepository.save(employer);
        return "Employer has been successfully updated";

    }

    @Override
    public void deleteById(Long id) {
        employerRepository.deleteById(id);
    }

    @Override
    public Employer getOne(Long id) {
        log.debug("Request to get a member : {}", id);
        return employerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Employer not found"));
    }

    @Override
    public void fakerEmployer() {
        for (int i = 0; i < 50; i++) {
            Employer employer = new Employer();
            Faker faker = new Faker();
            employer.setName(faker.company().name());
            /*employer.setRepresentative(faker.name().fullName());*/
            employer.setContactNumber(faker.phoneNumber().cellPhone());
            employer.setEmail(faker.internet().emailAddress());
            Address address = new Address();
            address.setCity(faker.address().city());
            address.setStreet(faker.address().streetAddress());
            address.setSuburb(faker.address().streetName());
           // employer.setAddress(address);
            employerRepository.save(employer);
        }
    }
    @Override
    public Employer add(Employer employer) {

        log.info("########### I am about to save employer");

        return  employerRepository.save(employer);
    }
}
