package exception;

/**
 * Thrown to indicate that there is a missing argument in a user input in the chatbot.
 */
public class NyabotMissingArgumentException extends NyabotException {
    public NyabotMissingArgumentException(String missingArgument) {
        super(missingArgument);
    }
}
