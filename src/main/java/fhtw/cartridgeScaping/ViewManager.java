package fhtw.cartridgeScaping;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ViewManager {

    private static Scene scene;
    private static boolean loadError = false;
    private static int loadErrorCounter = 0;
    private static final Map<View, Parent> cache = new HashMap<>();

    public static void setScene(Scene scene) {
        ViewManager.scene = scene;
    }

    public static void switchTo(View view) throws IOException{
        if(scene == null) {
            System.err.println("No scene was set");
        }
        try {
            Parent root;
            if(cache.containsKey(view)) {
                System.out.println("Loading from cache...");
                root = cache.get(view);
            } else {
                System.out.println("Loading from filesystem...");
                root = FXMLLoader.load(
                        Objects.requireNonNull(ViewManager.class.getResource(view.getFileName()))
                );

                // Since it hasn't been loaded before, we're going to cache it
                cache.put(view, root);
            }
            scene.setRoot(root);
        } catch (IOException e) {
            System.err.println("Failed to load requested view. Falling back to main menu.");
            loadError = true;
        }
        if(loadError) {
            Parent root = FXMLLoader.load(ViewManager.class.getResource(View.MAIN.getFileName()));
            scene.setRoot(root);
            loadErrorCounter++;
            if(loadErrorCounter > 3) {
                System.err.println("Loading view failed repeatedly, terminating application...");
                Platform.exit();
            }
            loadError = false;
        }
    }
}
