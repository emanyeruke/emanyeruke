package zw.co.mynhaka.polad.domain.dtos.claim;

import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressCreateDto;
import zw.co.mynhaka.polad.domain.enums.AccidentClaimReason;
import zw.co.mynhaka.polad.domain.enums.AccidentLocation;
import zw.co.mynhaka.polad.domain.enums.Gender;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class ClaimAccidentUpdateDTO {
    private Long id;

    private long beneficiaryId;

    private long policyAccidentId;

    private String policyNumber;

    private String claimantName;

    private Gender gender;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Optional<LocalDate> dob;

    private String relationship;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateofInjury;

    private AccidentLocation accidentLocation;

    private Boolean similarCondition;

    private Boolean hospitalAdmission;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate hospitalisationDate;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate admission;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate released;

    private String descriptionAccident;

    private String primaryPhysicianName;

    private @Valid
    AddressCreateDto primaryPhysicianAddress;

    @Email
    private String primaryPhysicianEmail;

    private String primaryPhysicianTelephone;

    private String primaryPhysicianFax;

    private String referringPhysicianName;

    private @Valid
    AddressCreateDto referringPhysicianAddress;

    @Email
    private String referringPhysicianEmail;

    private String referringPhysicianTelephone;

    private String referringPhysicianFax;

    private AccidentClaimReason accidentClaimReason;

}
