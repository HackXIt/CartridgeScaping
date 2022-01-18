package fhtw.cartridgeScaping.gameplay;

import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.Lookable;

public abstract class GameObject implements Lookable, Inspectable {
    protected Player itemHolder;
    protected boolean isHeld;
    protected boolean canBeHeld;
    protected String objectString = super.toString();

    public String getString() {
        return objectString;
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
