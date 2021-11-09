package zw.co.mynhaka.polad.service.impl;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.ManagerMapper;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerResultDTO;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Manager;
import zw.co.mynhaka.polad.repository.ManagerRepository;
import zw.co.mynhaka.polad.service.iface.ManagerService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.manager.ManagerToManagerResultDTO;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository repository;
    private final ManagerToManagerResultDTO toManagerResultDTO;
    private final ManagerMapper managerMapper;

    @Override
    public List<ManagerResultDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(managerMapper::toManagerResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ManagerResultDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(managerMapper::toManagerResultDTO);
    }

    @Override
    public ManagerResultDTO findById(Long id) {
        return managerMapper.toManagerResultDTO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Manager not found")));
    }

    @Override
    public ManagerResultDTO save(ManagerCreateDTO managerCreateDTO) {
        Manager manager = managerMapper.toManager(managerCreateDTO);
        return managerMapper.toManagerResultDTO(repository.save(manager));
    }

    @Override
    public ManagerResultDTO save(ManagerUpdateDTO managerUpdateDTO) {
        Manager manager = repository.getOne(managerUpdateDTO.getManagerId());
        managerMapper.updateManager(manager, managerUpdateDTO);
        return managerMapper.toManagerResultDTO(repository.save(manager));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Manager getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void fakerManager() {

        for (int i = 0; i < 5; i++) {
            Manager manager = new Manager();
            Faker faker = new Faker();
            manager.setName(faker.name().firstName());
            manager.setSurname(faker.name().lastName());
            manager.setEmail(faker.internet().emailAddress());
            repository.save(manager);
        }

    }
}
