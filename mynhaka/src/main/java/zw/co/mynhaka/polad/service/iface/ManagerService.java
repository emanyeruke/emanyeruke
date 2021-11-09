package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerResultDTO;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerUpdateDTO;
import zw.co.mynhaka.polad.domain.model.Manager;

import java.util.List;

public interface ManagerService {
    List<ManagerResultDTO> findAll();

    Page<ManagerResultDTO> findAll(Pageable pageable);

    ManagerResultDTO findById(Long id);

    ManagerResultDTO save(ManagerCreateDTO managerCreateDTO);

    ManagerResultDTO save(ManagerUpdateDTO managerUpdateDTO);

    void deleteById(Long id);

    Manager getOne(Long id);

    void fakerManager();
}
