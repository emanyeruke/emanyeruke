package springcommonsmodule.exception;

public class TransactionNotAllowedException extends RuntimeException {

    public TransactionNotAllowedException(final String s) {
        super(s);
    }
}
