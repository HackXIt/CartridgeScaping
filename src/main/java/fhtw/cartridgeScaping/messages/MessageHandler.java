package fhtw.cartridgeScaping.messages;

import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class MessageHandler {
    private ArrayList<Message> messages = new ArrayList<>();

    public void addMessage(Message msg) {
        messages.add(msg);
    }

    public void addMessage(Message msg, TextArea output) {
        addMessage(msg);
        output.appendText(msg.toString());
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
