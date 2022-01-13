package fhtw.cartridgeScaping;

import javafx.event.ActionEvent;

import java.io.IOException;

public class PlayConfigurationController {
    public void onPlay(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.LOADING);
    }

    public void onAbort(ActionEvent actionEvent) throws IOException{
        ViewManager.switchTo(View.MAIN);
    }
}
