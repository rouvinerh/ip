package bytebrew;

import utility.constants;

/**
 * Represents a custom exception class for ByteBrew bot.
 *
 */
public class ByteBrewException extends Exception {
    /**
     * Constructs a new ByteBrewException with the specified error message.
     *
     * @param message The error message of the exception.
     */
    public ByteBrewException(String message) {
        super(constants.HORIZONTAL_LINE + "\n" + message + "\n" + constants.HORIZONTAL_LINE);
    }
}