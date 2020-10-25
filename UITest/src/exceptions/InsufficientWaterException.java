package exceptions;

public class InsufficientWaterException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public InsufficientWaterException() {
        super("You didn't provide enough water to this crop... it died of thirst:(");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     *
     * @param message the message provided to the user when the exception is thrown
     */
    public InsufficientWaterException(String message) {
        super(message);
    }
}