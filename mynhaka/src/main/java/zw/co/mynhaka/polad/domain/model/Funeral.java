package zw.co.mynhaka.polad.domain.model;


import lombok.*;
import zw.co.mynhaka.polad.domain.enums.ProductType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false, of = {"adminFee"})
@ToString(exclude = "funeralProduct")
public class Funeral extends Product {
    @OneToMany(mappedBy = "funeral",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<FuneralProduct> funeralProduct = new HashSet<>();

    private double adminFee;

    public Funeral(String name, ProductType productType, double adminFee) {
        super(name, productType);
        this.adminFee = adminFee;
    }
}
