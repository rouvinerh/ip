public class ByteBrewException extends Exception {
    public static String HORIZONTAL_LINE = "__________________________________________________\n";
    public ByteBrewException(String message) {
        super(HORIZONTAL_LINE + message + "\n" + HORIZONTAL_LINE);
    }
}