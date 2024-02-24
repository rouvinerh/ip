package bytebrew;

/**
 * Represents a custom exception class for all time-based errors for the ByteBrew bot.
 */
public class ByteBrewTimeException extends ByteBrewException {

    /**
     * Constructs a new ByteBrewTimeException with the specified error message.
     *
     * @param message The error message of the exception.
     */
    public ByteBrewTimeException(String message) {
        super(message);
    }
}
