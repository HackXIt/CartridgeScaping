package fhtw.cartridgeScaping.messages;

public class ChatMessage extends Message{
    String playerName;
    public ChatMessage(String playerName, String input) {
        super(input, MessageType.CHAT);
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", playerName, input);
    }
}
