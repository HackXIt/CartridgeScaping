package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.console.Command;
import fhtw.cartridgeScaping.model.GameplayModel;
import fhtw.cartridgeScaping.util.View;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * INFO Header of GameplayController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class GameplayController extends Controller implements Initializable {
    private final GameplayModel model;

    @FXML
    private TextArea outputArea;
    @FXML
    private TextField inputField;
    @FXML
    private Button settingsButtons;
    @FXML
    private Button quitGameButton;
    @FXML
    private Text statusText;
    @FXML
    private ListView playerListView;

    public GameplayController() {
        super();
        this.model = new GameplayModel();
    }

    @Override
    public GameplayModel getModel() {
        return model;
    }


    //    NOTE Controls for gameplayView.fxml ----
    public void onQuitGameplay() {
        // TODO RoomMessage to notify that player has left the game.
        this.switchView(
                "Failed to load & switch view to Main.",
                "Successfully loaded & switched view to Main.",
                View.Main);
    }

    public void onOpenSettings() {
        this.openDialog(
                "Failed to open settings dialog.",
                "Successfully opened settings dialog.",
                View.SettingsDialog,
                "Settings...");
    }

    public void onInput() {
        String input = inputField.getText();
        Command cmd = Player.getInstance().getCommandManager().parseInput(input);
        if(cmd != null) {
            cmd.execute();
        }
        inputField.clear();
        // TODO Send messages on gameplay input
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewManager.getInstance().setCurrentOutputArea(outputArea);
        ViewManager.getInstance().setCurrentStatusText(statusText);
        ViewManager.getInstance().setCurrentInputField(inputField);
        outputArea.setFocusTraversable(false);
        inputField.requestFocus();
    }
}
