package fhtw.cartridgescaping;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameClient extends Application {
    double default_width = 800;
    double default_height = 600;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GameClient.class.getResource("MainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), default_width, default_height);
            primaryStage.setTitle("Cartridge Scaping");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}