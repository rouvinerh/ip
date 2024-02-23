package bytebrew;

import utility.constants;

public class ByteBrewException extends Exception {
    public ByteBrewException(String message) {
        super(constants.HORIZONTAL_LINE + "\n" + message + "\n" + constants.HORIZONTAL_LINE);
    }
}