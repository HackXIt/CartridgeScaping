package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.util.View;
import fhtw.cartridgeScaping.ViewManager;
import fhtw.cartridgeScaping.model.EasterEggModel;
import fhtw.cartridgeScaping.model.PlayConfigurationModel;
import fhtw.cartridgeScaping.model.SettingsModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
    private SettingsModel settingsModel;
    private PlayConfigurationModel playConfigurationModel;
    private EasterEggModel easterEggModel;

    public MainMenuController() {
    }

    public MainMenuController(SettingsModel settingsModel,
                              PlayConfigurationModel playConfigurationModel,
                              EasterEggModel easterEggModel) {
        this.settingsModel = settingsModel;
        this.playConfigurationModel = playConfigurationModel;
        this.easterEggModel = easterEggModel;
    }

    //    NOTE Controls for playConfiguration.fxml ----
    @FXML
    public void openPlayConfiguration(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.CONFIGURATION);
    }
    @FXML
    public void onStartPlay(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.WAITING);
    }
    @FXML
    public void onAbortPlay(ActionEvent actionEvent) throws IOException{
        ViewManager.switchTo(View.MAIN);
    }

//    NOTE Controls for appSettings.fxml ----
    @FXML
    public void openApplicationSettings(ActionEvent actionEvent) throws IOException{
        ViewManager.switchTo(View.SETTINGS);
    }
    @FXML
    public void onApply(ActionEvent actionEvent) throws IOException{
//        TODO Implement onApply for ApplicationSettings
        ViewManager.switchTo(View.MAIN);
    }

//    NOTE Controls for gameCredits.fxml ----
    @FXML
    public void showCredits(ActionEvent actionEvent) throws IOException{
        ViewManager.switchTo(View.CREDITS);
    }
    @FXML
    public void onCreditsBack(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.MAIN);
    }

//    NOTE Controls for mainMenu.fxml
    @FXML
    public void onExit(ActionEvent actionEvent) {
        // TODO onExit() open Dialog for user-friendliness
        Platform.exit();
    }

}
