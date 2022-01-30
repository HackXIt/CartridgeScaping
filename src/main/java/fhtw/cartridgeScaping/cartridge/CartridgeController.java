package fhtw.cartridgeScaping.cartridge;

import fhtw.cartridgeScaping.controller.Controller;
import fhtw.cartridgeScaping.gameplay.GameManager;
import fhtw.cartridgeScaping.util.IOResult;

import java.nio.file.Path;

public abstract class CartridgeController
        extends Controller<CartridgeModel> {
    protected final CartridgeModel model;
    protected CartridgeLoader loader;

    public CartridgeController() {
        this.model = new CartridgeModel();
    }

    public abstract void onLoad();

    public boolean loadCartridge(Path filePath) {
        loader = new CartridgeLoader(filePath);
        IOResult<Cartridge> io = model.load(loader);
        String messageOnError = "Successfully loaded Cartridge.";
        String messageOnSuccess = "Failed to load Cartridge.";
        return io.handleIoResult(messageOnError, messageOnSuccess);
    }
}
