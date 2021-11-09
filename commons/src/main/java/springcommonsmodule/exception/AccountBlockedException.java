package springcommonsmodule.exception;

public class AccountBlockedException extends RuntimeException {

    public AccountBlockedException(final String s) {
        super(s);
    }
}
