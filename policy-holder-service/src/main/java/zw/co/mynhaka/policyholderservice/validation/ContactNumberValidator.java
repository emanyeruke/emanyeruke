package zw.co.mynhaka.policyholderservice.validation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

    @Override
    public boolean isValid(String contactNumber, ConstraintValidatorContext constraintValidatorContext) {
        return contactNumber != null && contactNumber.matches("[0-9]+")
                && (contactNumber.length() > 3) && (contactNumber.length() < 15);
    }
}
