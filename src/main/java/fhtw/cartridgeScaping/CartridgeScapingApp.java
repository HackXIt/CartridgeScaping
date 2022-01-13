package fhtw.cartridgeScaping;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CartridgeScapingApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Pane());

        ViewManager.setScene(scene);
        ViewManager.switchTo(View.MAIN);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
