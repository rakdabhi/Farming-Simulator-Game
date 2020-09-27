package exceptions;

public class SeedChoiceNotFoundException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public SeedChoiceNotFoundException() {
        super("Please select one seed to start with!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * @param message the message provided to the user when the exception is thrown
     */
    public SeedChoiceNotFoundException(String message) {
        super(message);
    }
}
