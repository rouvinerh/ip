package bytebrew;

import utility.Constants;

/**
 * Represents a custom exception class for ByteBrew bot.
 */
public class ByteBrewException extends Exception {
    /**
     * Constructs a new ByteBrewException with the specified error message.
     *
     * @param message The error message of the exception.
     */
    public ByteBrewException(String message) {
        super(Constants.HORIZONTAL_LINE + "\n" + message + "\n" + Constants.HORIZONTAL_LINE);
    }
}