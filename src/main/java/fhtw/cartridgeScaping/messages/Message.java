package fhtw.cartridgeScaping.messages;

import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.console.Command;

import java.io.*;

public abstract class Message implements Serializable {
    String input;

    public Message(String input) {
        this.input = input;
    }

    protected void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    protected void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String toString() {
        return "Message{" +
                "input='" + input + '\'' +
                '}';
    }
}
