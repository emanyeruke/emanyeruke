package zw.co.mynhaka.policyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.policyservice.domain.enums.SurrenderStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SavingsSurrender extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SavingsPolicy savingsPolicy;

    private LocalDate effectiveDate;

    private String identityDocument;

    private BankingDetails bankingDetails;

    private SurrenderStatus surrenderStatus = SurrenderStatus.NEW;
}
