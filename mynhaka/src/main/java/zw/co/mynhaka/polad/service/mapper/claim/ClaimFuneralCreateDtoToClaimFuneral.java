package zw.co.mynhaka.polad.service.mapper.claim;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.model.Address;
import zw.co.mynhaka.polad.domain.model.ClaimFuneral;
import zw.co.mynhaka.polad.service.iface.PolicyFuneralService;


@Component
public class ClaimFuneralCreateDtoToClaimFuneral implements Converter<DeathClaimCreateDto, ClaimFuneral> {

    private final PolicyFuneralService policyFuneralService;

    public ClaimFuneralCreateDtoToClaimFuneral(final PolicyFuneralService policyFuneralService) {
        this.policyFuneralService = policyFuneralService;
    }

    @Override
    public ClaimFuneral convert(DeathClaimCreateDto deathClaimCreateDto) {

        ClaimFuneral claimFuneral = new ClaimFuneral();

        claimFuneral.setPolicyFuneral(policyFuneralService.findByPolicyNumber(deathClaimCreateDto.getPolicyNumber()));

        claimFuneral.setPolicyNumber(deathClaimCreateDto.getPolicyNumber());
        claimFuneral.setIdNumber(deathClaimCreateDto.getIdNumber());
        claimFuneral.setNameOfInsured(deathClaimCreateDto.getNameOfInsured());
        claimFuneral.setTelephoneNumber(deathClaimCreateDto.getTelephoneNumber());
        claimFuneral.setEmail(deathClaimCreateDto.getEmail());

        claimFuneral.setClaimantFirstName(deathClaimCreateDto.getClaimantFirstName());
        claimFuneral.setClaimantLastName(deathClaimCreateDto.getClaimantLastName());
        claimFuneral.setClaimantIdNumber(deathClaimCreateDto.getClaimantIdNumber());
        claimFuneral.setClaimantEmail(deathClaimCreateDto.getClaimantEmail());
        claimFuneral.setClaimantPostalAddress(
                new Address(deathClaimCreateDto.getClaimantPostalAddress().getStreet(),
                        deathClaimCreateDto.getClaimantPostalAddress().getSuburb(),
                        deathClaimCreateDto.getClaimantPostalAddress().getCity())
        );

        claimFuneral.setDeceasedFirstName(deathClaimCreateDto.getDeceasedFirstName());
        claimFuneral.setDeceasedLastName(deathClaimCreateDto.getDeceasedLastName());
        claimFuneral.setDeceasedIdNumber(deathClaimCreateDto.getDeceasedIdNumber());
        claimFuneral.setDeceasedRelationship(deathClaimCreateDto.getDeceasedRelationship());
        claimFuneral.setDeceasedOccupation(deathClaimCreateDto.getDeceasedOccupation());
        claimFuneral.setDeceasedEmployer(deathClaimCreateDto.getDeceasedEmployer());
        claimFuneral.setDateOfDeath(deathClaimCreateDto.getDateOfDeath());
        claimFuneral.setPlaceOfDeath(deathClaimCreateDto.getPlaceOfDeath());

        claimFuneral.setAddressOfDeath(new Address(
                deathClaimCreateDto.getAddressOfDeath().getStreet(),
                deathClaimCreateDto.getAddressOfDeath().getSuburb(),
                deathClaimCreateDto.getAddressOfDeath().getCity()
        ));
        claimFuneral.setContactPlaceOfDeath(deathClaimCreateDto.getContactPlaceOfDeath());
        //claimFuneral.setCauseOfDeath(deathClaimCreateDto.getCauseOfDeath());
      //  claimFuneral.setDeathPlace(deathClaimCreateDto.getDeathPlace());
        claimFuneral.setPlaceOfDeath(deathClaimCreateDto.getPlaceOfDeath());

        claimFuneral.setPalourName(deathClaimCreateDto.getPalourName());
        claimFuneral.setPalourAddress(new Address(
                deathClaimCreateDto.getPalourAddress().getStreet(),
                deathClaimCreateDto.getPalourAddress().getSuburb(),
                deathClaimCreateDto.getPalourAddress().getCity()
        ));
        claimFuneral.setPalourContactNumber(deathClaimCreateDto.getPalourContactNumber());
//
//        claimFuneral.setPoliceStation(deathClaimCreateDto.getPoliceStation());
//        claimFuneral.setPoliceStationAddress(new Address(
//                deathClaimCreateDto.getPoliceStationAddress().getStreet(),
//                deathClaimCreateDto.getPoliceStationAddress().getSuburb(),
//                deathClaimCreateDto.getPoliceStationAddress().getCity()
//        ));
//        claimFuneral.setPoliceCaseNumber(deathClaimCreateDto.getPoliceCaseNumber());
//        claimFuneral.setContactInvestigatingOfficer(deathClaimCreateDto.getContactInvestigatingOfficer());
//        claimFuneral.setNameInvestigatingOfficer(deathClaimCreateDto.getNameInvestigatingOfficer());
//        claimFuneral.setMedicalAttendant(deathClaimCreateDto.getMedicalAttendant());
//        claimFuneral.setContactNumberMedicalAttendant(deathClaimCreateDto.getContactNumberMedicalAttendant());
//        claimFuneral.setAddressMedicalAttendant(
//                new Address(
//                        deathClaimCreateDto.getAddressMedicalAttendant().getStreet(),
//                        deathClaimCreateDto.getAddressMedicalAttendant().getSuburb(),
//                        deathClaimCreateDto.getAddressMedicalAttendant().getCity()
//                )
//        );

        return claimFuneral;
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
