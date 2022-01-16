module CartridgeScaping {
    requires javafx.controls;
    requires javafx.fxml;

    // Main Package
    opens fhtw.cartridgeScaping to javafx.fxml;
    exports fhtw.cartridgeScaping;

    // Controller Package
    opens fhtw.cartridgeScaping.controller to javafx.fxml;
    exports fhtw.cartridgeScaping.controller;

    // Main Utility Package
    exports fhtw.cartridgeScaping.util;
    opens fhtw.cartridgeScaping.util to javafx.fxml;

    // Cartridge package
    opens fhtw.cartridgeScaping.cartridge to javafx.fxml;
    exports fhtw.cartridgeScaping.cartridge;

    // Model Package
    opens fhtw.cartridgeScaping.model to javafx.fxml;
    exports fhtw.cartridgeScaping.model;

    // Networking Package
    opens fhtw.cartridgeScaping.networking to javafx.fxml;
    exports fhtw.cartridgeScaping.networking;

    // Gameplay Package
    opens fhtw.cartridgeScaping.gameplay to javafx.fxml;
    exports fhtw.cartridgeScaping.gameplay;
    opens fhtw.cartridgeScaping.gameplay.console to javafx.fxml;
    exports fhtw.cartridgeScaping.gameplay.console;
    opens fhtw.cartridgeScaping.gameplay.items to javafx.fxml;
    exports fhtw.cartridgeScaping.gameplay.items;
    opens fhtw.cartridgeScaping.gameplay.rooms to javafx.fxml;
    exports fhtw.cartridgeScaping.gameplay.rooms;
    opens fhtw.cartridgeScaping.gameplay.text to javafx.fxml;
    exports fhtw.cartridgeScaping.gameplay.text;
    opens fhtw.cartridgeScaping.gameplay.util to javafx.fxml;
    exports fhtw.cartridgeScaping.gameplay.util;
}