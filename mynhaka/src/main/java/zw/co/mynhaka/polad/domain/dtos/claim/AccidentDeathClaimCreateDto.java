package zw.co.mynhaka.polad.domain.dtos.claim;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressCreateDto;
import zw.co.mynhaka.polad.domain.enums.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class AccidentDeathClaimCreateDto {

    private String policyNumber;


    private String idNumber;

    private String nameOfInsured;


    private String telephoneNumber;

    @Email
    private String email;

    private String claimantFirstName;

    private String claimantLastName;


    private String claimantIdNumber;


    private String claimantTelephoneNumber;

    @Email
    private String claimantEmail;

    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @NotNull
    private String relationship;

    private AddressCreateDto claimantPostalAddress;

    private String deceasedFirstName;

    private String deceasedLastName;


    private String deceasedIdNumber;

    private String deceasedRelationship;

    private String deceasedOccupation;

    private String deceasedEmployer;

    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfDeath;

    private String placeOfDeath;

    private AddressCreateDto addressOfDeath;

    private String contactPlaceOfDeath;

    private String causeOfDeath;

    private String deathPlace;

    private String placeOfBurial;


    private String palourName;

    private AddressCreateDto palourAddress;

    private String palourContactNumber;

//    private String policeStation;

//    private AddressCreateDto policeStationAddress;

//    private String policeCaseNumber;

//    private String nameInvestigatingOfficer;

//    private String contactInvestigatingOfficer;

//    private String medicalAttendant;

//    private AddressCreateDto addressMedicalAttendant;

//    private String contactNumberMedicalAttendant;

    private BankPayOutMethod bankPayOutMethod;

    private MobilePayOutMethod mobilePayOutMethod;

    private Boolean cashPayOutMethod;

    /**
     * Documents
     */
    private String claimantCertificate;

    private String burialOrder;

    private String deathCertificate;

    private String policyDocument;

    private String marriageCertificate;

    private String nationalID;

    private String birthCertificate;

    private String administrationLetter;

    private String medicalReportDocument;

    private String policeReport;

    private String certificationAuthority;

    private String certificateMedicalAttendant;

    private String mastersRelease;

}
