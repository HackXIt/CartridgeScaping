package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.model.PlayConfigurationModel;
import fhtw.cartridgeScaping.util.View;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/* NOTE PlayConfigurationController currently deprecated
The PlayConfigurationController is unnecessary and the control will be handled by MainMenuController

If the playConfiguration.fxml View ever requires additional control (= meaning more code),
this will become the controller for that View again, simply for readability.

But as of now, this is not necessary.
 */

/**
 * INFO Header of PlayConfigurationController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping
 * @project CartridgeScaping
 */
public class PlayConfigurationController
        extends Controller
        implements Initializable {
    private final PlayConfigurationModel model;
    private final SimpleStringProperty playerName = new SimpleStringProperty();
    private final SimpleStringProperty shortDescription = new SimpleStringProperty();
    private final SimpleStringProperty longDescription = new SimpleStringProperty();
    private final SimpleStringProperty status = new SimpleStringProperty();

    public PlayConfigurationController() {
        super();
        model = new PlayConfigurationModel();
    }

    @FXML
    private TextField fieldPlayerName;
    @FXML
    private TextArea areaShortDescription;
    @FXML
    private TextArea areaLongDescription;
    @FXML
    private Text statusText;

    @Override
    public PlayConfigurationModel getModel() {
        return model;
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public SimpleStringProperty playerNameProperty() {
        return playerName;
    }

    public String getShortDescription() {
        return shortDescription.get();
    }

    public SimpleStringProperty shortDescriptionProperty() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription.get();
    }

    public SimpleStringProperty longDescriptionProperty() {
        return longDescription;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    private boolean checkInput() {
        if(playerName.get() != null && !playerName.get().isEmpty() && playerName.get().length() >= 3) {
            Player.getInstance().setName(playerName.get());
            Player.getInstance().setShortDescription(
                    shortDescription.get() != null ? shortDescription.get() : ""
            );
            Player.getInstance().setLongDescription(
                    longDescription.get() != null ? longDescription.get() : ""
            );
            return true;
        } else {
            statusText.setVisible(true);
            statusText.setText("Failed: A player name is essential. Please enter a valid name.");
            // TODO Reset visibility of statusText after some time via multithreaded task
            return false;
        }
    }

    //    NOTE Controls for playConfiguration.fxml ----
    @FXML
    public void onHost() {
        if (checkInput()){
            this.openDialog(
                    "Failed to open hostingDialog.",
                    "Successfully opened hostingDialog.",
                    View.Host,
                    "Host game..."
            );
        }
    }

    @FXML
    public void onJoin() {
        if (checkInput()) {
            this.openDialog(
                    "Failed to open joinDialog.",
                    "Successfully opened joinDialog.",
                    View.Join,
                    "Join game..."
            );
        }
    }

    @FXML
    public void onAbortPlay() {
        this.switchView(
                "Failed to load & switch view to Main.",
                "Successfully loaded & switched view to Main.",
                View.Main);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewManager.getInstance().setCurrentStatusText(statusText);
        Bindings.bindBidirectional(fieldPlayerName.textProperty(), playerNameProperty());
        Bindings.bindBidirectional(areaShortDescription.textProperty(), shortDescriptionProperty());
        Bindings.bindBidirectional(areaLongDescription.textProperty(), longDescriptionProperty());
        Bindings.bindBidirectional(statusText.textProperty(), statusProperty());
    }
}
