package zw.co.mynhaka.polad.service.mapper.claim;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.claim.ClaimAccidentCreateDto;
import zw.co.mynhaka.polad.domain.model.Address;
import zw.co.mynhaka.polad.domain.model.ClaimAccident;
import zw.co.mynhaka.polad.service.iface.PolicyAccidentService;


@Component
public class ClaimAccidentCreateDtoToClaimAccident implements Converter<ClaimAccidentCreateDto, ClaimAccident> {

    private final PolicyAccidentService policyAccidentService;

    public ClaimAccidentCreateDtoToClaimAccident(final PolicyAccidentService policyAccidentService) {
        this.policyAccidentService = policyAccidentService;
    }

    @Override
    public ClaimAccident convert(ClaimAccidentCreateDto claimAccidentCreateDto) {

        ClaimAccident claimAccident = new ClaimAccident();

        //PolicyDetails
        claimAccident.setPolicyAccident(policyAccidentService.findByPolicyNumber(claimAccidentCreateDto.getPolicyNumber()));

        claimAccident.setPolicyNumber(claimAccidentCreateDto.getPolicyNumber());

        //Claimant Details
        //claimAccident.setClaimantName(claimAccidentCreateDto.getClaimantName());
        claimAccident.setRelationship(claimAccidentCreateDto.getRelationship());
        claimAccident.setGender(claimAccidentCreateDto.getGender());
        claimAccident.setDob(claimAccidentCreateDto.getDob());

        //Accident and Hospitalisation
        claimAccident.setAccidentClaimReason(claimAccidentCreateDto.getAccidentClaimReason());
        claimAccident.setAccidentLocation(claimAccidentCreateDto.getAccidentLocation());
        claimAccident.setAdmission(claimAccidentCreateDto.getAdmission());
        claimAccident.setAccidentClaimReason(claimAccidentCreateDto.getAccidentClaimReason());
        claimAccident.setDateofInjury(claimAccidentCreateDto.getDateofInjury());
        claimAccident.setDescriptionAccident(claimAccidentCreateDto.getDescriptionAccident());
        claimAccident.setReleased(claimAccidentCreateDto.getReleased());
        claimAccident.setSimilarCondition(claimAccidentCreateDto.getSimilarCondition());
        claimAccident.setHospitalAdmission(claimAccidentCreateDto.getHospitalAdmission());
        claimAccident.setHospitalisationDate(claimAccidentCreateDto.getHospitalisationDate());
        Address primaryPhysicianAddress = new Address(
                claimAccidentCreateDto.getPrimaryPhysicianAddress().getSuburb(),
                claimAccidentCreateDto.getPrimaryPhysicianAddress().getSuburb(),
                claimAccidentCreateDto.getPrimaryPhysicianAddress().getCity());

        claimAccident.setPrimaryPhysicianAddress(primaryPhysicianAddress);
        claimAccident.setPrimaryPhysicianEmail(claimAccidentCreateDto.getPrimaryPhysicianEmail());
        claimAccident.setPrimaryPhysicianFax(claimAccidentCreateDto.getPrimaryPhysicianFax());
        claimAccident.setPrimaryPhysicianName(claimAccidentCreateDto.getPrimaryPhysicianName());
        claimAccident.setPrimaryPhysicianTelephone(claimAccidentCreateDto.getPrimaryPhysicianTelephone());
        Address referringPhysicianAddress = new Address(
                claimAccidentCreateDto.getReferringPhysicianAddress().getSuburb(),
                claimAccidentCreateDto.getReferringPhysicianAddress().getSuburb(),
                claimAccidentCreateDto.getReferringPhysicianAddress().getCity());
        claimAccident.setReferringPhysicianAddress(referringPhysicianAddress);
        claimAccident.setReferringPhysicianEmail(claimAccidentCreateDto.getReferringPhysicianEmail());
        claimAccident.setReferringPhysicianFax(claimAccidentCreateDto.getReferringPhysicianFax());
        claimAccident.setReferringPhysicianName(claimAccidentCreateDto.getReferringPhysicianName());
        claimAccident.setReferringPhysicianTelephone(claimAccidentCreateDto.getReferringPhysicianTelephone());


        return claimAccident;
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
