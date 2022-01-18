package fhtw.cartridgeScaping.model;

import fhtw.cartridgeScaping.messages.MessageHandler;

/**
 * INFO Header of GameplayModel.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/model
 * @project CartridgeScaping
 */
public class GameplayModel extends Model {
    private final MessageHandler messageHandler = new MessageHandler();

    public GameplayModel() {
        super();
    }

    public MessageHandler messageHandler() {
        return messageHandler;
    }
}
