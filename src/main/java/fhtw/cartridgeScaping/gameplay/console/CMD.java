package fhtw.cartridgeScaping.gameplay.console;

public abstract class CMD {
    protected CommandText commandText;
    public CMD(CommandText commandText) {
        this.commandText = commandText;
    }

    public CommandText getCommandText() {
        return commandText;
    }

    public abstract void execute();
}
