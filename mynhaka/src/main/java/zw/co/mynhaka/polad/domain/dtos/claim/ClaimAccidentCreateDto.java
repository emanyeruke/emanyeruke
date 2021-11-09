package zw.co.mynhaka.polad.domain.dtos.claim;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressCreateDto;
import zw.co.mynhaka.polad.domain.enums.AccidentClaimReason;
import zw.co.mynhaka.polad.domain.enums.AccidentLocation;
import zw.co.mynhaka.polad.domain.enums.Gender;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Optional;

@Data
public class ClaimAccidentCreateDto {
    @NotNull
    private String policyNumber;

    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @NotNull
    private String relationship;

    private String claimantFirstName;

    private String claimantLastName;

    @NotNull
    private Gender gender;

    private String claimantIdNumber;

    private String claimantTelephoneNumber;

    @Email
    private String claimantEmail;

    private AddressCreateDto claimantPostalAddress;


    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateofInjury;

    @NotNull
    private AccidentLocation accidentLocation;

    @NotNull
    private Boolean similarCondition;

    @NotNull
    private Boolean hospitalAdmission;


    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate hospitalisationDate;


    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate admission;


    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate released;

    @NotNull
    private String descriptionAccident;

    @NotNull
    private String primaryPhysicianName;

    private @Valid
    AddressCreateDto primaryPhysicianAddress;

    @Email
    private String primaryPhysicianEmail;

    /* @ContactNumberConstraint*/
    private String primaryPhysicianTelephone;

    /* @ContactNumberConstraint*/
    private String primaryPhysicianFax;

    @NotNull
    private String referringPhysicianName;

    private @Valid
    AddressCreateDto referringPhysicianAddress;

    @Email
    private String referringPhysicianEmail;

    /*@ContactNumberConstraint*/
    private String referringPhysicianTelephone;

    /*@ContactNumberConstraint*/
    private String referringPhysicianFax;

    @NotNull
    private AccidentClaimReason accidentClaimReason;




}
