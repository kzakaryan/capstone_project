package exceptions;

public class CurrentLevelMismatchException extends RuntimeException {

  public CurrentLevelMismatchException(String message) {
        super(message);
  }

  public CurrentLevelMismatchException() {
    super("Current Level Mismatch");
  }

}
