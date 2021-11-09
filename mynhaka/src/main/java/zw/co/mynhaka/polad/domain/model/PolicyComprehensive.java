package zw.co.mynhaka.polad.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NamedEntityGraph(
        name = "PolicyComprehensive.comprehensiveFuneralProduct",
        attributeNodes = {
                @NamedAttributeNode("comprehensiveFuneralProduct"),
                @NamedAttributeNode("beneficiaryList")
        }
)
public class PolicyComprehensive extends Policy {

    @ManyToOne//(fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ComprehensiveFuneralProduct comprehensiveFuneralProduct;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "policyComprehensive",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BeneficiaryComprehensive> beneficiaryList = new HashSet<>();


}
