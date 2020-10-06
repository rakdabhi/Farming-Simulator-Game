package exceptions;

public class InsufficientInventorySpaceException extends RuntimeException {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public InsufficientInventorySpaceException() {
        super("You currently don't have enough capacity in your inventory!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * @param message the message provided to the user when the exception is thrown
     */
    public InsufficientInventorySpaceException(String message) {
        super(message);
    }
}
