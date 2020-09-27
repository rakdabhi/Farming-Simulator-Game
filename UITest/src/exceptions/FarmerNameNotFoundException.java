package exceptions;

public class FarmerNameNotFoundException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public FarmerNameNotFoundException() {
        super("It seems like the you haven't entered a name. "
                + "Please enter a name!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * @param message the message provided to the user when the exception is thrown
     */
    public FarmerNameNotFoundException(String message) {
        super(message);
    }
}
