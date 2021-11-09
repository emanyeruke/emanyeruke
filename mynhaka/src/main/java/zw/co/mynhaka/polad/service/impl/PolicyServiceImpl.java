package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.PolicyResultDTO;
import zw.co.mynhaka.polad.domain.model.Policy;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.repository.PolicyRepository;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.iface.PolicyHolderService;
import zw.co.mynhaka.polad.service.iface.PolicyService;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.PolicyMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final PolicyMapper policyMapper;
    private final PolicyHolderService policyHolderService;

    @Override
    public Page<PolicyResultDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return policyRepository.findAll(pageRequest);
               // .map(policyMapper::toPolicyResultDTO);
    }

    @Override
    public Policy getOne(Long id) {
        Optional<Policy> policy = policyRepository.findById(id);
        if (!policy.isPresent()) {
            throw new EntityNotFoundException("Policy with the ID " + id + " does not exist!");
        }
        return policy.get();
    }

    @Override
    public List<Policy> getAll() {
        List<Policy> policies =  policyRepository.findAll();
        if(policies.isEmpty()){
            throw new BusinessValidationException(" No policies available!");
        }else
            return  policies;
    }


    /*@Override
    public Policy findByPolicyHolder(PolicyHolder policyHolder) {
        PolicyHolder policyHolder = policyHolderService.getOne(policyHolderId);
        Optional<Policy> policy = policyRepository.findByPolicyHolder(policyHolder);
        if (!policy.isPresent()){
            throw new EntityNotFoundException("Policy holder with the ID " + policyHolder + " does not exist");
        }
        return policy.get();
    }

     */

    @Override
    public Policy findByPolicyNumber(String policyNumber){
        Optional<Policy> policy = policyRepository.findByPolicyNumber(policyNumber);
        if (!policy.isPresent()) {
            throw new BusinessValidationException("Policy with the ID " + policyNumber + " does not exist!");
        }
        return policy.get();
    }

    @Override
    public Policy getByPolicyNumber(String policyNumber) {
        Optional<Policy> policyNumberFromDatabase = policyRepository.findByPolicyNumber(policyNumber);
        if (!policyNumberFromDatabase.isPresent()) {
            throw new EntityNotFoundException("Policy Number: " + policyNumber + " not found");

        }
        return policyNumberFromDatabase.get();
    }

    @Override
    public List<Policy> getAllByPolicyHolder(PolicyHolder policyHolder) {
       List<Policy> policies =  policyRepository.findAllByPolicyHolder(policyHolder);
       if(policies.isEmpty()){
           throw  new EntityNotFoundException("Policies for policy holder :" + policyHolder.getFirstname() + " " + policyHolder.getLastname() + " cannot be found.");
       }

        return policies;
    }



    @Override
    public Policy findByPolicyId(Long policyId) {
        Optional<Policy> policy = policyRepository.findById(policyId);
        if (!policy.isPresent()){
            throw  new EntityNotFoundException("Policy with id: " + policyId + " not found");

        }
        return policy.get();
    }
}
