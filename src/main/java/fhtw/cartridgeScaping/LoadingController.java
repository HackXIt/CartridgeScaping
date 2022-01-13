package fhtw.cartridgeScaping;

import javafx.event.ActionEvent;

import java.io.IOException;

public class LoadingController {
    public void onReady(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.GAMEPLAY);
    }

    public void onGameAbort(ActionEvent actionEvent) throws IOException{
        // TODO Improve GameAbort-Handling
        ViewManager.switchTo(View.MAIN);
    }
}
