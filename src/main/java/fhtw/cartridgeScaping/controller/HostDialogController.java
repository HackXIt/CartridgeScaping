package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.cartridge.CartridgeController;
import fhtw.cartridgeScaping.gameplay.GameManager;
import fhtw.cartridgeScaping.networking.NetworkManager;
import fhtw.cartridgeScaping.networking.NetworkFactory;
import fhtw.cartridgeScaping.util.View;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class HostDialogController
        extends CartridgeController
        implements DialogController, Initializable {
    private final SimpleStringProperty hostPort = new SimpleStringProperty();

    @FXML
    private TextField fieldHostPort;
    @FXML
    private Button btnLoadCartridge;
    @FXML
    private Text statusText;

    public HostDialogController() {
        super();
    }

    public String getHostPort() {
        return hostPort.get();
    }

    public SimpleStringProperty hostPortProperty() {
        return hostPort;
    }

    @Override
    public void consumeDialog() {
        ViewManager.getInstance().devLog("Consuming dialog...");
        // TODO Host game upon consuming dialog
        // NOTE Since CartridgeModel is not implemented, Game is loaded via here no matter what happens.
//        if(ViewManager.isDeveloperMode()) {
//            System.out.println("DEVELOPER - Switching to GameplayMode");
//            this.switchView("Failed to load & switch view to WaitingRoom.",
//                    "Successfully loaded & switched view to WaitingRoom",
//                    View.WAITING);
//        }
        if(verifyInput()) {
            NetworkManager.getInstance().setConnection(NetworkFactory.createServer(
                    data -> {
                        Platform.runLater(() -> {
                            NetworkManager.getInstance().callback(data);
                        });
                    },
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
        ViewManager.getInstance().devLog("Verifying input...");
        try {
            if(ViewManager.getInstance().developerMode()) {
                // NOTE Overriding default behaviour for developer mode (always use same port & localHost)
                NetworkManager.getInstance().setPort(55555);
                NetworkManager.getInstance().setTargetIp(Inet4Address.getLocalHost());
            } else {
                NetworkManager.getInstance().setPort(Integer.parseInt(getHostPort()));
                NetworkManager.getInstance().setTargetIp(Inet4Address.getLocalHost());
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(ViewManager.getInstance().getPrimaryStage());
        if (file != null) {
            if(this.loadCartridge(file.toPath())) {
                GameManager.getInstance().handleLoadSuccess(statusText::setText);
            } else {
                GameManager.getInstance().handleLoadError(statusText::setText);
            }

//            GameManager.getInstance().admitCartridge(this.getModel());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewManager.getInstance().setCurrentStatusText(statusText);
        Bindings.bindBidirectional(fieldHostPort.textProperty(), hostPortProperty());
    }
}
