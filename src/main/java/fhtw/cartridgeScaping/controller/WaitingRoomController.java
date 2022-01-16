package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.WaitingRoomModel;
import fhtw.cartridgeScaping.util.View;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * INFO Header of WaitingRoomController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class WaitingRoomController extends Controller implements DialogController {
    private WaitingRoomModel model;

    @FXML
    private Text status;
    @FXML
    private ListView playerList;

    public WaitingRoomController() {
        super();
        model = new WaitingRoomModel();
    }

    //    NOTE Controls for waitingRoom.fxml ----
    public void onReady() {
        // TODO onReady() Increase Progress-Bar by Player count
        this.switchView(
                "Failed to load & switch view to PlayConfiguration.",
                "Successfully loaded & switched view to PlayConfiguration.",
                View.GAMEPLAY);
    }

    public void onGameAbort(){
        this.switchView(
                "Failed to load & switch view to PlayConfiguration.",
                "Successfully loaded & switched view to PlayConfiguration.",
                View.MAIN);
    }

    public void onGameSettings() throws UnsupportedOperationException{
        // TODO Open Dialog for game setting in WaitingRoomController
        throw new UnsupportedOperationException();
    }

    @Override
    public void consumeDialog() {

    }
}
