package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;

@Data
public class MobileMoneyDetailsCreateDTO {
    @ContactNumberConstraint
    private String mobileNumber;

    private String mobileAccountName;
}