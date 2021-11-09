package zw.co.mynhaka.polad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class CommissionReport extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agentId;

    private Long managerId;

    @ManyToOne
    private Payment payment;

    private BigDecimal amount;

    private Boolean status;
}
