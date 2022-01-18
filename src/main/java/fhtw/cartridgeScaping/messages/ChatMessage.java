package fhtw.cartridgeScaping.messages;

import fhtw.cartridgeScaping.gameplay.Player;

public class ChatMessage extends Message{
    String playerName;
    public ChatMessage(String playerName, String input) {
        super(input);
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", playerName, input);
    }
}
