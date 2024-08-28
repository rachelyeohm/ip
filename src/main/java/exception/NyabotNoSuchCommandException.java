package exception;

/**
 * Thrown to indicate that the command inputted by the user does not exist.
 */
public class NyabotNoSuchCommandException extends NyabotException {
    public NyabotNoSuchCommandException(String msg) {
        super(msg);
    }
}
