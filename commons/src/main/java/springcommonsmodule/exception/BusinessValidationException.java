package springcommonsmodule.exception;

public class BusinessValidationException extends RuntimeException {

    public BusinessValidationException(final String s) {
        super(s);
    }
}
