package zw.co.mynhaka.policyservice.domain.dto.mobilemoney;

import lombok.Data;
import zw.co.mynhaka.policyservice.validation.ContactNumberConstraint;

@Data
public class MobileMoneyDetailsCreateDTO {
    @ContactNumberConstraint
    private String mobileNumber;

    private String accountName;
}
