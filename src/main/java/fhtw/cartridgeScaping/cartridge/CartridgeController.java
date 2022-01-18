package fhtw.cartridgeScaping.cartridge;

import fhtw.cartridgeScaping.controller.Controller;
import fhtw.cartridgeScaping.gameplay.GameManager;
import fhtw.cartridgeScaping.util.IOResult;

import java.nio.file.Path;

public abstract class CartridgeController
        extends Controller<CartridgeModel> {

    public abstract void onLoad();

    public boolean loadCartridge(Path filePath) {
        IOResult<Cartridge> io = model.load(filePath);
        String messageOnError = "Successfully loaded Cartridge.";
        String messageOnSuccess = "Failed to load Cartridge.";
        if(io.handleIoResult(messageOnError, messageOnSuccess)) {
            // TODO switch view and host game with model
            GameManager.getInstance().admitCartridge(this.getModel());
            GameManager.getInstance().startGame();
            return true;
        } else {
            // TODO handle failure of loadCartridge
            return false;
        }
    }
}
