package exception;

/**
 * Thrown to indicate that an input or output operation has failed.
 */
public class NyabotIOException extends NyabotException {
    public NyabotIOException(String str) {
        super(str);
    }
}
