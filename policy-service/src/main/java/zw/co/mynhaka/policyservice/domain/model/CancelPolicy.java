package zw.co.mynhaka.policyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.policyservice.domain.enums.Reason;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CancelPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String policyNumber;

    @DateTimeFormat( pattern = "dd/MM/yyyy")
    private LocalDate effectiveDate;

    private String moreInformation;

    private Reason reason;

    private LocalDate submissionDate;
}
