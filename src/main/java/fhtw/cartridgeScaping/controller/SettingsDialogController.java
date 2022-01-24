package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.SettingsModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsDialogController
        extends AppSettingsController
        implements DialogController, Initializable {
    private final SettingsModel model = ViewManager.getInstance().getApplicationSettings();
    /* Inherited from parent
    private boolean tempDevMode;
    private boolean tempBlinkingCursor;
    private boolean tempVerboseMode;
     */

    /* Inherited from parent
    @FXML
    private CheckBox boxDevMode;
    @FXML
    private CheckBox boxBlinkingCursor;
    @FXML
    private CheckBox boxVerboseMode;
     */

    public SettingsDialogController() {
        super();
    }

    @Override
    public void consumeDialog() {
        ViewManager.getInstance().devLog("Consuming dialog...");
        model.setBlinkingCursor(tempBlinkingCursor);
        model.setVerbose(tempVerboseMode);
        ViewManager.getInstance().toggleDeveloperMode(tempDevMode);
    }

    @Override
    public boolean verifyInput() {
        // NOTE No input to verify, only checkboxes
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boxDevMode.setSelected(ViewManager.getInstance().developerMode());
        boxBlinkingCursor.setSelected(model.isBlinkingCursor());
        boxVerboseMode.setSelected((model.isVerbose()));
        tempDevMode = ViewManager.getInstance().developerMode();
        tempBlinkingCursor = model.isBlinkingCursor();
        tempVerboseMode = model.isVerbose();
    }
}
