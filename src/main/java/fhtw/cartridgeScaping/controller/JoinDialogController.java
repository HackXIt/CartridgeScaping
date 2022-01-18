package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.cartridge.CartridgeController;
import fhtw.cartridgeScaping.networking.NetworkFactory;
import fhtw.cartridgeScaping.networking.NetworkManager;
import fhtw.cartridgeScaping.util.View;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class JoinDialogController
        extends CartridgeController
        implements DialogController, Initializable {
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
    public void consumeDialog() {
        if(verifyInput()) {
            NetworkManager.getInstance().setConnection(
                    NetworkFactory.createClient(
                            data -> {
                                Platform.runLater(() -> {
                                    NetworkManager.getInstance().callback(data);
                                });
                            },
                    NetworkManager.getInstance().getTargetIp(),
                    NetworkManager.getInstance().getPort())
            );
            NetworkManager.getInstance().connection().startConnection();
            this.switchView("Failed to load & switch view to WaitingRoom.",
                    "Successfully loaded & switched view to WaitingRoom",
                    View.WAITING);
        }
    }

    @Override
    public boolean verifyInput() {
        try {
            if(ViewManager.getInstance().developerMode()) {
                // NOTE Overriding default behaviour for developer mode (always use same port & localHost)
                NetworkManager.getInstance().setPort(55555);
                NetworkManager.getInstance().setTargetIp(Inet4Address.getLocalHost());
            } else {
                NetworkManager.getInstance().setPort(Integer.parseInt(fieldPort.getText()));
                NetworkManager.getInstance().setTargetIp(Inet4Address.getByName(fieldIp.getText()));
            }
            return true;
        } catch (UnknownHostException | NumberFormatException e) {
            ViewManager.getInstance().handleInputException(e, statusText::setText);
            NetworkManager.getInstance().setPort(0);
            NetworkManager.getInstance().setTargetIp(null);
            return false;
        }
    }

    @Override
    public void onLoad() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewManager.getInstance().setCurrentStatusText(statusText);
    }

    public void onPortChanged() {
    }

    public void onIpChanged() {
    }
}
