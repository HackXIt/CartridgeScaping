package fhtw.cartridgeScaping.messages;

import java.io.Serializable;

public class LocalMessage extends Message implements Serializable {
    public LocalMessage(String input) {
        super(input, MessageType.CHAT);
    }
    // TODO Implement special logic needed in PlayerMessage (if any)
}
