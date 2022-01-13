package fhtw.cartridgeScaping;

import javafx.event.ActionEvent;

import java.io.IOException;

public class GameplayController {
    public void onQuitGameplay(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.MAIN);
    }
}
