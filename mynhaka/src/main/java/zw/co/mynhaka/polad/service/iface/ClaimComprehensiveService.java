package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ClaimComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.model.ClaimComprehensive;

import java.util.List;

public interface ClaimComprehensiveService {

    List<ClaimComprehensiveResultDTO> findAll();

    List<ClaimComprehensiveResultDTO> findAllByPolicyNumber(String policyNumber);

    ClaimComprehensiveResultDTO findById(Long id);

    ClaimComprehensive add(ClaimComprehensive claimComprehensive);

    void deleteById(Long id);

    ClaimComprehensive getOne(Long id);

    ClaimComprehensiveResultDTO validateClaim(Long id);

    ClaimComprehensiveResultDTO authorizeClaim(Long id);

    ClaimComprehensiveResultDTO approveClaim(Long id);

    ClaimComprehensiveResultDTO payClaim(Long id);

    ClaimComprehensiveResultDTO cancelClaim(Long id);


    ClaimComprehensiveResultDTO getOneDto(Long id);

    Page<ClaimComprehensiveResultDTO> findAll(PageRequest of);

    List<ClaimComprehensiveResultDTO> findAllByPolicyHolder(Long id);

    Page<ClaimComprehensiveResultDTO> findAllByStatus(PageRequest of, String status);
}
