package zw.co.mynhaka.polad.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class BankingDetails {
    private String bankName;

    private String branch;

    private String accountName;

    private String accountNumber;
}
