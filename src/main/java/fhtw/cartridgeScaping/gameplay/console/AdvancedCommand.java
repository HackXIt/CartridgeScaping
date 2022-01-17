package fhtw.cartridgeScaping.gameplay.console;

import java.util.function.Consumer;

/**
 * Class for commands with parameters which need to be consumed
 * @param <T> The type of the parameter that will be consumed
 */
public class AdvancedCommand<T> extends Command{
    private Consumer<T> consumer;
    private T staticConsumable;

    public AdvancedCommand(Runnable action, CommandText commandText) {
        super(action, commandText);
    }

    public AdvancedCommand(Consumer<T> consumer, T staticConsumable, CommandText commandText) {
        super(null, commandText);
        this.consumer = consumer;
        this.staticConsumable = staticConsumable;
    }

    @Override
    public Runnable getAction() throws UnsupportedOperationException{
        if(action == null) {
            // NOTE This is an actual unsupported operation, you can't do getAction with Consumer
            throw new UnsupportedOperationException();
        } else {
            return action;
        }
    }

    @Override
    public void execute() {
        if(commandText.hasArgument()) {
            consumer.accept(staticConsumable);
        } else {
            action.run();
        }
    }
}
