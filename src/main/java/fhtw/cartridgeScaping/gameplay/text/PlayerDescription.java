package fhtw.cartridgeScaping.gameplay.text;

import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.Lookable;

public class PlayerDescription extends Description implements Lookable, Inspectable {
    public PlayerDescription(String name) {
        super(name);
    }

    public PlayerDescription(String name, String shortDescription) {
        super(name, shortDescription);
    }

    public PlayerDescription(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
    }

    public PlayerDescription(String name, String shortDescription, String longDescription, String detailedDescription) {
        super(name, shortDescription, longDescription, detailedDescription);
    }

    public PlayerDescription(Description description) {
        super(description);
    }
    // TODO Improve PlayerDescription (Currently empty)
    // TODO Add special inspectable text for the PlayerDescription

    @Override
    public String inspect() {
        return getDetailedDescription();
    }

    @Override
    public String lookAt() {
        return toString();
    }
}
