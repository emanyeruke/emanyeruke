package springcommonsmodule.exception;

public class AmountInvalidException extends RuntimeException {

    public AmountInvalidException(final String s) {
        super(s);
    }
}