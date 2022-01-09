module fhtw.cartridgescaping {
    requires javafx.controls;
    requires javafx.fxml;


    opens fhtw.cartridgescaping to javafx.fxml;
    exports fhtw.cartridgescaping;
}