package zw.co.mynhaka.policyservice.domain.model;

import lombok.*;
import zw.co.mynhaka.policyservice.domain.enums.PersonType;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "uk_name_sum_assured_premium_person_type",
                columnNames = {
                        "name",
                        "sumAssured",
                        "premium",
                        "personType"
                }
        )
)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"product"})
@EqualsAndHashCode(callSuper = false, exclude = {"clawbackPeriod", "product"})
public class StandardFuneralPlan extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal sumAssured;

    private BigDecimal premium;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    private int clawbackPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
