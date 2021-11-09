package zw.co.mynhaka.polad.validation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class IdNumberValidator implements
        ConstraintValidator<IdNumberConstraint, String> {
    @Override
    public void initialize(IdNumberConstraint idNumber) {

    }

    @Override
    public boolean isValid(String idNumberField, ConstraintValidatorContext constraintValidatorContext) {
        String idNumber = idNumberField.toUpperCase().replace("-", "");
        log.info("ID LENGTH: " + idNumber.length());
        return idNumber.matches("[0-9]{9}[A-Z][0-9]{2}")
                || idNumber.matches("[0-9]{8}[A-Z][0-9]{2}");
    }
}
