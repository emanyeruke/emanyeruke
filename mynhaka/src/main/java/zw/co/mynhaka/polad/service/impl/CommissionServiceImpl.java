package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.agent.CommissionResultDTO;
import zw.co.mynhaka.polad.domain.model.Commission;
import zw.co.mynhaka.polad.repository.CommissionRepository;
import zw.co.mynhaka.polad.service.iface.CommissionService;
import zw.co.mynhaka.polad.service.mapper.commission.CommissionCreateDtoToCommission;
import zw.co.mynhaka.polad.service.mapper.commission.CommissionToCommissionResultDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class CommissionServiceImpl implements CommissionService {


    private final CommissionRepository commissionRepository;
    private final CommissionToCommissionResultDTO toCommissionResultDTO;
    private final CommissionCreateDtoToCommission toCommission;

    @Override
    public List<Commission> findAll() {
        List<Commission> commission = commissionRepository.findAll();
        if (commission.isEmpty()){
            throw new zw.co.mynhaka.polad.service.exception.EntityNotFoundException("NO commission available at this time!");
        }
        return commissionRepository.findAll();
    }

    @Override
    public CommissionResultDTO findById(Long id) {
        return toCommissionResultDTO.convert(getOne(id));
    }

    @Override
    public String add(Commission commission) {
        log.info("############ I AM SAVING COMMISSION RATES HERE...");

         commissionRepository.save(commission);

         return "Commission Rates Have Been Successfully Added";
    }


    @Override
    public Commission update (Commission commission) {

        Optional<Commission> commissionFromDatabase = commissionRepository.findById(commission.getId());

        if (!commissionFromDatabase.isPresent()){
            throw new EntityNotFoundException("Commission Rates not available.");
        }
        return commissionRepository.save(commission);
    }

    @Override
    public Commission getCommissionByPolicyType(String policyType) {
        return commissionRepository.findByPolicyType(policyType).orElseThrow(
                () -> new zw.co.mynhaka.polad.service.exception.EntityNotFoundException("No commission defined")
        );
    }

   /* @Override
    public int getFinalCommissionPaymentMonthByPolicyType(String policyType) {
        return commissionRepository.getFinalCommissionPaymentMonthByPolicyType(policyType);
    }

    */

    @Override
    public void deleteById(Long id) {
        commissionRepository.delete(getOne(id));
    }

    @Override
    public Commission getOne(Long id) {
        return commissionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }



}
