package zw.co.mynhaka.policyholderservice.service;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.RepresentativeUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.Representative;

public interface RepresentativeService {
    Page<RepresentativeResultDTO> findAll(int page, int size);

    Page<RepresentativeResultDTO> findAllByEmployer(Long employerId, int page, int size);

    RepresentativeResultDTO findById(Long id);

    RepresentativeResultDTO save(RepresentativeCreateDTO representativeCreateDTO);

    RepresentativeResultDTO save(RepresentativeUpdateDTO representativeUpdateDto);

    void deleteById(Long id);

    Representative getOne(Long id);

    void fakerRepresentative();
}
