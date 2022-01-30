package fhtw.cartridgeScaping.gameplay.text;

import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.Lookable;

public class PlayerDescription extends Description<PlayerDescription> {

    public PlayerDescription() {

    }

    public PlayerDescription(PlayerDescription playerDescription) {
        super(playerDescription);
    }

    @Override
    public PlayerDescription cloneDescription() {
        return new PlayerDescription(this);
    }


    // TODO Improve PlayerDescription (Currently empty)
    // TODO Add special inspectable text for the PlayerDescription
}
