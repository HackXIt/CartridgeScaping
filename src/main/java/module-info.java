module CartridgeScaping {
    requires javafx.controls;
    requires javafx.fxml;

    opens fhtw.cartridgeScaping to javafx.fxml;
    exports fhtw.cartridgeScaping;
    opens fhtw.cartridgeScaping.controller to javafx.fxml;
    exports fhtw.cartridgeScaping.controller;
    exports fhtw.cartridgeScaping.util;
    opens fhtw.cartridgeScaping.util to javafx.fxml;
}