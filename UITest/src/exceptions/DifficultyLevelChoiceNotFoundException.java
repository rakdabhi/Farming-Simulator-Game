package exceptions;

public class DifficultyLevelChoiceNotFoundException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public DifficultyLevelChoiceNotFoundException() {
        super("Please select your difficulty level!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * @param message the message provided to the user when the exception is thrown
     */
    public DifficultyLevelChoiceNotFoundException(String message) {
        super(message);
    }
}
