package zw.co.mynhaka.polad.service.mapper.beneficiary;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.accident.BeneficiaryAccidentResultDTO;
import zw.co.mynhaka.polad.domain.model.BeneficiaryAccident;


@Component
public class BeneficiaryAccidentToBeneficiaryAccidentResultDTO implements Converter<BeneficiaryAccident, BeneficiaryAccidentResultDTO> {
    @Override
    public BeneficiaryAccidentResultDTO convert(BeneficiaryAccident beneficiary) {

        BeneficiaryAccidentResultDTO beneficiaryResultDTO = new BeneficiaryAccidentResultDTO();

        if (beneficiary.getId() != null) {
            beneficiaryResultDTO.setBeneficiaryId(beneficiary.getId());
        }
        if (beneficiary.getName() != null) {
            beneficiaryResultDTO.setName(beneficiary.getName());
        }
        if (beneficiary.getSurname() != null) {
            beneficiaryResultDTO.setSurname(beneficiary.getSurname());
        }
        if (beneficiary.getDateOfBirth() != null) {
            beneficiaryResultDTO.setDateOfBirth(beneficiary.getDateOfBirth());
        }

        if (beneficiary.getGender() != null) {
            beneficiaryResultDTO.setGender(beneficiary.getGender().toString());
        }

        if (beneficiary.getIdNumber() != null) {
            beneficiaryResultDTO.setIdNumber(beneficiary.getIdNumber());
        }

        if (beneficiary.getPersonType() != null) {
            beneficiaryResultDTO.setPersonType(beneficiary.getPersonType().toString());
        }

        if (beneficiary.getRelationship() != null) {
            beneficiaryResultDTO.setRelationship(beneficiary.getRelationship());
        }

        if (beneficiary.getPercentageShare() != 0) {
            beneficiaryResultDTO.setPercentageShare(beneficiary.getPercentageShare());
        }




        if (beneficiary.getPolicyAccident() != null) {
            beneficiaryResultDTO.setAccidentPolicyNumber(beneficiary.getPolicyAccident().getPolicyNumber());
        }

        if (beneficiary.getAccident() != null) {
            beneficiaryResultDTO.setAccidentId(beneficiary.getAccident().getId());
        }

        return beneficiaryResultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
