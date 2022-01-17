package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.cartridge.CartridgeController;
import fhtw.cartridgeScaping.model.Model;
import fhtw.cartridgeScaping.networking.NetworkManager;
import fhtw.cartridgeScaping.networking.NetworkFactory;
import fhtw.cartridgeScaping.util.View;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;

import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class JoinDialogController extends CartridgeController implements DialogController, Initializable {
    private SimpleStringProperty textPort = new SimpleStringProperty();
    private SimpleStringProperty textIp = new SimpleStringProperty();
    private SimpleStringProperty status = new SimpleStringProperty();
    // TODO Check fieldPort & fieldIp for correct input upon change

    @FXML
    private TextField fieldPort;
    @FXML
    private TextField fieldIp;
    @FXML
    private Text statusText;

    public JoinDialogController() {
        super();
    }

    @Override
    public Model getModel() {
        return null;
    }

    public String getTextPort() {
        return textPort.get();
    }

    public SimpleStringProperty textPortProperty() {
        return textPort;
    }

    public String getTextIp() {
        return textIp.get();
    }

    public SimpleStringProperty textIpProperty() {
        return textIp;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    @Override
    public void consumeDialog() {
        if(verifyInput()) {
            NetworkManager.setSelfConnection(NetworkFactory.createClient(
                    data -> {
                        Platform.runLater(NetworkManager::callback);
                    },
                    NetworkManager.getSelfIp(),
                    NetworkManager.getSelfPort()
            ));
            this.switchView("Failed to load & switch view to WaitingRoom.",
                    "Successfully loaded & switched view to WaitingRoom",
                    View.WAITING);
        }
    }

    @Override
    public boolean verifyInput() {
        try {
            NetworkManager.setSelfPort(Integer.parseInt(getTextPort()));
            NetworkManager.setSelfIp(Inet4Address.getByName(getTextIp()));
            return true;
        } catch (UnknownHostException | NumberFormatException e) {
            ViewManager.handleInputException(e, statusText::setText);
            NetworkManager.setSelfPort(0);
            NetworkManager.setSelfIp(null);
            return false;
        }
    }

    @Override
    public void onLoad() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void onSave() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void onApply() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Bindings.bindBidirectional(fieldPort.textProperty(), textPortProperty());
        Bindings.bindBidirectional(fieldIp.textProperty(), textIpProperty());
        Bindings.bindBidirectional(statusText.textProperty(), statusProperty());
    }

    public void onPortChanged(InputMethodEvent inputMethodEvent) {
    }

    public void onIpChanged(InputMethodEvent inputMethodEvent) {
    }
}
