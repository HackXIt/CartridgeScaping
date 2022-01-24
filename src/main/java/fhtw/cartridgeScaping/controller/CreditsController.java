package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.Model;
import fhtw.cartridgeScaping.util.View;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * INFO Header of CreditsController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class CreditsController extends Controller implements Initializable {

    @FXML
    private TextArea creditsArea;

    public CreditsController() {
        super();
    }

    @Override
    public Model getModel() {
        return null;
    }

    //    NOTE Controls for gameCredits.fxml ----
    @FXML
    public void onCreditsBack() {
        this.switchView(
                "Failed to load & switch view to Main.",
                "Successfully loaded & switched view to Main.",
                View.Main);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewManager.getInstance().setCurrentOutputArea(creditsArea);
    }
}
