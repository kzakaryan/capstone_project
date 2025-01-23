package exceptions;

public class NoSpaceAvailableException extends RuntimeException {

    public NoSpaceAvailableException(String message) {
        super(message);
    }

    public NoSpaceAvailableException() {
        super("No Space Available");
    }
}