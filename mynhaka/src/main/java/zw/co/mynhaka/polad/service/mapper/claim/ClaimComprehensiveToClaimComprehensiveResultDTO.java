package zw.co.mynhaka.polad.service.mapper.claim;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ClaimComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.model.ClaimComprehensive;
import zw.co.mynhaka.polad.service.mapper.policyholder.AddressToAddressResultDTO;


@Component
public class ClaimComprehensiveToClaimComprehensiveResultDTO implements Converter<ClaimComprehensive, ClaimComprehensiveResultDTO> {

    private final AddressToAddressResultDTO toAddressResultDTO;

    public ClaimComprehensiveToClaimComprehensiveResultDTO(final AddressToAddressResultDTO toAddressResultDTO) {
        this.toAddressResultDTO = toAddressResultDTO;
    }

    @Override
    public ClaimComprehensiveResultDTO convert(ClaimComprehensive claimComprehensive) {

        ClaimComprehensiveResultDTO resultDTO = new ClaimComprehensiveResultDTO();
        resultDTO.setId(claimComprehensive.getId());
        resultDTO.setPolicyId(claimComprehensive.getPolicyComprehensive().getId());
        resultDTO.setPolicyNumber(claimComprehensive.getPolicyNumber());
        resultDTO.setIdNumber(claimComprehensive.getIdNumber());
        resultDTO.setNameOfInsured(claimComprehensive.getNameOfInsured());
        resultDTO.setTelephoneNumber(claimComprehensive.getTelephoneNumber());
        resultDTO.setEmail(claimComprehensive.getEmail());

        resultDTO.setClaimantFirstName(claimComprehensive.getClaimantFirstName());
        resultDTO.setClaimantLastName(claimComprehensive.getClaimantLastName());
        resultDTO.setClaimantIdNumber(claimComprehensive.getClaimantIdNumber());
        resultDTO.setClaimantEmail(claimComprehensive.getClaimantEmail());
        resultDTO.setClaimantPostalAddress(toAddressResultDTO.convert(claimComprehensive.getClaimantPostalAddress())
        );

        resultDTO.setDeceasedFirstName(claimComprehensive.getDeceasedFirstName());
        resultDTO.setDeceasedLastName(claimComprehensive.getDeceasedLastName());
        resultDTO.setDeceasedIdNumber(claimComprehensive.getDeceasedIdNumber());
        resultDTO.setDeceasedRelationship(claimComprehensive.getDeceasedRelationship());
        resultDTO.setDeceasedOccupation(claimComprehensive.getDeceasedOccupation());
        resultDTO.setDeceasedEmployer(claimComprehensive.getDeceasedEmployer());
        resultDTO.setDateofDeath(claimComprehensive.getDateOfDeath());
        resultDTO.setPlaceOfDeath(claimComprehensive.getPlaceOfDeath());

        resultDTO.setAddressOfDeath(toAddressResultDTO.convert(claimComprehensive.getAddressOfDeath()));
        resultDTO.setContactPlaceOfDeath(claimComprehensive.getContactPlaceOfDeath());
        resultDTO.setCauseOfDeath(claimComprehensive.getCauseOfDeath());
        resultDTO.setDeathPlace(claimComprehensive.getDeathPlace());
        resultDTO.setPlaceOfDeath(claimComprehensive.getPlaceOfDeath());

        resultDTO.setPalourName(claimComprehensive.getPalourName());
        resultDTO.setPalourAddress(toAddressResultDTO.convert(claimComprehensive.getPalourAddress()));
        resultDTO.setPalourContactNumber(claimComprehensive.getPalourContactNumber());

//        resultDTO.setPoliceStation(claimComprehensive.getPoliceStation());
//        resultDTO.setPoliceStationAddress(toAddressResultDTO.convert(claimComprehensive.getPoliceStationAddress()));
//        resultDTO.setPoliceCaseNumber(claimComprehensive.getPoliceCaseNumber());
//        resultDTO.setContactInvestigatingOfficer(claimComprehensive.getContactInvestigatingOfficer());
//        resultDTO.setNameInvestigatingOfficer(claimComprehensive.getNameInvestigatingOfficer());
//        resultDTO.setMedicalAttendant(claimComprehensive.getMedicalAttendant());
//        resultDTO.setContactNumberMedicalAttendant(claimComprehensive.getContactNumberMedicalAttendant());
//        resultDTO.setAddressMedicalAttendant(
//                toAddressResultDTO.convert(claimComprehensive.getAddressMedicalAttendant())
//        );

        resultDTO.setClaimStatus(claimComprehensive.getClaimStatus().toString());
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
