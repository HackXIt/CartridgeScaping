package fhtw.cartridgeScaping;

import fhtw.cartridgeScaping.util.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * NOTE Global TODOs for the Application
 * TODO Add Logger for handling all logMessages and print them to file by default
 * TODO Generate Cartridge from File
 * TODO Implement networking package (Host & Client functionality)
 * TODO Implement console package (Commands & chat functionality)
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

    @Override
    public void start(Stage stage) throws Exception {
//        root = new Scene(new Pane());
        ViewManager.setup();
        ViewManager.enableDeveloperMode(true);
//        ViewManager.switchTo(View.MAIN);
        Scene root = new Scene(ViewManager.getLoader().load());
        ViewManager.setScene(root);
        ViewManager.setMainWindow(stage);
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
