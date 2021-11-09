package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import zw.co.mynhaka.polad.domain.enums.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Indexed
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
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

    @Field
    @Column(nullable = false)
    private String firstname;

    @Field
    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Field
    @Column(unique = true, length = 12, nullable = false)
    private String idNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String workTelephone;

    @Field
    @Column(nullable = false)
    private String mobile;

    @Field
    @Column(unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employer employer;


    @Field
    private String employeeNumber;

    @Field
    @Column(nullable = false)
    private String occupation;

    private double balance;

    private Address physicalAddress;

    private Address postalAddress;

    @Enumerated(EnumType.STRING)
    private PersonType personType = PersonType.PRINCIPAL;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @Enumerated(EnumType.STRING)
    private PolicyHolderStatus status = PolicyHolderStatus.ACTIVE;
}
