package zw.co.mynhaka.polad.service.mapper.beneficiary;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.beneficiary.comprehensive.BeneficiaryComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.model.BeneficiaryComprehensive;


@Component
@Slf4j
public class BeneficiaryComprehensiveToBeneficiaryComprehensiveResultDTO implements Converter<BeneficiaryComprehensive, BeneficiaryComprehensiveResultDTO> {
    @Override
    public BeneficiaryComprehensiveResultDTO convert(BeneficiaryComprehensive beneficiary) {
        BeneficiaryComprehensiveResultDTO beneficiaryResultDTO = new BeneficiaryComprehensiveResultDTO();

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


        if (beneficiary.getPolicyComprehensive() != null) {
            beneficiaryResultDTO.setComprehensivePolicyNumber(beneficiary.getPolicyComprehensive().getPolicyNumber());
        }

        if (beneficiary.getComprehensiveFuneral() != null) {
            beneficiaryResultDTO.setComprehensiveFuneralId(beneficiary.getComprehensiveFuneral().getId());
        }

        log.info("############## Response for conversion: {}", beneficiaryResultDTO.toString());
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
