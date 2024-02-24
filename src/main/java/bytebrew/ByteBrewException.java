package bytebrew;

import utility.Constants;

public class ByteBrewException extends Exception {
    public ByteBrewException(String message) {
        super(Constants.HORIZONTAL_LINE + "\n" + message + "\n" + Constants.HORIZONTAL_LINE);
    }
}