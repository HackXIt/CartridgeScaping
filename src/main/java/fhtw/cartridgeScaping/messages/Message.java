package fhtw.cartridgeScaping.messages;

import java.io.*;

public abstract class Message implements Serializable {
    MessageType type;
    String input;

    public Message(String input, MessageType type) {
        this.input = input;
        this.type = type;
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
