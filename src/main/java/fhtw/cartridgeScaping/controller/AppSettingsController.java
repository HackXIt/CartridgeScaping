package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.SettingsModel;
import fhtw.cartridgeScaping.util.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;

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
public class AppSettingsController extends Controller<SettingsModel> implements Initializable {
    private final SettingsModel model = ViewManager.getInstance().getApplicationSettings();

    @FXML
    private CheckBox boxDevMode;
    @FXML
    private CheckBox boxBlinkingCursor;
    @FXML
    private Text statusText;

    public AppSettingsController() {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewManager.getInstance().setCurrentStatusText(statusText);
        statusText.setVisible(false);
        boxDevMode.setSelected(ViewManager.getInstance().developerMode());
    }

    //    NOTE Controls for appSettings.fxml ----
    @FXML
    public void onApply()  {
        // TODO Implement onApply() in AppSettingsController
        statusText.setVisible(true);
        statusText.setText("Success!");
    }

    public void onDevModeToggle() {
        ViewManager.getInstance().toggleDeveloperMode(boxDevMode.isSelected());
    }

    public void onCursorModeToggle() {
        model.setBlinkingCursor(boxBlinkingCursor.isSelected());
        ViewManager.getInstance().devLog(
                String.format("%s blinking cursor.",
                boxBlinkingCursor.isSelected() ? "Enabled" : "Disabled"));
    }

    public void onBack() {
        this.switchView(
                "Failed to load & switch view to Main.",
                "Successfully loaded & switched view to Main.",
                View.MAIN);
    }
}
