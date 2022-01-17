package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.Model;
import fhtw.cartridgeScaping.util.IOResult;
import fhtw.cartridgeScaping.util.View;
import javafx.fxml.FXMLLoader;

public abstract class Controller {
    public Controller() {
        // NOTE Global controller initialization (if something applies to all of them)
    }

    public abstract Model getModel();

    public void switchView(String messageOnError,
                           String messageOnSuccess,
                           View view) {
        IOResult<FXMLLoader> io = ViewManager.switchTo(view);
        io.handleIoResult(messageOnError, messageOnSuccess);
    }

    public void openDialog(String messageOnError,
                           String messageOnSuccess,
                           View view,
                           String dialogTitle) {
        IOResult<FXMLLoader> io = ViewManager.openDialog(view, dialogTitle);
        io.handleIoResult(messageOnError, messageOnSuccess);
    }
}
