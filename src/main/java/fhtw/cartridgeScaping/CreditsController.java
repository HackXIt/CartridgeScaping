package fhtw.cartridgeScaping;

import javafx.event.ActionEvent;

import java.io.IOException;

public class CreditsController {
    public void onBack(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.MAIN);
    }
}
