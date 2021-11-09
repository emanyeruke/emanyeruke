package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FinancialAdvisor extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String surname;

    @Column(unique = true)
    private String email;

    private String contactNumber;

    //private String reference;

   /* @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private PolicyHolder policyHolder;


   @OneToMany(mappedBy = "financialAdvisor", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonBackReference
   private Set<PolicyHolder> policyHolders = new HashSet<>();

    */

}
