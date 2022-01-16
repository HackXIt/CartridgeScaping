package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.util.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * INFO Header of CreditsController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class CreditsController extends Controller{

    public CreditsController() {
        super();
    }

    //    NOTE Controls for gameCredits.fxml ----
    @FXML
    public void onCreditsBack() {
        this.switchView(
                "Failed to load & switch view to Main.",
                "Successfully loaded & switched view to Main.",
                View.MAIN);
    }
}
