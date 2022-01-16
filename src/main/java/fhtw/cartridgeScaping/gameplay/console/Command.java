package fhtw.cartridgeScaping.gameplay.console;

/**
 * Class for simple word commands
 */
public class Command {
    protected final Runnable action;
    protected CommandText commandText;

    public Command(Runnable action, CommandText commandText) {
        this.action = action;
        this.commandText = commandText;
    }

    public Runnable getAction() {
        return action;
    }

    public CommandText getCommandText() {
        return commandText;
    }

    public void execute() {
        if(action != null) {
            action.run();
        }
    }
}
