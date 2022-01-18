package fhtw.cartridgeScaping.messages;

import fhtw.cartridgeScaping.gameplay.Player;

import java.io.Serializable;

public class LocalMessage extends Message implements Serializable {
    public LocalMessage(String input) {
        super(input);
    }
    // TODO Implement special logic needed in PlayerMessage (if any)
}
