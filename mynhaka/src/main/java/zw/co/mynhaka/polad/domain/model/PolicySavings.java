package zw.co.mynhaka.polad.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Indexed
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedEntityGraph(
        name = "PolicySavings.savingsProduct",
        attributeNodes = {
                @NamedAttributeNode("savingsProduct"),
                @NamedAttributeNode("beneficiaryList")
        }
)
public class PolicySavings extends Policy {

    @ManyToOne//(fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SavingsProduct savingsProduct;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "policySavings",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BeneficiarySavings> beneficiaryList = new HashSet<>();

    private double balance;


}
