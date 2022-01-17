package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.model.SettingsModel;
import fhtw.cartridgeScaping.util.IOResult;
import fhtw.cartridgeScaping.util.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/**
 * INFO Header of ViewManager.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping
 * @project CartridgeScaping
 */
public class ViewManager {
    private static final HashMap<View, FXMLLoader> loaders;
    private static final HashMap<View, Parent> cache;
    private static final SettingsModel applicationSettings;
    private static Scene scene;
    private static Window mainWindow;
    private static boolean developerMode;
    static {
        cache = new HashMap<>();
        loaders = new HashMap<>();
        loaders.put(View.MAIN, new FXMLLoader(ViewManager.class.getResource(View.MAIN.getFileName())));
        loaders.get(View.MAIN).setControllerFactory(c -> new MainMenuController());
//        TODO Set default value of developerMode to false upon release!
        developerMode = true;
        applicationSettings = new SettingsModel(
                SettingsModel.default_width,
                SettingsModel.default_height,
                SettingsModel.default_fullscreen,
                SettingsModel.default_blinkingCursor);
    }

    public static void setup() {

    }

    public static void setScene(Scene scene) {
        ViewManager.scene = scene;
    }

    public static Scene getScene() {
        return scene;
    }

    public static SettingsModel getApplicationSettings() {
        return applicationSettings;
    }

    public static void setMainWindow(Stage stage) {
        ViewManager.mainWindow = stage;
    }

    public static Window getMainWindow() {
        return ViewManager.mainWindow;
    }

    public static FXMLLoader getLoader(View view) {
        return loaders.get(view);
    }

    public static void enableDeveloperMode(boolean devMode) {
        ViewManager.developerMode = devMode;
    }

    public static boolean isDeveloperMode() {
        return developerMode;
    }

    public static void handleInputException(Exception e, Consumer<String> statusAction) {
        if(developerMode) {
            e.printStackTrace();
        } else {
            System.out.println("test");
            switch (e.getClass().getName()) {
                case "UnknownHostException" -> statusAction.accept("IP address is invalid.");
                case "NumberFormatException" -> statusAction.accept("Port is invalid.");
                default -> System.out.printf("Obj: %s\nName: %s\nClass: %s",
                        e, e.getClass().getName(), e.getClass());
            }
        }
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

    public static IOResult<FXMLLoader> openDialog(View view, String dialogTitle) {
        IOResult<FXMLLoader> io = new IOResult<>();
        if(scene == null) {
            System.err.println("No scene was set");
            return io.failure(view.getFileName(),
                    new IllegalStateException("Scene is null - Dialog cannot be loaded!"));
        }
        try {
            FXMLLoader dialogLoader = new FXMLLoader(DialogController.class.getResource(view.getFileName()));
            DialogPane dialogPane = dialogLoader.load();
            loadAndShowDialog(dialogTitle, dialogLoader, dialogPane);
            return io.success(view.getFileName(), dialogLoader);
        } catch (IOException e) {
            // TODO Move printStackTrace upstream to where the IOResult gets handled
            if(developerMode) {
                e.printStackTrace();
            }
            return io.failure(view.getFileName(), e);
        }
    }

    public static IOResult<FXMLLoader> switchTo(View view) {
        IOResult<FXMLLoader> io = new IOResult<>();
        if(scene == null) {
            System.err.println("No scene was set");
            return io.failure(view.getFileName(),
                    new IllegalStateException("Scene is null - View cannot be loaded!"));
        }
        try {
            Parent rootPane;
            if(cache.containsKey(view)) {
                System.out.println("Loading from cache...");
                rootPane = cache.get(view);
            } else {
                System.out.println("Loading from filesystem...");
                if(!loaders.containsKey(view)) {
                    loaders.put(view, new FXMLLoader(ViewManager.class.getResource(view.getFileName())));
                    rootPane = loaders.get(view).load();
                } else {
                    rootPane = loaders.get(view).getRoot();
                }
                if(view.hasCache()) {
                    // Since it hasn't been loaded before, we're going to cache it
                    cache.put(view, rootPane);
                }
            }
            scene.setRoot(rootPane);
            return io.success(view.getFileName(), loaders.get(view));
        } catch (IOException e) {
            return io.failure(view.getFileName(), e);
        }
    }
}
