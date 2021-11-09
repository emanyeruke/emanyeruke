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
        name = "PolicyFuneral.funeralProduct",
        attributeNodes = {
                @NamedAttributeNode("funeralProduct"),
                @NamedAttributeNode("beneficiaryList")
        }
)
public class PolicyFuneral extends Policy {

    @ManyToOne//(fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FuneralProduct funeralProduct;

    @OneToMany(
            mappedBy = "policyFuneral",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BeneficiaryFuneral> beneficiaryList = new HashSet<>();


}
