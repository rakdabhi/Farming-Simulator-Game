
package exceptions;

public class EmptyPlotException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public EmptyPlotException() {
        super("This plot is empty!");
    }

    /**
     * This constructor creates a new Exception with a custom message.
     * @param message the message provided to the user when the exception is thrown
     */
    public EmptyPlotException(String message) {
        super(message);
    }
}
