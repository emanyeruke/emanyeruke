package zw.co.mynhaka.polad.domain.dtos.claim;

import lombok.Data;

@Data
public class BankPayOutMethod {
    private String bank;
    private String branch;
    private String accountNumber;
    private String accountName;
}
