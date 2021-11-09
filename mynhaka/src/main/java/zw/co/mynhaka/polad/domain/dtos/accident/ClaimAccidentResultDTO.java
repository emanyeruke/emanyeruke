package zw.co.mynhaka.polad.domain.dtos.accident;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressResultDTO;
import zw.co.mynhaka.polad.domain.enums.AccidentClaimReason;
import zw.co.mynhaka.polad.domain.enums.AccidentLocation;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class ClaimAccidentResultDTO {

    private Long id;

    private Long policyId;

    private String policyNumber;

    private String claimantName;

    private Gender gender;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    private String relationship;

    @PastOrPresent
    private LocalDate dateofInjury;

    private AccidentLocation accidentLocation;

    private Boolean similarCondition;

    private Boolean hospitalAdmission;

    @PastOrPresent
    private LocalDate hospitalisationDate;

    @PastOrPresent
    private LocalDate admission;

    @PastOrPresent
    private LocalDate released;

    private String descriptionAccident;

    private String primaryPhysicianName;

    private AddressResultDTO primaryPhysicianAddress;

    @Email
    private String primaryPhysicianEmail;

    @ContactNumberConstraint
    private String primaryPhysicianTelephone;

    @ContactNumberConstraint
    private String primaryPhysicianFax;

    private String referringPhysicianName;

    private AddressResultDTO referringPhysicianAddress;

    @Email
    private String referringPhysicianEmail;

    @ContactNumberConstraint
    private String referringPhysicianTelephone;

    @ContactNumberConstraint
    private String referringPhysicianFax;

    private AccidentClaimReason accidentClaimReason;

    private String claimStatus;
}
