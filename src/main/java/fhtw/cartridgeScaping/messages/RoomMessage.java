package fhtw.cartridgeScaping.messages;

import java.io.Serializable;

public class RoomMessage extends Message implements Serializable {
    public RoomMessage(String input) {
        super(input, MessageType.CHAT);
    }

    // TODO Implement special logic needed in PlayerMessage (if any)
}
