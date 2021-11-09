package zw.co.mynhaka.polad.service.mapper.claim;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.accident.ClaimAccidentResultDTO;
import zw.co.mynhaka.polad.domain.model.ClaimAccident;
import zw.co.mynhaka.polad.service.mapper.policyholder.AddressToAddressResultDTO;


@Component
public class ClaimAccidentToClaimAccidentResultDTO implements Converter<ClaimAccident, ClaimAccidentResultDTO> {
    private final AddressToAddressResultDTO toAddressResultDTO;

    public ClaimAccidentToClaimAccidentResultDTO(final AddressToAddressResultDTO toAddressResultDTO) {
        this.toAddressResultDTO = toAddressResultDTO;
    }

    @Override
    public ClaimAccidentResultDTO convert(ClaimAccident claimAccident) {

        ClaimAccidentResultDTO resultDTO = new ClaimAccidentResultDTO();
        resultDTO.setId(claimAccident.getId());
        resultDTO.setPolicyNumber(claimAccident.getPolicyNumber());
        resultDTO.setPolicyId(claimAccident.getPolicyAccident().getId());

        //Claimant Details
       // resultDTO.setClaimantName(claimAccident.getClaimantName());
        resultDTO.setRelationship(claimAccident.getRelationship());
        resultDTO.setGender(claimAccident.getGender());
        resultDTO.setDob(claimAccident.getDob());

        //Accident and Hospitalisation
        resultDTO.setAccidentClaimReason(claimAccident.getAccidentClaimReason());
        resultDTO.setAccidentLocation(claimAccident.getAccidentLocation());
        resultDTO.setAdmission(claimAccident.getAdmission());
        resultDTO.setAccidentClaimReason(claimAccident.getAccidentClaimReason());
        resultDTO.setDateofInjury(claimAccident.getDateofInjury());
        resultDTO.setDescriptionAccident(claimAccident.getDescriptionAccident());
        resultDTO.setReleased(claimAccident.getReleased());
        resultDTO.setSimilarCondition(claimAccident.getSimilarCondition());
        resultDTO.setHospitalAdmission(claimAccident.getHospitalAdmission());
        resultDTO.setHospitalisationDate(claimAccident.getHospitalisationDate());


        resultDTO.setPrimaryPhysicianAddress(
                toAddressResultDTO.convert(claimAccident.getPrimaryPhysicianAddress()));
        resultDTO.setPrimaryPhysicianEmail(claimAccident.getPrimaryPhysicianEmail());
        resultDTO.setPrimaryPhysicianFax(claimAccident.getPrimaryPhysicianFax());
        resultDTO.setPrimaryPhysicianName(claimAccident.getPrimaryPhysicianName());
        resultDTO.setPrimaryPhysicianTelephone(claimAccident.getPrimaryPhysicianTelephone());

        resultDTO.setReferringPhysicianAddress(toAddressResultDTO.convert(claimAccident.getReferringPhysicianAddress()))
        ;
        resultDTO.setReferringPhysicianEmail(claimAccident.getReferringPhysicianEmail());
        resultDTO.setReferringPhysicianFax(claimAccident.getReferringPhysicianFax());
        resultDTO.setReferringPhysicianName(claimAccident.getReferringPhysicianName());
        resultDTO.setReferringPhysicianTelephone(claimAccident.getReferringPhysicianTelephone());

        resultDTO.setClaimStatus(claimAccident.getClaimStatus().toString());
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
}
