package fhtw.cartridgeScaping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import fhtw.cartridgeScaping.cartridge.Cartridge;
import fhtw.cartridgeScaping.gameplay.GameManager;
import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.json.Json;
import fhtw.cartridgeScaping.messages.ChatMessage;
import fhtw.cartridgeScaping.messages.ErrorMessage;
import fhtw.cartridgeScaping.messages.LocalMessage;
import fhtw.cartridgeScaping.messages.Message;
import fhtw.cartridgeScaping.model.WaitingRoomModel;
import fhtw.cartridgeScaping.networking.NetworkManager;
import fhtw.cartridgeScaping.util.View;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * INFO Header of WaitingRoomController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class WaitingRoomController
        extends Controller<WaitingRoomModel>
        implements DialogController, Initializable {
    private final WaitingRoomModel model;
    private final SimpleDoubleProperty progressBar = new SimpleDoubleProperty();

    @FXML
    private TextArea chatArea;
    @FXML
    private TextField inputField;
    @FXML
    private ToolBar toolbar;
    @FXML
    private Button hostStartButton;
    @FXML
    private TextArea statusMessages;
    @FXML
    private Text gameTitle;
    @FXML
    private ImageView cartridgeBoxImage;
    @FXML
    private Text statusText;
    @FXML
    private ListView playerList;
    @FXML
    private ProgressBar progBar;
    @FXML
    private CheckBox readyBox;

    public WaitingRoomController() {
        super();
        this.model = new WaitingRoomModel();
    }

    public WaitingRoomModel getModel() {
        return model;
    }

    public double getProgressBar() {
        return progressBar.get();
    }

    public SimpleDoubleProperty progressBarProperty() {
        return progressBar;
    }

    public void resetProgressBar() {
        double divider = 1.0 / GameManager.getInstance().getPlayers().size();
        ArrayList<Player> players = GameManager.getInstance().getPlayers();
        double progress = 0.0;
        for (Player player: players) {
            if(GameManager.getInstance().getPlayerStates().get(player)) {
                progress += divider;
            }
        }
        this.progressBar.set(progress);
    }

    //    NOTE Controls for waitingRoom.fxml ----
    public void onReady() {
        // DONE onReady() Increase Progress-Bar by Player count
        GameManager.getInstance().setPlayerState(Player.getInstance(), readyBox.isSelected());
        resetProgressBar();
        ViewManager.getInstance().devLog(
                String.format("Player %s is %s.",
                        Player.getInstance().getName(),
                        readyBox.isSelected() ? "ready" : "not ready")
        );
        NetworkManager.getInstance().connection().send(
                new LocalMessage(String.format("Player %s is %s",
                        Player.getInstance().getName(),
                        readyBox.isSelected()))
        );
    }

    public void onGameAbort(){
        try {
            NetworkManager.getInstance().connection().closeConnection();
            ViewManager.getInstance().devLog("Closed connection.");
            System.out.println("Closed connection.");
        } catch (Exception e) {
            ViewManager.getInstance().errorLog("Close connection failed", e);
        }
        this.switchView(
                "Failed to load & switch view to PlayConfiguration.",
                "Successfully loaded & switched view to PlayConfiguration.",
                View.Main);
    }

    public void onGameSettings() throws UnsupportedOperationException{
        // TODO Open Dialog for game setting in WaitingRoomController
        throw new UnsupportedOperationException("Game settings dialog not implemented.");
    }

    @Override
    public void consumeDialog() throws UnsupportedOperationException {
        // TODO Implement Dialog handling for game settings.
        throw new UnsupportedOperationException("Consuming game settings not implemented.");
    }

    @Override
    public boolean verifyInput() {
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewManager.getInstance().setCurrentStatusText(statusText);
        ViewManager.getInstance().setCurrentInputField(inputField);
        ViewManager.getInstance().setCurrentOutputArea(chatArea);
        NetworkManager.getInstance().setCallback(
                model.MessageHandler()::addMessage
        );
        Bindings.bindBidirectional(progBar.progressProperty(), progressBarProperty());
        hostStartButton.setVisible(NetworkManager.getInstance().isHost());
        chatArea.setFocusTraversable(false);
        inputField.requestFocus();
        if(GameManager.getInstance().getGame() != null) {
            gameTitle.setVisible(true);
            gameTitle.setText(GameManager.getInstance().getGame().getGameTitle());
            cartridgeBoxImage.setVisible(true);
            cartridgeBoxImage.setImage(GameManager.getInstance().getGame().getGameImage());
            if(!NetworkManager.getInstance().isHost()) {

            }
        }
    }

    public void onToolbarToggle() {
        // TODO Implement action when toolbar is clicked
    }

    public void onHostStart() {
        if(GameManager.getInstance().allReady()) {
            GameManager.getInstance().startGame();
            this.switchView(
                    "Failed to load & switch view to PlayConfiguration.",
                    "Successfully loaded & switched view to PlayConfiguration.",
                    View.Gameplay);
        }
    }

    public void onInput() {
        String input = inputField.getText();
        Message msg = new ChatMessage(Player.getInstance().getName(), input);
        if(NetworkManager.getInstance().connection().send(msg)) {
            model.MessageHandler().addMessage(msg, chatArea);
        } else {
            model.MessageHandler().addMessage(new ErrorMessage(
                    input,
                    "Connection error."), chatArea);
        }
        inputField.clear();
    }
}
