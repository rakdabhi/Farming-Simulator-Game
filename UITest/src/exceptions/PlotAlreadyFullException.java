package exceptions;

public class PlotAlreadyFullException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public PlotAlreadyFullException() {
        super("This plot already has a crop!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * @param message the message provided to the user when the exception is thrown
     */
    public PlotAlreadyFullException(String message) {
        super(message);
    }
}
