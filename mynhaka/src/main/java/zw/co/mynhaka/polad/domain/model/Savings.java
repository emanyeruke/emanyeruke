package zw.co.mynhaka.polad.domain.model;


import lombok.*;
import zw.co.mynhaka.polad.domain.enums.ProductType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true, of = {"adminFee"})
@ToString(exclude = "savingsProduct")
public class Savings extends Product {
    /*@OneToMany(mappedBy = "savings",
            cascade = CascadeType.ALL,
            orphanRemoval = true)

     */

    @OneToMany(mappedBy = "savings", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<SavingsProduct> savingsProduct = new HashSet<>();

    private BigDecimal adminFee;

    public Savings(String name, ProductType productType, BigDecimal adminFee) {
        super(name, productType);
        this.adminFee = adminFee;
    }
}
