package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.cartridge.Cartridge;
import fhtw.cartridgeScaping.cartridge.CartridgeController;
import fhtw.cartridgeScaping.util.IOResult;
import fhtw.cartridgeScaping.util.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

public class HostDialogController extends CartridgeController implements DialogController {
    @FXML
    private TextField fieldHostPort;

    @FXML
    private Button btnLoadCartridge;

    @FXML
    private Text status;

    public HostDialogController() {
        super();
    }

    @Override
    public void consumeDialog() {
        // TODO Host game upon consuming dialog
        // NOTE Since CartridgeModel is not implemented, Game is loaded via here no matter what happens.
        if(ViewManager.isDeveloperMode()) {
            System.out.println("DEVELOPER - Switching to GameplayMode");
            this.switchView("Failed to load & switch view to WaitingRoom.",
                    "Successfully loaded & switched view to WaitingRoom",
                    View.WAITING);
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
}
