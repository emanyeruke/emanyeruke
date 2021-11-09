package zw.co.mynhaka.policyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BankingDetails {
    private String bankName;

    private String branch;

    private String accountName;

    private String accountNumber;
}
