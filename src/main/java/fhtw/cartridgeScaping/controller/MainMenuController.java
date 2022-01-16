package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.util.View;
import fhtw.cartridgeScaping.model.EasterEggModel;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * INFO Header of MainMenuController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class MainMenuController extends Controller{
    private final EasterEggModel easterEggModel;

    public MainMenuController() {
        super();
        this.easterEggModel = new EasterEggModel();
    }

    //    NOTE Controls for mainMenu.fxml ----
    @FXML
    public void openPlayConfiguration() {
        this.switchView(
                "Failed to load & switch view to PlayConfiguration.",
                "Successfully loaded & switched view to PlayConfiguration.",
                View.CONFIGURATION);
    }
    @FXML
    public void openApplicationSettings() {
        this.switchView(
                "Failed to load & switch view to ApplicationSettings.",
                "Successfully loaded & switched view to ApplicationSettings.",
                View.SETTINGS);
    }
    @FXML
    public void showCredits()  {
        this.switchView(
                "Failed to load & switch view to Credits.",
                "Successfully loaded & switched view to Credits.",
                View.CREDITS);
    }
    @FXML
    public void onExit() {
        // TODO onExit() open Dialog for user-friendliness
        Platform.exit();
    }

}
