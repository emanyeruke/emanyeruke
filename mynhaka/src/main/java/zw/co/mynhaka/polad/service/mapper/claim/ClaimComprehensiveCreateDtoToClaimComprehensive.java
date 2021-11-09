package zw.co.mynhaka.polad.service.mapper.claim;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.model.Address;
import zw.co.mynhaka.polad.domain.model.ClaimComprehensive;
import zw.co.mynhaka.polad.service.iface.PolicyComprehensiveService;


@Component
public class ClaimComprehensiveCreateDtoToClaimComprehensive implements Converter<DeathClaimCreateDto, ClaimComprehensive> {


    private final PolicyComprehensiveService policyComprehensiveService;

    public ClaimComprehensiveCreateDtoToClaimComprehensive(final PolicyComprehensiveService policyComprehensiveService) {
        this.policyComprehensiveService = policyComprehensiveService;
    }


    @Override
    public ClaimComprehensive convert(DeathClaimCreateDto deathClaimCreateDto) {
        ClaimComprehensive claimComprehensive = new ClaimComprehensive();

        claimComprehensive.setPolicyComprehensive(policyComprehensiveService.findByPolicyNumber(deathClaimCreateDto.getPolicyNumber()));
        claimComprehensive.setPolicyNumber(deathClaimCreateDto.getPolicyNumber());
        claimComprehensive.setIdNumber(deathClaimCreateDto.getIdNumber());
        claimComprehensive.setNameOfInsured(deathClaimCreateDto.getNameOfInsured());
        claimComprehensive.setTelephoneNumber(deathClaimCreateDto.getTelephoneNumber());
        claimComprehensive.setEmail(deathClaimCreateDto.getEmail());

        claimComprehensive.setClaimantFirstName(deathClaimCreateDto.getClaimantFirstName());
        claimComprehensive.setClaimantLastName(deathClaimCreateDto.getClaimantLastName());
        claimComprehensive.setClaimantIdNumber(deathClaimCreateDto.getClaimantIdNumber());
        claimComprehensive.setClaimantEmail(deathClaimCreateDto.getClaimantEmail());
        claimComprehensive.setClaimantPostalAddress(
                new Address(deathClaimCreateDto.getClaimantPostalAddress().getStreet(),
                        deathClaimCreateDto.getClaimantPostalAddress().getSuburb(),
                        deathClaimCreateDto.getClaimantPostalAddress().getCity())
        );

        claimComprehensive.setDeceasedFirstName(deathClaimCreateDto.getDeceasedFirstName());
        claimComprehensive.setDeceasedLastName(deathClaimCreateDto.getDeceasedLastName());
        claimComprehensive.setDeceasedIdNumber(deathClaimCreateDto.getDeceasedIdNumber());
        claimComprehensive.setDeceasedRelationship(deathClaimCreateDto.getDeceasedRelationship());
        claimComprehensive.setDeceasedOccupation(deathClaimCreateDto.getDeceasedOccupation());
        claimComprehensive.setDeceasedEmployer(deathClaimCreateDto.getDeceasedEmployer());
        claimComprehensive.setDateOfDeath(deathClaimCreateDto.getDateOfDeath());
        claimComprehensive.setPlaceOfDeath(deathClaimCreateDto.getPlaceOfDeath());

        claimComprehensive.setAddressOfDeath(new Address(
                deathClaimCreateDto.getAddressOfDeath().getStreet(),
                deathClaimCreateDto.getAddressOfDeath().getSuburb(),
                deathClaimCreateDto.getAddressOfDeath().getCity()
        ));
        claimComprehensive.setContactPlaceOfDeath(deathClaimCreateDto.getContactPlaceOfDeath());
        //claimComprehensive.setCauseOfDeath(deathClaimCreateDto.getCauseOfDeath());
        claimComprehensive.setDeathPlace(deathClaimCreateDto.getDeathPlace());
        claimComprehensive.setPlaceOfDeath(deathClaimCreateDto.getPlaceOfDeath());

        claimComprehensive.setPalourName(deathClaimCreateDto.getPalourName());
        claimComprehensive.setPalourAddress(new Address(
                deathClaimCreateDto.getPalourAddress().getStreet(),
                deathClaimCreateDto.getPalourAddress().getSuburb(),
                deathClaimCreateDto.getPalourAddress().getCity()
        ));
//        claimComprehensive.setPalourContactNumber(deathClaimCreateDto.getPalourContactNumber());
//
//        claimComprehensive.setPoliceStation(deathClaimCreateDto.getPoliceStation());
//        claimComprehensive.setPoliceStationAddress(new Address(
//                deathClaimCreateDto.getPoliceStationAddress().getStreet(),
//                deathClaimCreateDto.getPoliceStationAddress().getSuburb(),
//                deathClaimCreateDto.getPoliceStationAddress().getCity()
//        ));
//        claimComprehensive.setPoliceCaseNumber(deathClaimCreateDto.getPoliceCaseNumber());
//        claimComprehensive.setContactInvestigatingOfficer(deathClaimCreateDto.getContactInvestigatingOfficer());
//        claimComprehensive.setNameInvestigatingOfficer(deathClaimCreateDto.getNameInvestigatingOfficer());
//        claimComprehensive.setMedicalAttendant(deathClaimCreateDto.getMedicalAttendant());
//        claimComprehensive.setContactNumberMedicalAttendant(deathClaimCreateDto.getContactNumberMedicalAttendant());
//        claimComprehensive.setAddressMedicalAttendant(
//                new Address(
//                        deathClaimCreateDto.getAddressMedicalAttendant().getStreet(),
//                        deathClaimCreateDto.getAddressMedicalAttendant().getSuburb(),
//                        deathClaimCreateDto.getAddressMedicalAttendant().getCity()
//                )
//        );

        return claimComprehensive;
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
