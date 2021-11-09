package zw.co.mynhaka.policyservice.service.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, String field, Object value) {
        super(String.format("%s with %s: %s not found", entity, field, value.toString()));
    }

    public EntityNotFoundException(String entity, String field, Object value, Throwable cause) {
        super(String.format("%s with %s: %s not found", entity, field, value.toString()), cause);
    }
}
