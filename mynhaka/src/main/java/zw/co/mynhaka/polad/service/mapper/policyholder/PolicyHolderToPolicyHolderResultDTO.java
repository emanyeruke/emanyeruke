package zw.co.mynhaka.polad.service.mapper.policyholder;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.service.mapper.agent.AgentToAgentResultDTO;
import zw.co.mynhaka.polad.service.mapper.employer.EmployerToEmployerResultDTO;


@Component
public class PolicyHolderToPolicyHolderResultDTO implements Converter<PolicyHolder, PolicyHolderResultDTO> {

    private final EmployerToEmployerResultDTO employerToEmployerResultDTO;
    private final AddressToAddressResultDTO toAddressResultDTO;
    //private final LegalGuardianToLegalGuardianResultDTO toLegalGuardianResultDTO;
    private final FinancialAdvisorToFinancialAdvisorResultDTO toFinancialAdvisorResultDTO;

    private final AgentToAgentResultDTO toAgentResultDTO;

    public PolicyHolderToPolicyHolderResultDTO(final EmployerToEmployerResultDTO employerToEmployerResultDTO,
                                               final AddressToAddressResultDTO toAddressResultDTO,
                                               //final LegalGuardianToLegalGuardianResultDTO toLegalGuardianResultDTO,
                                               final FinancialAdvisorToFinancialAdvisorResultDTO toFinancialAdvisorResultDTO,
                                               final AgentToAgentResultDTO toAgentResultDTO) {
        this.employerToEmployerResultDTO = employerToEmployerResultDTO;
        this.toAddressResultDTO = toAddressResultDTO;
       // this.toLegalGuardianResultDTO = toLegalGuardianResultDTO;
        this.toFinancialAdvisorResultDTO = toFinancialAdvisorResultDTO;
        this.toAgentResultDTO = toAgentResultDTO;
    }

    @Override
    public PolicyHolderResultDTO convert(PolicyHolder policyHolder) {
        PolicyHolderResultDTO resultDTO = new PolicyHolderResultDTO();

        resultDTO.setId(policyHolder.getId());
        resultDTO.setTitle(policyHolder.getTitle().toString());
        resultDTO.setFirstname(policyHolder.getFirstname());
        resultDTO.setLastname(policyHolder.getLastname());
        resultDTO.setDateOfBirth(policyHolder.getDateOfBirth());
        resultDTO.setIdNumber(policyHolder.getIdNumber());
        resultDTO.setEmail(policyHolder.getEmail());
        resultDTO.setGender(policyHolder.getGender().toString());
        resultDTO.setClientType(policyHolder.getClientType().toString());
        if (policyHolder.getWorkTelephone() != null) {
            resultDTO.setWorkTelephone(policyHolder.getWorkTelephone());
        }
        resultDTO.setMobile(policyHolder.getMobile());
        if (policyHolder.getEmployer() != null) {
            resultDTO.setEmployer(employerToEmployerResultDTO.convert(policyHolder.getEmployer()));
        }
        if (policyHolder.getEmployeeNumber() != null) {
            resultDTO.setEmployeeNumber(policyHolder.getEmployeeNumber());
        }
        if (policyHolder.getOccupation() != null) {
            resultDTO.setOccupation(policyHolder.getOccupation());
        }
        resultDTO.setPhysicalAddress(toAddressResultDTO.convert(policyHolder.getPhysicalAddress()));
        resultDTO.setPostalAddress(toAddressResultDTO.convert(policyHolder.getPostalAddress()));

//        if (policyHolder.getLegalGuardian() != null) {
//            resultDTO.setLegalGuardian(toLegalGuardianResultDTO.convert(policyHolder.getLegalGuardian()));
//        }
//
//        if (policyHolder.getFinancialAdvisor() != null) {
//            resultDTO.setFinancialAdvisor(toFinancialAdvisorResultDTO.convert(policyHolder.getFinancialAdvisor()));
//        }
        resultDTO.setPersonType(policyHolder.getPersonType().toString());
//        resultDTO.setAgent(toAgentResultDTO.convert(policyHolder.getAgent()));
        resultDTO.setPersonType(policyHolder.getPersonType().toString());

        return resultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }

    public Object convertAgain(Object o) {
        PolicyHolder policyHolder = (PolicyHolder) o;
        PolicyHolderResultDTO resultDTO = new PolicyHolderResultDTO();

        resultDTO.setId(policyHolder.getId());
        resultDTO.setTitle(policyHolder.getTitle().toString());
        resultDTO.setFirstname(policyHolder.getFirstname());
        resultDTO.setLastname(policyHolder.getLastname());
        resultDTO.setDateOfBirth(policyHolder.getDateOfBirth());
        resultDTO.setIdNumber(policyHolder.getIdNumber());
        resultDTO.setEmail(policyHolder.getEmail());
        resultDTO.setGender(policyHolder.getGender().toString());
        resultDTO.setClientType(policyHolder.getClientType().toString());

        if (policyHolder.getWorkTelephone() != null) {
            resultDTO.setWorkTelephone(policyHolder.getWorkTelephone());
        }
        resultDTO.setMobile(policyHolder.getMobile());
        if (policyHolder.getEmployer() != null) {
            resultDTO.setEmployer(employerToEmployerResultDTO.convert(policyHolder.getEmployer()));
        }
        if (policyHolder.getEmployeeNumber() != null) {
            resultDTO.setEmployeeNumber(policyHolder.getEmployeeNumber());
        }
        if (policyHolder.getOccupation() != null) {
            resultDTO.setOccupation(policyHolder.getOccupation());
        }
        resultDTO.setPhysicalAddress(toAddressResultDTO.convert(policyHolder.getPhysicalAddress()));
        resultDTO.setPostalAddress(toAddressResultDTO.convert(policyHolder.getPostalAddress()));

//        if (policyHolder.getLegalGuardian() != null) {
//            resultDTO.setLegalGuardian(toLegalGuardianResultDTO.convert(policyHolder.getLegalGuardian()));
//        }
//
//        if (policyHolder.getFinancialAdvisor() != null) {
//            resultDTO.setFinancialAdvisor(toFinancialAdvisorResultDTO.convert(policyHolder.getFinancialAdvisor()));
//        }
        resultDTO.setPersonType(policyHolder.getPersonType().toString());
//        resultDTO.setAgent(toAgentResultDTO.convert(policyHolder.getAgent()));
        resultDTO.setPersonType(policyHolder.getPersonType().toString());

        return resultDTO;
    }
}
