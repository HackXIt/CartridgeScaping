package fhtw.cartridgeScaping.cartridge;

import fhtw.cartridgeScaping.util.IOResult;

import java.nio.file.Path;

public class CartridgeModel {
    private Cartridge cartridge;

    public IOResult<Cartridge> load(Path file) {
        IOResult<Cartridge> io = new IOResult<>();
        cartridge = new Cartridge();
        io.setData(cartridge);
        return io.failure("load() not implemented.",
                new UnsupportedOperationException("Loading of cartridge is not yet implemented."));
    }

    public Cartridge getCartridge() {
        return cartridge;
    }
}
