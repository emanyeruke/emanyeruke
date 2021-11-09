package zw.co.mynhaka.policyservice.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import zw.co.mynhaka.policyservice.domain.enums.ProductType;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"name", "productType"})
public class Product extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private BigDecimal adminFee;
}
