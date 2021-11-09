package zw.co.mynhaka.polad.domain.model;

import lombok.*;
import zw.co.mynhaka.polad.domain.enums.ProductType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "comprehensiveFuneralProduct")
public class ComprehensiveFuneral extends Product {
    @OneToMany(
            mappedBy = "comprehensiveFuneral",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<ComprehensiveFuneralProduct> comprehensiveFuneralProduct = new HashSet<>();

    public ComprehensiveFuneral(String name, ProductType productType) {
        super(name, productType);
    }
}
