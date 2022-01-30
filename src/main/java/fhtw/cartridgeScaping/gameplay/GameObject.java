package fhtw.cartridgeScaping.gameplay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fhtw.cartridgeScaping.gameplay.util.Holdable;
import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.Lookable;
import javafx.scene.image.Image;

public abstract class GameObject implements Lookable, Inspectable, Holdable {
    protected Player itemHolder = null;
    protected boolean isHeld = false;
    protected boolean canBeHeld;
    protected Image objectImage = null;

    public Image getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(Image objectImage) {
        this.objectImage = objectImage;
    }

    public abstract String lookAt();

    public abstract String inspect();

    public abstract String getName();

    @JsonIgnore
    public boolean isHeld() {
        return isHeld;
    }

    @JsonProperty("canBeHeld")
    public boolean canBeHeld() {
        return canBeHeld;
    }

    public void setCanBeHeld(boolean canBeHeld) {
        this.canBeHeld = canBeHeld;
    }

    public void pickup() {
        if(canBeHeld) {
            if(!isHeld) {
                Player.getInstance().getCurrentRoom().removeItem(this);
                Player.getInstance().addItem(this);
                itemHolder = Player.getInstance();
                isHeld = true;
            }
        }
    }

    public void drop() {
        if(canBeHeld) {
            if (isHeld) {
                Player.getInstance().getCurrentRoom().addItem(this);
                itemHolder.dropObject(this);
                itemHolder = null;
                isHeld = false;
            }
        }
    }
}
