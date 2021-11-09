package zw.co.mynhaka.polad.service.iface;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderUpdateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyFuneralResultDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;

import java.util.List;


public interface PolicyHolderService {

    //Page<PolicyHolderResultDTO> findAll(final Pageable pageable);

    PolicyHolderResultDTO findById(Long id);

    PolicyHolder add(PolicyHolder policyHolder);

    PolicyHolder update(PolicyHolder policyHolder);

    void deleteById(Long id);

    PolicyHolder getOne(Long id);

    PolicyHolder getOneByNationalID(String nationalId);

    List<PolicyAccidentResultDTO> getAccidentPolicy(Long id);

    List<PolicyFuneralResultDTO> getFuneralPolicy(Long id);

    List<PolicyComprehensiveResultDTO> getComprehensivePolicy(Long id);

    List<PolicySavingsResultDTO> getSavingsPolicy(Long id);

    List<PolicyHolder> findAll();

    void saveFaker();

    Page<PolicyHolderResultDTO> searchAll(Specification msTitleRating, Pageable pageable);
}
