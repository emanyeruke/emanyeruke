package zw.co.mynhaka.polad.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.*;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(
                name = "primaryPhysicianAddress.street",
                column = @Column(name = "primary_physician_address_street")
        ),
        @AttributeOverride(
                name = "primaryPhysicianAddress.suburb",
                column = @Column(name = "primary_physician_address_suburb")
        ),
        @AttributeOverride(
                name = "primaryPhysicianAddress.city",
                column = @Column(name = "primary_physician_address_city")
        ),
        @AttributeOverride(
                name = "referringPhysicianAddress.street",
                column = @Column(name = "referring_physician_address_street")
        ),
        @AttributeOverride(
                name = "referringPhysicianAddress.suburb",
                column = @Column(name = "referring_physician_address_suburb")
        ),
        @AttributeOverride(
                name = "referringPhysicianAddress.city",
                column = @Column(name = "referring_physician_address_city")
        ),
        @AttributeOverride(name = "claimantPostalAddress.street",
        column = @Column(name = "claimant_postal_address_street")
),
@AttributeOverride(
        name = "claimantPostalAddress.suburb",
        column = @Column(name = "claimant_postal_address_suburb")
),
@AttributeOverride(
        name = "claimantPostalAddress.city",
        column = @Column(name = "claimant_postal_address_city")
),
@AttributeOverride(
        name = "addressOfDeath.street",
        column = @Column(name = "address_of_death_street")
),
@AttributeOverride(
        name = "addressOfDeath.suburb",
        column = @Column(name = "address_of_death_suburb")
),
@AttributeOverride(
        name = "addressOfDeath.city",
        column = @Column(name = "address_of_death_city")
),
@AttributeOverride(
        name = "palourAddress.street",
        column = @Column(name = "palour_address_street")
),
@AttributeOverride(
        name = "palourAddress.suburb",
        column = @Column(name = "palour_address_suburb")
),
@AttributeOverride(
        name = "palourAddress.city",
        column = @Column(name = "palour_address_city")
)
})
public class ClaimAccident extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PolicyAccident policyAccident;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PolicyHolder policyHolder;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Beneficiary beneficiary; //need to get beneficiary details given it's the deceased


   /* @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PolicyHolder policyHolder;

    */

    private String policyNumber;

    private LocalDate dob;


    @Enumerated(EnumType.STRING)
    private AccidentClaimType accidentClaimType;

    //claimant info
    @Column(length = 30)
    private String claimantFirstName;

    @Column(length = 30)
    private String claimantLastName;

    @Column(length = 15)
    private String claimantIdNumber;


    @Column(length = 15)
    private String claimantTelephoneNumber;

    @Column(length = 50)
    private String claimantEmail;

    private ClaimantType claimantType;

    private Gender gender;


    private String relationship;

    @Column(length = 100)
    private Address claimantPostalAddress;


    private LocalDate dateofInjury;
    private AccidentLocation accidentLocation;
    private Boolean similarCondition;
    private Boolean hospitalAdmission;
    private LocalDate hospitalisationDate;
    private LocalDate admission;
    private LocalDate released;
    private String descriptionAccident;

    //primary physician info
    private String primaryPhysicianName;
    private Address primaryPhysicianAddress;
    private String primaryPhysicianEmail;
    private String primaryPhysicianTelephone;
    private String primaryPhysicianFax;

    //referring physician info
    private String referringPhysicianName;
    private Address referringPhysicianAddress;
    private String referringPhysicianEmail;
    private String referringPhysicianTelephone;
    private String referringPhysicianFax;

    @Enumerated(EnumType.STRING)
    private AccidentClaimReason accidentClaimReason;

    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus = ClaimStatus.INITIATED;

 //capturing death details.

    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfDeath;


    @Column(length = 15)
    private String idNumber;

    @Column(length = 50)
    private String nameOfInsured;

    @Column(length = 15)
    private String telephoneNumber;

    @Column(length = 50)
    private String email;

 @Column(length = 30)
 private String deceasedFirstName;

    @Column(length = 30)
    private String deceasedLastName;

    @Column(length = 15)
    private String deceasedIdNumber;

    @Column(length = 15)
    private String deceasedRelationship;

    @Column(length = 30)
    private String deceasedOccupation;

    @Column(length = 30)
    private String deceasedEmployer;


    @Column(length = 50)
    private String placeOfDeath;

    private Address addressOfDeath;

    @Column(length = 50)
    private String contactPlaceOfDeath;

    @Column(length = 30)
    private String causeOfDeath;

    @Column(length = 50)
    private String deathPlace;

    @Column(length = 50)
    private String placeOfBurial;

    @Column(length = 50)
    private String palourName;

    private Address palourAddress;

    @Column(length = 15)
    private String palourContactNumber;

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



    private BigDecimal amount;

    private Instant dateClaimPaid;

    @CreationTimestamp
    private Timestamp dateCreated;

    private double sumAssured;



}
