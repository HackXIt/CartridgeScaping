package fhtw.cartridgeScaping.gameplay;

import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.Lookable;
import javafx.scene.image.Image;

public abstract class GameObject implements Lookable, Inspectable {
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

    public boolean isHeld() {
        return isHeld;
    }
    public boolean canBeHeld() {
        return canBeHeld;
    }

    public void setCanBeHeld(boolean canBeHeld) {
        this.canBeHeld = canBeHeld;
    }

    public void pickup() {
        if(canBeHeld) {
            if(!isHeld) {
                Player.getInstance().getCurrentRoom().removeItem((Item) this);
                Player.getInstance().addItem(this);
                itemHolder = Player.getInstance();
                isHeld = true;
            }
        }
    }

    public void drop() {
        if(isHeld) {
            Player.getInstance().getCurrentRoom().addItem((Item) this);
            itemHolder.dropItem((Item) this);
            itemHolder = null;
            isHeld = false;
        }
    }
}
