package springcommonsmodule.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(final String s) {
        super(s);
    }
}
