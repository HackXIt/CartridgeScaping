package fhtw.cartridgeScaping.cartridge;

import fhtw.cartridgeScaping.controller.Controller;
import fhtw.cartridgeScaping.util.IOResult;

import java.nio.file.Path;

public abstract class CartridgeController extends Controller {
    protected CartridgeModel model;

    public abstract void onLoad();
    public abstract void onSave();
    public abstract void onApply();


    public void loadCartridge(String messageOnError, String messageOnSucess, Path filePath) {
        IOResult<Cartridge> io = model.load(filePath);
        if(io.handleIoResult(messageOnError, messageOnSucess)) {
            // TODO switch view and host game with model
        } else {
            // TODO handle failure of onLoadCartridge()
        }
    }
}
