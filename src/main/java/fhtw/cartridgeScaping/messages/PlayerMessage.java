package fhtw.cartridgeScaping.messages;

import java.io.Serializable;

public class PlayerMessage extends Message implements Serializable {
    public PlayerMessage(String input) {
        super(input, MessageType.CHAT);
    }
    // TODO Implement special logic needed in PlayerMessage (if any)
}
