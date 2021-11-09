package zw.co.mynhaka.policyservice.service.exceptions;

public class BusinessRulesValidationException extends RuntimeException {
    public BusinessRulesValidationException(String message) {
        super(message);
    }

    public BusinessRulesValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
