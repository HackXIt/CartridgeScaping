package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.ViewManager;
import fhtw.cartridgeScaping.cartridge.Cartridge;
import fhtw.cartridgeScaping.cartridge.CartridgeModel;
import fhtw.cartridgeScaping.util.IOResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

public class HostDialogController implements DialogController {
    private CartridgeModel model;

    @FXML
    private TextField fieldHostPort;

    @FXML
    private Button btnLoadCartridge;

    @FXML
    private Text status;

    public HostDialogController(CartridgeModel model) {
        this.model = new CartridgeModel();
    }

    @Override
    public void consumeDialog() {
        // TODO Host game upon consuming dialog
    }

    @FXML
    public void onLoadCartridge() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(ViewManager.getMainWindow());
        if (file != null) {
            IOResult<Cartridge> io = model.load(file.toPath());
            if(io.isOk()) {
                // TODO switch view and host game with model
            } else {
                // TODO handle failure of onLoadCartridge()
            }
        }
    }
}
