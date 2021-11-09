package zw.co.mynhaka.polad.service.exception;

public class TransactionNotAllowedException extends RuntimeException {

    public TransactionNotAllowedException(final String s) {
        super(s);
    }
}
