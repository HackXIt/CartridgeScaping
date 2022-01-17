package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.WaitingRoomModel;
import fhtw.cartridgeScaping.networking.NetworkManager;
import fhtw.cartridgeScaping.util.View;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * INFO Header of WaitingRoomController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class WaitingRoomController extends Controller implements DialogController, Initializable {
    private WaitingRoomModel model;
    private SimpleDoubleProperty progressBar = new SimpleDoubleProperty();

    @FXML
    private Text status;
    @FXML
    private ListView playerList;
    @FXML
    private ProgressBar progBar;
    @FXML
    private CheckBox readyBox;

    public WaitingRoomController() {
        super();
        model = new WaitingRoomModel();
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

    public void setProgressBar(double progressBar) {
        this.progressBar.set(progressBar);
    }

    //    NOTE Controls for waitingRoom.fxml ----
    public void onReady() {
        // DONE onReady() Increase Progress-Bar by Player count
        setProgressBar(getProgressBar() + 0.1);
        NetworkManager.setSelfReady(readyBox.isSelected());
        if (ViewManager.isDeveloperMode()) {
            System.out.printf("Player %s is %s",
                    NetworkManager.getSelf().getPlayerName(),
                    NetworkManager.isSelfReady() ? "ready" : "not ready");
        }
//        this.switchView(
//                "Failed to load & switch view to PlayConfiguration.",
//                "Successfully loaded & switched view to PlayConfiguration.",
//                View.GAMEPLAY);
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

    @Override
    public boolean verifyInput() {
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Bindings.bindBidirectional(progBar.progressProperty(), progressBarProperty());
    }
}
