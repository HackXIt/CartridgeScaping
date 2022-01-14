package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.View;
import fhtw.cartridgeScaping.ViewManager;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * INFO Header of SettingsController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class SettingsController {
    public void onApply(ActionEvent actionEvent) throws IOException {
        // TODO Implement onApply() in SettingsController
        ViewManager.switchTo(View.MAIN);
    }

    public void onGameplaySettings(ActionEvent actionEvent) {
        // TODO Implement onGameplaySettings() in SettingsController
    }

    public void onVideoSettings(ActionEvent actionEvent) {
        // TODO Implement onVideoSettings() in SettingsController
    }
}
