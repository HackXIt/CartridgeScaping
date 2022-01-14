package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.View;
import fhtw.cartridgeScaping.ViewManager;
import javafx.event.ActionEvent;

import java.io.IOException;

/* NOTE CreditsController currently deprecated
The CreditsController is unnecessary and the control will be handled by MainMenuController

If the gameCredits.fxml View ever requires additional control (= meaning more code),
this will become the controller for that View again, simply for readability.

But as of now, this is not necessary.
 */

/**
 * INFO Header of CreditsController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class CreditsController {
    public void onCreditsBack(ActionEvent actionEvent) throws IOException {
        ViewManager.switchTo(View.MAIN);
    }
}
