abstract public class Command {

    protected boolean isExit = false;

    abstract public void execute();

    abstract public boolean isExit();

    abstract public String successfulMessage();

}
