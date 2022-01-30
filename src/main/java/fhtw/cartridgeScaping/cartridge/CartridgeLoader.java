package fhtw.cartridgeScaping.cartridge;

import com.fasterxml.jackson.databind.JsonNode;
import fhtw.cartridgeScaping.json.Json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class CartridgeLoader {
    private File file;
    private JsonNode node;

    public CartridgeLoader(Path path) {
        file = path.toFile();
    }

    public Cartridge load() throws IOException {
        node = Json.getInstance().getObjectMapper().readTree(file);
        Cartridge cart = new Cartridge();
        return cart;
    }
}
