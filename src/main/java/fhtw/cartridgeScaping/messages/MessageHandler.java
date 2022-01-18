package fhtw.cartridgeScaping.messages;

import fhtw.cartridgeScaping.controller.ViewManager;
import javafx.scene.control.TextArea;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageHandler {
    private ArrayList<Message> messages = new ArrayList<>();

    public void addMessage(Serializable msg) {
        addMessage((Message) msg);
        ViewManager.getInstance().getCurrentOutputArea().appendText(msg.toString() + "\n");
    }

    public void addMessage(Message msg) {
        messages.add(msg);
    }

    public void addMessage(Message msg, TextArea output) {
        addMessage(msg);
        output.appendText(msg.toString() + "\n");
    }

    public void removeMessage(Message msg) {
        messages.remove(msg);
    }

    public void clearMessages() {
        messages.clear();
    }

    public void clearMessages(TextArea output) {
        messages.clear();
        output.clear();
    }

    public Message getLastMessage() {
        return messages.get(messages.size() - 1);
    }
}
