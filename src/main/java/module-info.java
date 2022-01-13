module CartridgeScaping {
    requires javafx.controls;
    requires javafx.fxml;

    opens fhtw.cartridgeScaping to javafx.fxml;
    exports fhtw.cartridgeScaping;
}