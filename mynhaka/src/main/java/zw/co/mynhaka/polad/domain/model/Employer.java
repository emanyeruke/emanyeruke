package zw.co.mynhaka.polad.domain.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AttributeOverrides({
        @AttributeOverride(
                name = "physicalAddress.street",
                column = @Column(name = "street")
        ),
        @AttributeOverride(
                name = "physicalAddress.suburb",
                column = @Column(name = "suburb")
        ),
        @AttributeOverride(
                name = "physicalAddress.city",
                column = @Column(name = "city")
        )
        ,
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
public class Employer extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

   /* private String representative;*/

    private Address physicalAddress;

    private Address postalAddress;


    @Column(unique = true)
    private String email;

    private String contactNumber;

    private double balance;

    @OneToMany(
            mappedBy = "employer",
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
    @JsonBackReference
    private Set<PolicyHolder> policyHolderList = new HashSet<>();

    @OneToMany(
            mappedBy = "employer",
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
    @JsonBackReference
    private Set<Representative> representatives = new HashSet<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "employer"
    )
    @JsonBackReference
    private Set<Invoice> invoices = new HashSet<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "invoice"
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Set<Payment> payments = new HashSet<>();
}
