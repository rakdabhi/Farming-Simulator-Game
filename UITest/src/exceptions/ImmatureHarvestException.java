package exceptions;

public class ImmatureHarvestException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public ImmatureHarvestException() {
        super("Don't kill the baby!!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * @param message the message provided to the user when the exception is thrown
     */
    public ImmatureHarvestException(String message) {
        super(message);
    }
}
