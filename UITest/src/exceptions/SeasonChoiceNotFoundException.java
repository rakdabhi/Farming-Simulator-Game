package exceptions;

public class SeasonChoiceNotFoundException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public SeasonChoiceNotFoundException() {
        super("Please select a season to start farming in!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * @param message the message provided to the user when the exception is thrown
     */
    public SeasonChoiceNotFoundException(String message) {
        super(message);
    }
}
