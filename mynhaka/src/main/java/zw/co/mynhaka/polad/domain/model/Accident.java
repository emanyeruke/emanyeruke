package zw.co.mynhaka.polad.domain.model;


import lombok.*;
import zw.co.mynhaka.polad.domain.enums.ProductType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, of = {"adminFee"})
@ToString(exclude = "accidentProduct")
public class Accident extends Product {
    @OneToMany(
            mappedBy = "accident",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private Set<AccidentProduct> accidentProduct = new HashSet<>();

    private double adminFee;

    public Accident(String name, ProductType productType, double adminFee) {
        super(name, productType);
        this.adminFee = adminFee;
    }
}
