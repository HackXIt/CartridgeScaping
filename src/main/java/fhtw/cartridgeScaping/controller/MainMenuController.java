package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.View;
import fhtw.cartridgeScaping.ViewManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.IOException;

/* NOTE Multiple Views controlled by MainMenuController
I am aware that it isn't convention to have one controller for multiple views.
But I thought, it makes much more sense to have one controller for the whole main menu,
since the sub-menu's don't really do much.

If the amount of code per view ever changes drastically, said view will get back it's own controller.
 */

/**
 * INFO Header of MainMenuController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class MainMenuController {

//    NOTE Controls for playConfiguration.fxml ----
    public void onPlayOpenConfiguration(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.CONFIGURATION);
    }

//    NOTE Controls for applicationSettings.fxml ----
    public void openApplicationSettings(ActionEvent actionEvent) throws IOException{
        ViewManager.switchTo(View.SETTINGS);
    }

//    NOTE Controls for gameCredits.fxml ----
    public void showCredits(ActionEvent actionEvent) throws IOException{
        ViewManager.switchTo(View.CREDITS);
    }
    public void onCreditsBack(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.MAIN);
    }

//    NOTE Controls for mainMenu.fxml
    public void onExit(ActionEvent actionEvent) {
        // TODO onExit() open Dialog for user-friendliness
        Platform.exit();
    }
}
