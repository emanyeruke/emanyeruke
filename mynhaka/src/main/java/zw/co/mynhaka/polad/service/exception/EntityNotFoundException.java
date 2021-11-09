package zw.co.mynhaka.polad.service.exception;


public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3133985741221255036L;

    public EntityNotFoundException(final String s) {
        super(s);
    }
}
