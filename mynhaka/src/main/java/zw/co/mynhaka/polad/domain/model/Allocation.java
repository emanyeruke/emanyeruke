package zw.co.mynhaka.polad.domain.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allocation extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Payment payment;

    @OneToOne
    private PolicyAccident policyAccident;

    @OneToOne
    private PolicyFuneral policyFuneral;

    @OneToOne
    private PolicyComprehensive policyComprehensive;

    @OneToOne
    private PolicySavings policySavings;

    private BigDecimal amount;

    @OneToOne(orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "transaction_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Transaction transaction;
}
