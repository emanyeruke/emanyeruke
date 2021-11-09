package zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.dtos.claim.BankPayOutMethod;
import zw.co.mynhaka.polad.domain.dtos.claim.MobilePayOutMethod;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressResultDTO;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;
import zw.co.mynhaka.polad.validation.IdNumberConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class ClaimComprehensiveResultDTO {

    private Long id;

    private Long policyId;

    private String policyNumber;

    @IdNumberConstraint
    private String idNumber;

    private String nameOfInsured;

    @ContactNumberConstraint
    private String telephoneNumber;

    @Email
    private String email;

    private String claimantFirstName;

    private String claimantLastName;

    @IdNumberConstraint
    private String claimantIdNumber;

    @ContactNumberConstraint
    private String claimantTelephoneNumber;

    @Email
    private String claimantEmail;

    private AddressResultDTO claimantPostalAddress;

    private String deceasedFirstName;

    private String deceasedLastName;

    @IdNumberConstraint
    private String deceasedIdNumber;

    private String deceasedRelationship;

    private String deceasedOccupation;

    private String deceasedEmployer;

    @PastOrPresent
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateofDeath;

    private String placeOfDeath;

    private AddressResultDTO addressOfDeath;

    @ContactNumberConstraint
    private String contactPlaceOfDeath;

    private String causeOfDeath;

    private String deathPlace;

    private String placeOfBurial;

    private String palourName;

    private AddressResultDTO palourAddress;

    @ContactNumberConstraint
    private String palourContactNumber;

    private String policeStation;

    private AddressResultDTO policeStationAddress;

    private String policeCaseNumber;

    private String nameInvestigatingOfficer;

    private String contactInvestigatingOfficer;

    private String medicalAttendant;

    private AddressResultDTO addressMedicalAttendant;

    @ContactNumberConstraint
    private String contactNumberMedicalAttendant;

    private BankPayOutMethod bankPayOutMethod;

    private MobilePayOutMethod mobilePayOutMethod;

    private Boolean cashPayOutMethod;

    private String claimStatus;
}
