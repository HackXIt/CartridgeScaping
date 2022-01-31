package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.gameplay.GameObject;

/**
 * Class for simple word commands
 */
public class Command extends CMD {
    protected final Runnable action;
    protected CommandText commandText;

    public Command(Runnable action, CommandText commandText) {
        super(commandText);
        this.action = action;
    }

    @Override
    public void execute() {
        if(action != null) {
            action.run();
        }
    }
}
