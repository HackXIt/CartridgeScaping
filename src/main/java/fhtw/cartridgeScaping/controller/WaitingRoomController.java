package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.util.View;
import fhtw.cartridgeScaping.ViewManager;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * INFO Header of WaitingRoomController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class WaitingRoomController {
    public void onReady(ActionEvent actionEvent) throws IOException {
        // TODO onReady() Increase Progress-Bar by Player count
        ViewManager.switchTo(View.GAMEPLAY);
    }

    public void onGameAbort(ActionEvent actionEvent) throws IOException{
        // TODO Improve GameAbort-Handling
        ViewManager.switchTo(View.MAIN);
    }
}
