package exceptions;

public class WaterOverflowException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public WaterOverflowException() {
        super("You gave this crop too much water... it has drowned:(");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     *
     * @param message the message provided to the user when the exception is thrown
     */
    public WaterOverflowException(String message) {
        super(message);
    }
}