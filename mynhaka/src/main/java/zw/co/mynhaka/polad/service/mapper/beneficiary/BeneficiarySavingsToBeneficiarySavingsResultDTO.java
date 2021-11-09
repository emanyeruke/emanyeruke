package zw.co.mynhaka.polad.service.mapper.beneficiary;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.savings.BeneficiarySavingsResultDTO;
import zw.co.mynhaka.polad.domain.model.BeneficiarySavings;


@Component
public class BeneficiarySavingsToBeneficiarySavingsResultDTO implements Converter<BeneficiarySavings, BeneficiarySavingsResultDTO> {
    @Override
    public BeneficiarySavingsResultDTO convert(BeneficiarySavings beneficiary) {
        BeneficiarySavingsResultDTO beneficiaryResultDTO = new BeneficiarySavingsResultDTO();

        if (beneficiary.getPersonType() != null) {
            beneficiaryResultDTO.setPersonType(beneficiary.getPersonType().toString());
        }
        beneficiaryResultDTO.setBeneficiaryId(beneficiary.getId());
        beneficiaryResultDTO.setName(beneficiary.getName());
        beneficiaryResultDTO.setSurname(beneficiary.getSurname());
        beneficiaryResultDTO.setDateOfBirth(beneficiary.getDateOfBirth());
        beneficiaryResultDTO.setGender(beneficiary.getGender().toString());
        beneficiaryResultDTO.setIdNumber(beneficiary.getIdNumber());

        if (beneficiary.getRelationship() != null) {
            beneficiaryResultDTO.setRelationship(beneficiary.getRelationship());
        }

        beneficiaryResultDTO.setPercentageShare(beneficiary.getPercentageShare());


        if (beneficiary.getPolicySavings() != null) {
            beneficiaryResultDTO.setSavingsPolicyNumber(beneficiary.getPolicySavings().getPolicyNumber());
        }

        if (beneficiary.getSavings() != null) {
            beneficiaryResultDTO.setSavingsId(beneficiary.getSavings().getId());
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
