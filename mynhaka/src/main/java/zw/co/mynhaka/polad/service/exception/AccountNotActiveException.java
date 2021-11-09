package zw.co.mynhaka.polad.service.exception;

public class AccountNotActiveException extends RuntimeException {

    public AccountNotActiveException(final String s) {
        super(s);
    }
}