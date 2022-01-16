package fhtw.cartridgeScaping.gameplay.console;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Class for commands with parameters, but the parameter is parsed during runtime
 * The parameter is given by the player and validation must happen before execution
 * @param <T> The type of the parameter which will be parsed during runtime
 */
public class DynamicCommand<T> extends Command {
    private Consumer<T> consumer;
    private T dynamicConsumable;

    public DynamicCommand(Runnable action, CommandText commandText) {
        super(action, commandText);
    }

    public DynamicCommand(Consumer<T> consumer, CommandText commandText) {
        super(null, commandText);
        this.consumer = consumer;
    }

    @Override
    public Runnable getAction() throws UnsupportedOperationException{
        if(action == null) {
            // NOTE This is an actual unsupported operation, you can't getAction when Consumable is null
            throw new UnsupportedOperationException();
        } else {
            return action;
        }
    }

    public void dynamicExecute(T value) {
        if(value != null) {
            dynamicConsumable = value;
            this.execute();
            dynamicConsumable = null;
        }
    }

    @Override
    public void execute() {
        if(commandText.hasArgument()) {
            if(dynamicConsumable != null) {
                consumer.accept(dynamicConsumable);
            } else {
                // TODO PlayerMessage That the given command is not executable (consumable is null)
            }
        } else {
            action.run();
        }
    }
}
