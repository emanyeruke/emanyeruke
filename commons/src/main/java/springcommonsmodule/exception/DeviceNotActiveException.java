package springcommonsmodule.exception;

public class DeviceNotActiveException extends RuntimeException {

    public DeviceNotActiveException(final String s) {
        super(s);
    }
}