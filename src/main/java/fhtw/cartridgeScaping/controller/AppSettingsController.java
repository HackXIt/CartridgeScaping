package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.util.View;
import fhtw.cartridgeScaping.ViewManager;
import javafx.event.ActionEvent;

import java.io.IOException;

/* NOTE SettingsController currently deprecated
The SettingsController is unnecessary and the control will be handled by MainMenuController

If the appSettings.fxml View ever requires additional control (= meaning more code),
this will become the controller for that View again, simply for readability.

But as of now, this is not necessary.
 */

/**
 * INFO Header of SettingsController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class AppSettingsController {
    public void onApply(ActionEvent actionEvent) throws IOException {
        // TODO Implement onApply() in SettingsController
        ViewManager.switchTo(View.MAIN);
    }

//    public void onGameplaySettings(ActionEvent actionEvent) {
//        // TODO Implement onGameplaySettings() in SettingsController
//    }
//
//    public void onVideoSettings(ActionEvent actionEvent) {
//        // TODO Implement onVideoSettings() in SettingsController
//    }
}
