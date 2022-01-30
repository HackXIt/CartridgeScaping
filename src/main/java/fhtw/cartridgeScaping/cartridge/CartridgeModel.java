package fhtw.cartridgeScaping.cartridge;

import fhtw.cartridgeScaping.util.IOResult;

import java.io.IOException;
import java.nio.file.Path;

public class CartridgeModel {
    private Cartridge cartridge;

    public IOResult<Cartridge> load(CartridgeLoader loader) {
        IOResult<Cartridge> io = new IOResult<>();
        try {
            cartridge = loader.load();
            return io.success(loader.getFile().toString(), cartridge);
        } catch (IOException e) {
            return io.failure("Loading cartridge failed.", e);
        }
    }

    public Cartridge getCartridge() {
        return cartridge;
    }

    public void setCartridge(Cartridge cartridge) {
        this.cartridge = cartridge;
    }
}
