package exception;

/**
 * Thrown to indicate that an index in an iterable structure is out of bounds.
 */
public class NyabotIndexOutOfBoundsException extends NyabotException {
    public NyabotIndexOutOfBoundsException(String msg) {
        super(msg);
    }
}
