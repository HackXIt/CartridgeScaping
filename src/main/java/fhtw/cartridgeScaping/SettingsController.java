package fhtw.cartridgeScaping;

import javafx.event.ActionEvent;

import java.io.IOException;

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
