package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import zw.co.mynhaka.polad.domain.dtos.accident.ClaimAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.claim.ClaimAccidentCreateDto;
import zw.co.mynhaka.polad.domain.model.ClaimAccident;

import java.util.List;

public interface ClaimAccidentService {

    List<ClaimAccident> findAll();

    List<ClaimAccidentResultDTO> findAllByPolicyNumber(String policyNumber);

    ClaimAccidentResultDTO findById(Long id);

    ClaimAccident add (ClaimAccident claimAccident);

    String update(ClaimAccident claimAccident);

    void deleteById(Long id);

    ClaimAccident getOne(Long id);

    ClaimAccidentResultDTO validateClaim(Long id);

    ClaimAccidentResultDTO authorizeClaim(Long id);

    ClaimAccidentResultDTO approveClaim(Long id);

    ClaimAccidentResultDTO cancelClaim(Long id);

    ClaimAccidentResultDTO payClaim(Long id);

    ClaimAccidentResultDTO getOneDto(Long id);

    Page<ClaimAccidentResultDTO> findAll(PageRequest of);

    List<ClaimAccidentResultDTO> findAllByPolicyHolder(Long id);

    Page<ClaimAccidentResultDTO> findAllByStatus(PageRequest of, String status);
}
