package zw.co.mynhaka.polad.service.iface;


import zw.co.mynhaka.polad.domain.model.ClaimSavingsDeath;

import java.util.List;

public interface ClaimSavingsDeathService {

    String add(ClaimSavingsDeath claimSavingsDeath);

    List<ClaimSavingsDeath> findAll();

    List<ClaimSavingsDeath> findAllByPolicyNumber(String policyNumber);

    void deleteById(Long id);

    ClaimSavingsDeath getOne(Long id);

    ClaimSavingsDeath validateClaim(Long id);

    ClaimSavingsDeath authorizeClaim(Long id);

    ClaimSavingsDeath approveClaim(Long id);

    ClaimSavingsDeath payClaim(Long id);

    ClaimSavingsDeath cancelClaim(Long id);


    ClaimSavingsDeath getOneDto(Long id);

    List<ClaimSavingsDeath> findAllByPolicyHolder(Long id);
}
