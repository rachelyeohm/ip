package exception;

/**
 * Thrown to indicate a failure occuring during parsing.
 */
public class NyabotParseException extends NyabotException {

    public NyabotParseException(String str) {
        super(str);
    }
}
