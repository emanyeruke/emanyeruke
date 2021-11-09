package zw.co.mynhaka.polad.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.ClaimFuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.ClaimFuneral;

import java.util.List;

public interface ClaimFuneralService {

    List<ClaimFuneralResultDTO> findAll();

    List<ClaimFuneralResultDTO> findAllByPolicyNumber(String policyNumber);

    ClaimFuneralResultDTO findById(Long id);

    ClaimFuneral add(ClaimFuneral claimFuneral);

    void deleteById(Long id);

    ClaimFuneral getOne(Long id);

    ClaimFuneral validateClaim(Long id);

    ClaimFuneral authorizeClaim(Long id);

    ClaimFuneral approveClaim(Long id);

    ClaimFuneral payClaim(Long id);

    ClaimFuneral cancelClaim(Long id);


    ClaimFuneralResultDTO getOneDto(Long id);

    Page<ClaimFuneralResultDTO> findAll(PageRequest of);

    List<ClaimFuneralResultDTO> findAllByPolicyHolder(Long id);

    Page<ClaimFuneralResultDTO> findAllByStatus(PageRequest of, String status);
}
