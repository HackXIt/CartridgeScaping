package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.util.View;
import fhtw.cartridgeScaping.ViewManager;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * INFO Header of GameplayController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class GameplayController {
    public void onQuitGameplay(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.MAIN);
    }
}
