package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.View;
import fhtw.cartridgeScaping.ViewManager;
import javafx.event.ActionEvent;

import java.io.IOException;

/* NOTE PlayConfigurationController currently deprecated
The PlayConfigurationController is unnecessary and the control will be handled by MainMenuController

If the playConfiguration.fxml View ever requires additional control (= meaning more code),
this will become the controller for that View again, simply for readability.

But as of now, this is not necessary.
 */

/**
 * INFO Header of PlayConfigurationController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping
 * @project CartridgeScaping
 */
public class PlayConfigurationController {
    public void onStartPlay(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.WAITING);
    }

    public void onAbortPlay(ActionEvent actionEvent) throws IOException{
        ViewManager.switchTo(View.MAIN);
    }
}
