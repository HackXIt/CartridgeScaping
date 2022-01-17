package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.Model;
import fhtw.cartridgeScaping.model.SettingsModel;
import fhtw.cartridgeScaping.util.View;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.util.ResourceBundle;

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
public class AppSettingsController extends Controller implements Initializable {
    private final SettingsModel model = ViewManager.getApplicationSettings();
    private final SimpleBooleanProperty blinkingCursor = new SimpleBooleanProperty();

    @FXML
    private CheckBox boxDevMode;
    @FXML
    private CheckBox boxBlinkingCursor;

    public AppSettingsController() {
        super();
    }

    @Override
    public SettingsModel getModel() {
        return null;
    }

    public boolean isBlinkingCursor() {
        return blinkingCursor.get();
    }

    public SimpleBooleanProperty blinkingCursorProperty() {
        return blinkingCursor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Bindings.bindBidirectional(boxBlinkingCursor.selectedProperty(), blinkingCursorProperty());
    }

    //    NOTE Controls for appSettings.fxml ----
    @FXML
    public void onApply()  {
        // TODO Implement onApply() in AppSettingsController
        this.switchView(
                "Failed to load & switch view to Main.",
                "Successfully loaded & switched view to Main.",
                View.MAIN);
    }

    public void onDevModeToggle() {
        ViewManager.enableDeveloperMode(boxDevMode.selectedProperty().get());
        if(ViewManager.isDeveloperMode()) {
            System.out.println("Enabled DeveloperMode.");
        } else {
            System.out.println("Disabled DeveloperMode.");
        }
    }

    public void onCursorModeToggle() {
        model.setBlinkingCursor(blinkingCursorProperty().get());
        if(ViewManager.isDeveloperMode()) {
            System.out.printf("%s blinking cursor.", blinkingCursorProperty().get() ? "Enabled" : "Disabled");
        }
    }
}
