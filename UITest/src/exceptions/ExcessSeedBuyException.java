package exceptions;

public class ExcessSeedBuyException extends Exception {
    /**
     * This constructor creates a new Exception with a predetermined message.
     */
    public  ExcessSeedBuyException() {
        super("Cannot buy more seeds than are available in the store!");
    }

}
