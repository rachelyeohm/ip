package exception;

/**
 * Thrown to indicate a general error has occured relating to the Nyabot chatbot.
 */
public class NyabotException extends Exception {
    public NyabotException(String str) {
        super(str);
    }
}
