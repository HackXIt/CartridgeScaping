package fhtw.cartridgeScaping;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MainController {
    public void onPlayOpenConfiguration(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.CONFIGURATION);
    }

    public void openSettings(ActionEvent actionEvent) throws IOException{
        ViewManager.switchTo(View.SETTINGS);
    }

    public void showCredits(ActionEvent actionEvent) throws IOException{
        ViewManager.switchTo(View.CREDITS);
    }

    public void onExit(ActionEvent actionEvent) {
        // TODO onExit() open Dialog for user-friendliness
        Platform.exit();
    }
}
