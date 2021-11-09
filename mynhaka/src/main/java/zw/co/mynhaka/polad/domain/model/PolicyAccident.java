package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Indexed
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NamedEntityGraph(
        name = "PolicyAccident.policyAccidentProduct",
        attributeNodes = {
                @NamedAttributeNode("accidentProduct"),
                @NamedAttributeNode("beneficiaryList")
        }
)
public class PolicyAccident extends Policy {

    @ManyToOne//(fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AccidentProduct accidentProduct;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "policyAccident", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BeneficiaryAccident> beneficiaryList = new HashSet<>();


}
