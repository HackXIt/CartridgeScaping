package fhtw.cartridgeScaping;

import fhtw.cartridgeScaping.util.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * NOTE Global TODOs for the Application
 * TODO Add Logger for handling all logMessages and print them to file by default
 */

/**
 * INFO Header of CartridgeScapingApp.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping
 * @project CartridgeScaping
 */
public class CartridgeScapingApp extends Application {
    private static Scene root;

    @Override
    public void start(Stage stage) throws Exception {
//        root = new Scene(new Pane());
        ViewManager.setup();
        ViewManager.enableDeveloperMode(true);
//        ViewManager.switchTo(View.MAIN);
        root = new Scene(ViewManager.getLoader().load());
        ViewManager.setScene(root);
        stage.setScene(root);
        if(ViewManager.isDeveloperMode()) {
            try {
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
