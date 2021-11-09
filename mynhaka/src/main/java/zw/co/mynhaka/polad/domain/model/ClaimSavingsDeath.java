package zw.co.mynhaka.polad.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.enums.ClaimStatus;
import zw.co.mynhaka.polad.domain.enums.DeathCause;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
})
public class ClaimSavingsDeath extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PolicySavings policySavings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PolicyHolder policyHolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Beneficiary beneficiary; //need to get beneficiary details, in case it's beneficiary that deceased

    @Enumerated(EnumType.STRING)
    private DeathCause deathCause;

    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
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


    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus = ClaimStatus.INITIATED;

    private BigDecimal amount;//total amount to be withdrawn

    private Instant dateClaimPaid;

    /*method to calculate total amount
    public void getTotalValue(){
        for (amount=policySavings.getSavingsProduct().getMonthlyInvestmentPremium();
             amount>=policySavings.getSavingsProduct().getMonthlyInvestmentPremium();amount++)

            amount += policySavings.getSavingsProduct().getMonthlyInvestmentPremium() * policySavings.getSavingsProduct().getMonthlyInterest();
    }

     */



}

