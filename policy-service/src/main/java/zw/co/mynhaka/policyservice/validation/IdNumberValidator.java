package zw.co.mynhaka.policyservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdNumberValidator implements
        ConstraintValidator<IdNumberConstraint, String> {
    @Override
    public void initialize(IdNumberConstraint idNumber) { }

    @Override
    public boolean isValid(String idNumberField, ConstraintValidatorContext constraintValidatorContext) {
        String idNumber = idNumberField.toUpperCase().replace("-","");
        return idNumber.matches("[0-9]{9}[A-Z][0-9]{2}") || idNumber.matches("[0-9]{8}[A-Z][0-9]{2}");
    }
}
