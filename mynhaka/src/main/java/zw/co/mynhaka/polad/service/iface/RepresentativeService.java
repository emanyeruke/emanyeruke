package zw.co.mynhaka.polad.service.iface;

import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeResultDTO;
import zw.co.mynhaka.polad.domain.dtos.representative.RepresentativeUpdateDto;
import zw.co.mynhaka.polad.domain.model.Representative;

import java.util.List;


public interface RepresentativeService {

    List<Representative> findAll();

    RepresentativeResultDTO findById(Long id);

    RepresentativeResultDTO save(RepresentativeCreateDTO representativeCreateDTO);

    RepresentativeResultDTO save(RepresentativeUpdateDto representativeUpdateDto);

    void deleteById(Long id);

    Representative getOne(Long id);

    void fakerRepresentative();

}
