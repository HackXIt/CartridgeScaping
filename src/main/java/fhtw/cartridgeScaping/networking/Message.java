package fhtw.cartridgeScaping.networking;

import java.io.*;
import java.util.Formatter;

public abstract class Message implements Serializable {
    Formatter stringFormat;
    String command;
    String message;

    protected void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(stringFormat);
        out.writeObject(command);
        out.writeObject(message);
    }
    protected void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        stringFormat = (Formatter) in.readObject();
        command = (String) in.readObject();
        message = (String) in.readObject();
    }
}
