module CartridgeScaping {
    requires javafx.controls;
    requires javafx.fxml;

    opens fhtw.cartridgeScaping to javafx.fxml;
    exports fhtw.cartridgeScaping;
    opens fhtw.cartridgeScaping.controller to javafx.fxml;
    exports fhtw.cartridgeScaping.controller;
    exports fhtw.cartridgeScaping.util;
    opens fhtw.cartridgeScaping.util to javafx.fxml;
    opens fhtw.cartridgeScaping.cartridge to javafx.fxml;
    exports fhtw.cartridgeScaping.cartridge;
    opens fhtw.cartridgeScaping.model to javafx.fxml;
    exports fhtw.cartridgeScaping.model;
    opens fhtw.cartridgeScaping.networking to javafx.fxml;
    exports fhtw.cartridgeScaping.networking;
    opens fhtw.cartridgeScaping.console to javafx.fxml;
    exports fhtw.cartridgeScaping.console;
}