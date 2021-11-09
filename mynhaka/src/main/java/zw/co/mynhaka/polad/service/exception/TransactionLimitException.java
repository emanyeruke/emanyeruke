package zw.co.mynhaka.polad.service.exception;

public class TransactionLimitException extends RuntimeException {

    public TransactionLimitException(final String s) {
        super(s);
    }
}
