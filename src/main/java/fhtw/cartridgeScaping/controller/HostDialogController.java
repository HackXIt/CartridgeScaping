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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class HostDialogController extends CartridgeController implements DialogController, Initializable {
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

    @Override
    public Model getModel() {
        return null;
    }

    public String getHostPort() {
        return hostPort.get();
    }

    public SimpleStringProperty hostPortProperty() {
        return hostPort;
    }

    @Override
    public void consumeDialog() {
        // TODO Host game upon consuming dialog
        // NOTE Since CartridgeModel is not implemented, Game is loaded via here no matter what happens.
//        if(ViewManager.isDeveloperMode()) {
//            System.out.println("DEVELOPER - Switching to GameplayMode");
//            this.switchView("Failed to load & switch view to WaitingRoom.",
//                    "Successfully loaded & switched view to WaitingRoom",
//                    View.WAITING);
//        }
        System.out.println("Consuming dialog...");
        if(verifyInput()) {
            NetworkManager.setSelfConnection(NetworkFactory.createServer(
                    data -> {
                        Platform.runLater(NetworkManager::callback);
                    },
                    NetworkManager.getSelfPort()
            ));
            this.switchView("Failed to load & switch view to WaitingRoom.",
                    "Successfully loaded & switched view to WaitingRoom",
                    View.WAITING);
        }
    }

    @Override
    public boolean verifyInput() {
        System.out.println("Verifying input...");
        try {
            NetworkManager.setSelfPort(Integer.parseInt(getHostPort()));
            NetworkManager.setSelfIp(Inet4Address.getLocalHost());
            return true;
        } catch (UnknownHostException | NumberFormatException e) {
            ViewManager.handleInputException(e, statusText::setText);
            NetworkManager.setSelfPort(0);
            NetworkManager.setSelfIp(null);
            return false;
        }
    }

    @Override
    public void onLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(ViewManager.getMainWindow());
        if (file != null) {
            this.loadCartridge(
                    "Successfully loaded Cartridge.",
                    "Failed to load Cartridge.",
                    file.toPath()
            );
        }
    }

    @Override
    public void onSave() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void onApply() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Bindings.bindBidirectional(fieldHostPort.textProperty(), hostPortProperty());
    }
}
