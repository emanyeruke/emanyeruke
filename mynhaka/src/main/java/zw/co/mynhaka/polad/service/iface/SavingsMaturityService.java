package zw.co.mynhaka.polad.service.iface;


import zw.co.mynhaka.polad.domain.model.SavingsMaturity;

import java.util.List;

public interface SavingsMaturityService {
    SavingsMaturity save(String policyNumber, SavingsMaturity savingsMaturity);

    SavingsMaturity update(SavingsMaturity savingsMaturity);

    SavingsMaturity getOne(Long id);

    List<SavingsMaturity> findAll();

    List<SavingsMaturity> findAllByStatus(String maturityStatus);

    void deleteById(Long id);
}
