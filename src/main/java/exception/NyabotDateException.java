package exception;

/**
 * Thrown to indicate that a date-related error has occurred in the Nyabot chatbot.
 * For example, the start date of an event could be after the end date of an event.
 */
public class NyabotDateException extends NyabotException {
    public NyabotDateException(String str) {
        super(str);
    }
}
