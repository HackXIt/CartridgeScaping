package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.SettingsModel;
import fhtw.cartridgeScaping.util.IOResult;
import fhtw.cartridgeScaping.util.View;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
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
    private static ViewManager singleton_instance;
    private final HashMap<View, FXMLLoader> loaders;
    private final HashMap<View, Parent> cache;
    private final SettingsModel applicationSettings;
    private Scene scene;
    private Window primaryStage;
    private boolean developerMode;
    private TextArea currentOutputArea;
    private TextField currentInputField;
    private Text currentStatusText;

    private ViewManager() {
        cache = new HashMap<>();
        loaders = new HashMap<>();
        developerMode = false;
        applicationSettings = new SettingsModel();
    }

    public static ViewManager getInstance() {
        if(singleton_instance == null) {
            singleton_instance = new ViewManager();
        }
        return singleton_instance;
    }

    public Scene init() {
        try {
            FXMLLoader loader = new FXMLLoader(ViewManager.class.getResource(View.MAIN.getFileName()));
            loader.setControllerFactory(c -> new MainMenuController());
            scene = new Scene(loader.load());
            return scene;
        } catch (Exception e) {
            System.err.println("Loading application view during initialization failed - closing application.");
            if(developerMode) {
                e.printStackTrace();
            }
            Platform.exit();
            return null;
        }
    }

    public Scene getScene() {
        return scene;
    }

    public SettingsModel getApplicationSettings() {
        return applicationSettings;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public Window getPrimaryStage() {
        return this.primaryStage;
    }

    public FXMLLoader getLoader(View view) {
        return loaders.get(view);
    }

    public void toggleDeveloperMode(boolean devMode) {
        if (devMode) {
            System.out.println("Enabled developer mode.");
        } else {
            System.out.println("Disabled developer mode.");
        }
        this.developerMode = devMode;
    }

    public boolean developerMode() {
        return developerMode;
    }

    public TextArea getCurrentOutputArea() {
        return currentOutputArea;
    }

    public void setCurrentOutputArea(TextArea currentOutputArea) {
        this.currentOutputArea = currentOutputArea;
    }

    public TextField getCurrentInputField() {
        return currentInputField;
    }

    public void setCurrentInputField(TextField currentInputField) {
        this.currentInputField = currentInputField;
    }

    public Text getCurrentStatusText() {
        return currentStatusText;
    }

    public void setCurrentStatusText(Text currentStatusText) {
        this.currentStatusText = currentStatusText;
    }

    public void devLog(String log) {
        if(developerMode) {
            System.out.println(log);
        }
    }

    public void errorLog(String error, Exception e) {
        System.err.println(error);
        if(developerMode) {
            e.printStackTrace();
        }
    }

    public void handleInputException(Exception e, Consumer<String> statusAction) {
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

    private void loadAndShowDialog(String dialogTitle,
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

    public IOResult<FXMLLoader> openDialog(View view, String dialogTitle) {
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

    public IOResult<FXMLLoader> switchTo(View view) {
        IOResult<FXMLLoader> io = new IOResult<>();
        if(scene == null) {
            System.err.println("No scene was set");
            return io.failure(view.getFileName(),
                    new IllegalStateException("Scene is null - View cannot be loaded!"));
        }
        try {
            Parent rootPane;
            if(cache.containsKey(view)) {
                if(developerMode) {
                    System.out.printf("\nLoading %s from cache...\n", view.getFileName());
                }
                rootPane = cache.get(view);
            } else {
                if(developerMode) {
                    System.out.printf("\nLoading %s from filesystem...\n", view.getFileName());
                }
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
