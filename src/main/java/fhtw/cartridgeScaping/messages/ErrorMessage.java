package fhtw.cartridgeScaping.messages;

public class ErrorMessage extends Message{
    private final String error;

    public ErrorMessage(String input, String error) {
        super(input, MessageType.CHAT);
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s couldn't be executed.\nReason: %s", input, error);
    }
}
