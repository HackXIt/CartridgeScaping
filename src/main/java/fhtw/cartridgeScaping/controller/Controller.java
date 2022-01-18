package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.Model;
import fhtw.cartridgeScaping.util.IOResult;
import fhtw.cartridgeScaping.util.View;
import javafx.fxml.FXMLLoader;

public abstract class Controller<T> {
    protected T model;
    public Controller() {
        // NOTE Global controller initialization (if something applies to all of them)
    }

    public T getModel() {
        return model;
    }

    public void switchView(String messageOnError,
                           String messageOnSuccess,
                           View view) {
        IOResult<FXMLLoader> io = ViewManager.getInstance().switchTo(view);
        io.handleIoResult(messageOnError, messageOnSuccess);
    }

    public void openDialog(String messageOnError,
                           String messageOnSuccess,
                           View view,
                           String dialogTitle) {
        IOResult<FXMLLoader> io = ViewManager.getInstance().openDialog(view, dialogTitle);
        io.handleIoResult(messageOnError, messageOnSuccess);
    }
}
