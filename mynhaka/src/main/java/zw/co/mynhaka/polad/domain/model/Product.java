package zw.co.mynhaka.polad.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import zw.co.mynhaka.polad.domain.enums.ProductType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class Product extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    public Product(String name, ProductType productType) {
        this.name = name;
        this.productType = productType;
    }
}
