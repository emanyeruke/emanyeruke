package zw.co.mynhaka.polad.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import zw.co.mynhaka.polad.domain.enums.AccidentClaimReason;
import zw.co.mynhaka.polad.domain.enums.ClaimStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@AttributeOverrides({
        @AttributeOverride(
                name = "claimantPostalAddress.street",
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
        ),
//        @AttributeOverride(
//                name = "policeStationAddress.street",
//                column = @Column(name = "police_station_address_street")
//        ),
//        @AttributeOverride(
//                name = "policeStationAddress.suburb",
//                column = @Column(name = "police_station_address_suburb")
//        ),
//        @AttributeOverride(
//                name = "policeStationAddress.city",
//                column = @Column(name = "police_station_address_city")
//        ),
//        @AttributeOverride(
//                name = "addressMedicalAttendant.street",
//                column = @Column(name = "address_medical_attendant_street")
//        ),
//        @AttributeOverride(
//                name = "addressMedicalAttendant.suburb",
//                column = @Column(name = "address_medical_attendant_suburb")
//        ),
//        @AttributeOverride(
//                name = "addressMedicalAttendant.city",
//                column = @Column(name = "address_medical_attendant_city")
//        )
})
public class ClaimComprehensive extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PolicyComprehensive policyComprehensive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PolicyHolder policyHolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Beneficiary beneficiary; //need to get beneficiary details


    private LocalDate dateOfDeath;

    @Column(length = 30)
    private String policyNumber;

    @Column(length = 15)
    private String idNumber;

    @Column(length = 50)
    private String nameOfInsured;

    @Column(length = 15)
    private String telephoneNumber;

    @Column(length = 50)
    private String email;


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

    @Column(length = 100)
    private Address claimantPostalAddress;

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

//    @Column(length = 50)
//    private String policeStation;
//
//    private Address policeStationAddress;
//
//    @Column(length = 15)
//    private String policeCaseNumber;
//
//    @Column(length = 50)
//    private String nameInvestigatingOfficer;
//
//    @Column(length = 15)
//    private String contactInvestigatingOfficer;
//
//    @Column(length = 50)
//    private String medicalAttendant;
//
//    private Address addressMedicalAttendant;
//
//    @Column(length = 15)
//    private String contactNumberMedicalAttendant;

    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus = ClaimStatus.INITIATED;

    private BigDecimal amount;
    private Instant dateClaimPaid;

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

    @Enumerated(EnumType.STRING)
    private AccidentClaimReason accidentClaimReason;

    @CreationTimestamp
    private Timestamp dateCreated;

    private double sumAssured;

}
