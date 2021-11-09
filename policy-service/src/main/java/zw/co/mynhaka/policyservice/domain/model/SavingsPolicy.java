package zw.co.mynhaka.policyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SavingsPolicy extends Policy {
    @ManyToOne
    private SavingsPlan savingsPlan;

    private BigDecimal balance = BigDecimal.ZERO;
}
