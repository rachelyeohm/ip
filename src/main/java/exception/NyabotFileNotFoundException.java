package exception;

/**
 * Thrown to indicate that there was a failed attempt to open a file, because the file
 * by that specific filename does not exist.
 */
public class NyabotFileNotFoundException extends NyabotException {
    public NyabotFileNotFoundException(String str) {
        super(str);
    }
}
