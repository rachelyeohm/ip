package exception;

public class NyabotMissingArgumentException extends NyabotException {
    public NyabotMissingArgumentException(String missingArgument) {
        super(missingArgument);
    }
}
