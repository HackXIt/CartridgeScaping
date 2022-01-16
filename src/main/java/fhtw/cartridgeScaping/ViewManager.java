package fhtw.cartridgeScaping;

import fhtw.cartridgeScaping.controller.DialogController;
import fhtw.cartridgeScaping.controller.HostDialogController;
import fhtw.cartridgeScaping.controller.MainMenuController;
import fhtw.cartridgeScaping.model.EasterEggModel;
import fhtw.cartridgeScaping.model.PlayConfigurationModel;
import fhtw.cartridgeScaping.model.SettingsModel;
import fhtw.cartridgeScaping.util.IOResult;
import fhtw.cartridgeScaping.util.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.FocusModel;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * INFO Header of ViewManager.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping
 * @project CartridgeScaping
 */
public class ViewManager {

    private static FXMLLoader loader;
    private static Scene scene;
    private static Window mainWindow;
    private static boolean developerMode;
    private static final Map<View, Parent> cache = new HashMap<>();

    public static void setup() {
        loader = new FXMLLoader(ViewManager.class.getResource(View.MAIN.getFileName()));
        loader.setControllerFactory(c -> new MainMenuController(
                new SettingsModel(
                        SettingsModel.default_width,
                        SettingsModel.default_height,
                        false),
                new PlayConfigurationModel(),
                new EasterEggModel()
        ));
    }

    public static void setScene(Scene scene) {
        ViewManager.scene = scene;
    }

    public static void setMainWindow(Stage stage) {
        ViewManager.mainWindow = stage;
    }
    public static Window getMainWindow() {
        return ViewManager.mainWindow;
    }

    public static FXMLLoader getLoader() {
        return loader;
    }

    public static void enableDeveloperMode(boolean devMode) {
        ViewManager.developerMode = devMode;
    }

    public static boolean isDeveloperMode() {
        return developerMode;
    }

    private static void loadAndShowDialog(String dialogTitle,
                                          FXMLLoader dialogLoader,
                                          DialogPane dialogPane) {
        DialogController dialogController = dialogLoader.getController();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle(dialogTitle);

        Optional<ButtonType> clickedButton = dialog.showAndWait();

        if(clickedButton.isPresent()) {
            if (clickedButton.get() == ButtonType.OK) {
                dialogController.consumeDialog();
            } else {
                dialog.close();
            }
        }
    }

    public static IOResult<View> openDialog(View view, String dialogTitle) {
        if(scene == null) {
            System.err.println("No scene was set");
            return (IOResult<View>) IOResult.failure("Scene wasn't set, cannot open Dialog!", null);
        }
        try {
            FXMLLoader dialogLoader = new FXMLLoader(DialogController.class.getResource(view.getFileName()));
            DialogPane dialogPane = dialogLoader.load();
            loadAndShowDialog(dialogTitle, dialogLoader, dialogPane);
            return (IOResult<View>) IOResult.success(
                    String.format("Successfully loaded %s", dialogTitle),
                    dialogLoader);
        } catch (IOException e) {
            // TODO Move printStackTrace upstream to where the IOResult gets handled
            if(developerMode) {
                e.printStackTrace();
            }
            return (IOResult<View>) IOResult.failure(String.format("Failed to load '%s'.", dialogTitle), e);
        }
    }

    //    FIXME Unchecked cast warning in switchTo() of ViewManager because of IOResult
    public static IOResult<View> switchTo(View view) {
        if(scene == null) {
            System.err.println("No scene was set");
            return (IOResult<View>) IOResult.failure("Scene wasn't set, cannot switch view!", null);
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
            return (IOResult<View>) IOResult.success(String.format("Successfully loaded %s", view.getFileName()), view);
        } catch (IOException e) {
            // TODO Move printStackTrace upstream to where the IOResult gets handled
            if(developerMode) {
                e.printStackTrace();
            }
            return (IOResult<View>) IOResult.failure(String.format("Failed to load %s", view.getFileName()), e);
//            System.err.println("Failed to load requested view. Falling back to main menu.");
//            e.printStackTrace();
//            loadError = true;
        }
//        if(loadError) {
//            Parent root = FXMLLoader.load(ViewManager.class.getResource(View.MAIN.getFileName()));
//            scene.setRoot(root);
//            loadErrorCounter++;
//            if(loadErrorCounter > 3) {
//                System.err.println("Loading view failed repeatedly, terminating application...");
//                Platform.exit();
//            }
//            loadError = false;
//        }
    }
}
