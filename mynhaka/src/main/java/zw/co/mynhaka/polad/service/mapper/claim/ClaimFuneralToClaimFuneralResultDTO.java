package zw.co.mynhaka.polad.service.mapper.claim;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.funeral.ClaimFuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.ClaimFuneral;
import zw.co.mynhaka.polad.service.mapper.policyholder.AddressToAddressResultDTO;


@Component
public class ClaimFuneralToClaimFuneralResultDTO implements Converter<ClaimFuneral, ClaimFuneralResultDTO> {
    private final AddressToAddressResultDTO toAddressResultDTO;

    public ClaimFuneralToClaimFuneralResultDTO(final AddressToAddressResultDTO toAddressResultDTO) {
        this.toAddressResultDTO = toAddressResultDTO;
    }

    @Override
    public ClaimFuneralResultDTO convert(ClaimFuneral claimFuneral) {
        ClaimFuneralResultDTO resultDTO = new ClaimFuneralResultDTO();
        resultDTO.setId(claimFuneral.getId());
        resultDTO.setPolicyId(claimFuneral.getPolicyFuneral().getId());
        resultDTO.setPolicyNumber(claimFuneral.getPolicyNumber());
        resultDTO.setIdNumber(claimFuneral.getIdNumber());
        resultDTO.setNameOfInsured(claimFuneral.getNameOfInsured());
        resultDTO.setTelephoneNumber(claimFuneral.getTelephoneNumber());
        resultDTO.setEmail(claimFuneral.getEmail());

        resultDTO.setClaimantFirstName(claimFuneral.getClaimantFirstName());
        resultDTO.setClaimantLastName(claimFuneral.getClaimantLastName());
        resultDTO.setClaimantIdNumber(claimFuneral.getClaimantIdNumber());
        resultDTO.setClaimantEmail(claimFuneral.getClaimantEmail());
        resultDTO.setClaimantPostalAddress(toAddressResultDTO.convert(claimFuneral.getClaimantPostalAddress())
        );

        resultDTO.setDeceasedFirstName(claimFuneral.getDeceasedFirstName());
        resultDTO.setDeceasedLastName(claimFuneral.getDeceasedLastName());
        resultDTO.setDeceasedIdNumber(claimFuneral.getDeceasedIdNumber());
        resultDTO.setDeceasedRelationship(claimFuneral.getDeceasedRelationship());
        resultDTO.setDeceasedOccupation(claimFuneral.getDeceasedOccupation());
        resultDTO.setDeceasedEmployer(claimFuneral.getDeceasedEmployer());
        //resultDTO.setDateofDeath(claimFuneral.getDateofDeath());
        resultDTO.setPlaceOfDeath(claimFuneral.getPlaceOfDeath());

        resultDTO.setAddressOfDeath(toAddressResultDTO.convert(claimFuneral.getAddressOfDeath()));
        resultDTO.setContactPlaceOfDeath(claimFuneral.getContactPlaceOfDeath());
        resultDTO.setCauseOfDeath(claimFuneral.getCauseOfDeath());
        //resultDTO.setDeathPlace(claimFuneral.getDeathPlace());
        resultDTO.setPlaceOfDeath(claimFuneral.getPlaceOfDeath());

        resultDTO.setPalourName(claimFuneral.getPalourName());
        resultDTO.setPalourAddress(toAddressResultDTO.convert(claimFuneral.getPalourAddress()));
        resultDTO.setPalourContactNumber(claimFuneral.getPalourContactNumber());

//        resultDTO.setPoliceStation(claimFuneral.getPoliceStation());
//        resultDTO.setPoliceStationAddress(toAddressResultDTO.convert(claimFuneral.getPoliceStationAddress()));
//        resultDTO.setPoliceCaseNumber(claimFuneral.getPoliceCaseNumber());
//        resultDTO.setContactInvestigatingOfficer(claimFuneral.getContactInvestigatingOfficer());
//        resultDTO.setNameInvestigatingOfficer(claimFuneral.getNameInvestigatingOfficer());
//        resultDTO.setMedicalAttendant(claimFuneral.getMedicalAttendant());
//        resultDTO.setContactNumberMedicalAttendant(claimFuneral.getContactNumberMedicalAttendant());
//        resultDTO.setAddressMedicalAttendant(
//                toAddressResultDTO.convert(claimFuneral.getAddressMedicalAttendant())
//        );

        resultDTO.setClaimStatus(claimFuneral.getClaimStatus().toString());

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
