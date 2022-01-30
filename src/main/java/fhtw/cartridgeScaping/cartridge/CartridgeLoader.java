package fhtw.cartridgeScaping.cartridge;

import com.fasterxml.jackson.databind.JsonNode;
import fhtw.cartridgeScaping.json.Json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class CartridgeLoader {
    private File file;
    private JsonNode cartridgeJson;

    public CartridgeLoader(Path path) {
        file = path.toFile();
    }

    public Cartridge load() throws IOException {
        cartridgeJson = Json.getInstance().getObjectMapper().readTree(file);
        return Json.getInstance().fromJson(cartridgeJson, Cartridge.class);
    }

    public File getFile() {
        return file;
    }

    public JsonNode getCartridgeJson() {
        return cartridgeJson;
    }
}
