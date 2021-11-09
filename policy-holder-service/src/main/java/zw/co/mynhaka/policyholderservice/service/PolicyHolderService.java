package zw.co.mynhaka.policyholderservice.service;

import org.springframework.data.domain.Page;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderCreateDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.policyholderservice.domain.dto.policyholder.PolicyHolderUpdateDTO;
import zw.co.mynhaka.policyholderservice.domain.models.PolicyHolder;

import java.util.List;

public interface PolicyHolderService {
    Page<PolicyHolderResultDTO> findAll(int page, int size);

    Page<PolicyHolderResultDTO> findAllByEmployer(Long employerId, int page, int size);

    PolicyHolderResultDTO findById(Long id);

    PolicyHolderResultDTO save(PolicyHolderCreateDTO policyHolderCreateDTO);

    PolicyHolderResultDTO save(PolicyHolderUpdateDTO policyHolderUpdateDTO);

    void deleteById(Long id);

    PolicyHolder getOne(Long id);

    PolicyHolder getOneByNationalID(String nationalId);

    List<PolicyHolder> findAll();

    List<PolicyHolder> findAllByEmployer(Long employerId);

    void saveFaker();
}
