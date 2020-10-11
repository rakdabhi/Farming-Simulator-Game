package exceptions;

public class InsufficientPlotSpaceException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public InsufficientPlotSpaceException() {
        super("This plot is already full and cannot hold more seeds!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * 
     * @param message the message provided to the user when the exception is thrown
     */
    public InsufficientPlotSpaceException(String message) {
        super(message);
    }
}