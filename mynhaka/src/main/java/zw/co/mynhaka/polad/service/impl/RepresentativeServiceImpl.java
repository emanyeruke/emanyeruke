package zw.co.mynhaka.polad.service.impl;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeResultDTO;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeUpdateDto;
import zw.co.mynhaka.polad.domain.enums.Category;
import zw.co.mynhaka.polad.domain.model.Employer;
import zw.co.mynhaka.polad.domain.model.Representative;
import zw.co.mynhaka.polad.repository.RepresentativeRepository;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.iface.EmployerService;
import zw.co.mynhaka.polad.service.iface.RepresentativeService;
import zw.co.mynhaka.polad.service.mapper.RepresentativeMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Slf4j
@Service
@RequiredArgsConstructor
public class RepresentativeServiceImpl implements RepresentativeService {

    private final RepresentativeRepository representativeRepository;
    private final RepresentativeMapper representativeMapper;
    private final EmployerService employerService;

    @Override
    public List<Representative> findAll() {
        List<Representative> representative = representativeRepository.findAll();
        if (representative.isEmpty()){
            throw new EntityNotFoundException("NO representative available at this time!");
        }
        return representative;
    }

    @Override
    public RepresentativeResultDTO findById(Long id) {
        return representativeMapper.toRepresentativeResultDTO(getOne(id));
    }

    @Override
    public RepresentativeResultDTO save(RepresentativeCreateDTO representativeCreateDTO) {
        Employer employer = employerService.getOne(representativeCreateDTO.getEmployerId());
        log.info(employer.toString());
        Representative representative = representativeMapper.toRepresentative(representativeCreateDTO, employer);
        log.info(representative.toString());
        return representativeMapper.toRepresentativeResultDTO(representativeRepository.save(representative));
    }

    @Override
    public RepresentativeResultDTO save(RepresentativeUpdateDto representativeUpdateDto) {
        Employer savedEmployer = employerService.getOne(representativeUpdateDto.getEmployerId());
        Representative savedRepresentative = getOne(representativeUpdateDto.getRepresentativeId());
        representativeMapper.updateRepresentative(savedRepresentative, savedEmployer, representativeUpdateDto);
        return representativeMapper.toRepresentativeResultDTO(representativeRepository.save(savedRepresentative));
    }

    @Override
    public void deleteById(Long id) {
        Representative representative = getOne(id);
        representativeRepository.delete(representative);
    }

    @Override
    public Representative getOne(Long id) {
        return representativeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Representative not found"));
    }

    @Override
    public void fakerRepresentative() {
        List<Category> categories = Arrays.asList(Category.values());

        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            Representative representative = new Representative();
            Faker faker = new Faker();
            representative.setFirstName(faker.name().firstName());
            representative.setLastName(faker.name().lastName());
            representative.setContactNumber(faker.phoneNumber().cellPhone());
            representative.setEmail(faker.internet().emailAddress());
            representative.setCategory(categories.get(random.nextInt(categories.size() - 1)));
            int employerNumber = 1 + random.nextInt(3457) % 50;
            try {
                Employer employer = employerService.getOne((long) employerNumber);
                representative.setEmployer(employer);
                Representative representative1 = representativeRepository.save(representative);
            } catch (EntityNotFoundException e) {
                log.info("Exception occurred");
            }
        }
    }
}
