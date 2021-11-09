package zw.co.mynhaka.policyholderservice.domain.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import zw.co.mynhaka.policyholderservice.domain.enums.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

//@Indexed Hibernate Search
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AttributeOverrides({
        @AttributeOverride(
                name = "physicalAddress.street",
                column = @Column(name = "physical_address_street")
        ),
        @AttributeOverride(
                name = "physicalAddress.suburb",
                column = @Column(name = "physical_address_suburb")
        ),
        @AttributeOverride(
                name = "physicalAddress.city",
                column = @Column(name = "physical_address_city")
        ),
        @AttributeOverride(
                name = "postalAddress.street",
                column = @Column(name = "postal_address_street")
        ),
        @AttributeOverride(
                name = "postalAddress.suburb",
                column = @Column(name = "postal_address_suburb")
        ),
        @AttributeOverride(
                name = "postalAddress.city",
                column = @Column(name = "postal_address_city")
        )
})
public class PolicyHolder extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Title title;

//    @Field
    @Column(nullable = false)
    private String firstname;

//    @Field
    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

//    @Field
    @Column(unique = true, length = 12, nullable = false)
    private String idNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String workTelephone;

//    @Field
    @Column(nullable = false)
    private String mobile;

//    @Field
    @Column(unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employer employer;

//    @Field
    private String employeeNumber;

//    @Field
    @Column(nullable = false)
    private String occupation;

    private BigDecimal balance = BigDecimal.ZERO;

    private Address physicalAddress;

    private Address postalAddress;

    @Enumerated(EnumType.STRING)
    private PremiumPayer premiumPayer;

    @Enumerated(EnumType.STRING)
    private PersonType personType = PersonType.PRINCIPAL;

    @Enumerated(EnumType.STRING)
    private PolicyHolderStatus status = PolicyHolderStatus.ACTIVE;
}
