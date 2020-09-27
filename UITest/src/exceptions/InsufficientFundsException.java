package exceptions;

public class InsufficientFundsException extends RuntimeException {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public InsufficientFundsException() {
        super("You currently don't have enough money to pay for this!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * @param message the message provided to the user when the exception is thrown
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
