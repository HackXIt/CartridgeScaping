package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.PlayConfigurationModel;
import fhtw.cartridgeScaping.util.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

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
public class PlayConfigurationController extends Controller {
    private PlayConfigurationModel playConfigurationModel;

    public PlayConfigurationController() {
        super();
        playConfigurationModel = new PlayConfigurationModel();
    }

    //    NOTE Controls for playConfiguration.fxml ----
    @FXML
    public void onHost() {
        this.openDialog(
                "Failed to open hostingDialog.",
                "Successfully opened hostingDialog.",
                View.HOST,
                "Host game..."
        );
    }

    @FXML
    public void onJoin() {
        this.openDialog(
                "Failed to open joinDialog.",
                "Successfully opened joinDialog.",
                View.JOIN,
                "Join game..."
        );
    }

    @FXML
    public void onAbortPlay() {
        this.switchView(
                "Failed to load & switch view to Main.",
                "Successfully loaded & switched view to Main.",
                View.MAIN);
    }
}
