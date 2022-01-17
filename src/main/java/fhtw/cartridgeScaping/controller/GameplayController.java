package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.model.GameplayModel;
import fhtw.cartridgeScaping.model.Model;
import fhtw.cartridgeScaping.util.View;
import javafx.event.ActionEvent;

/**
 * INFO Header of GameplayController.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/controller
 * @project CartridgeScaping
 */
public class GameplayController extends Controller{
    private final GameplayModel model;

    public GameplayController() {
        super();
        this.model = new GameplayModel();
    }

    @Override
    public GameplayModel getModel() {
        return model;
    }


    //    NOTE Controls for gameplayView.fxml ----
    public void onQuitGameplay(ActionEvent actionEvent) {
        // TODO RoomMessage to notify that player has left the game.
        this.switchView(
                "Failed to load & switch view to Main.",
                "Successfully loaded & switched view to Main.",
                View.MAIN);
    }
}
