package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.controller.ViewManager;

import java.util.function.Consumer;

/**
 * Class for commands with parameters, but the parameter is parsed during runtime
 * The parameter is given by the player and validation must happen before execution
 * @param <T> The type of the parameter which will be parsed during runtime
 */
public class DynamicCommand<T> extends CMD {
    private Consumer<T> consumer;
    private T dynamicConsumable;

    public DynamicCommand(Consumer<T> consumer, CommandText commandText) {
        super(commandText);
        this.consumer = consumer;
    }

    public void setDynamicConsumable(T dynamicConsumable) {
        if(dynamicConsumable != null) {
            this.dynamicConsumable = dynamicConsumable;
        }
    }

    @Override
    public void execute() {
        if (dynamicConsumable != null) {
            consumer.accept(dynamicConsumable);
            dynamicConsumable = null;
        } else {
            // TODO PlayerMessage That the given command is not executable (consumable is null)
        }
    }
}
