package fhtw.cartridgeScaping;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.networking.NetworkManager;
import javafx.application.Application;
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
    private NetworkManager networkManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewManager.getInstance().toggleDeveloperMode(true);
        ViewManager.getInstance().setPrimaryStage(primaryStage);
        primaryStage.setScene(ViewManager.getInstance().init());
        Player.getInstance().getCommandManager().init();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
