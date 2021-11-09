package zw.co.mynhaka.polad.service.mapper.beneficiary;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.funeral.BeneficiaryFuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.BeneficiaryFuneral;


@Component
public class BeneficiaryFuneralToBeneficiaryFuneralResultDTO implements Converter<BeneficiaryFuneral, BeneficiaryFuneralResultDTO> {
    @Override
    public BeneficiaryFuneralResultDTO convert(BeneficiaryFuneral beneficiary) {
        BeneficiaryFuneralResultDTO beneficiaryResultDTO = new BeneficiaryFuneralResultDTO();

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

        //beneficiaryResultDTO.setPercentageShare(beneficiary.getPercentageShare());


        if (beneficiary.getPolicyFuneral() != null) {
            beneficiaryResultDTO.setFuneralPolicyNumber(beneficiary.getPolicyFuneral().getPolicyNumber());
        }

        if (beneficiary.getFuneral() != null) {
            beneficiaryResultDTO.setFuneralId(beneficiary.getFuneral().getId());
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
