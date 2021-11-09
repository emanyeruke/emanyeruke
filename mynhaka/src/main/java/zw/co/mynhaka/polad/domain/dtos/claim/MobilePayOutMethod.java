package zw.co.mynhaka.polad.domain.dtos.claim;

import lombok.Data;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;


@Data
public class MobilePayOutMethod {

    private String walletName;

    @ContactNumberConstraint
    private String mobileNumber;

    private String accountNumber;
}
