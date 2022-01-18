package fhtw.cartridgeScaping.messages;

import fhtw.cartridgeScaping.gameplay.Player;

public class ErrorMessage extends Message{
    private final String error;

    public ErrorMessage(String input, String error) {
        super(input);
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s couldn't be executed.\nReason: %s", input, error);
    }
}
