package fhtw.cartridgeScaping.gameplay;

import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.InventoryItem;
import fhtw.cartridgeScaping.gameplay.util.Lookable;

public abstract class GameObject implements Lookable, Inspectable {
    protected Player itemHolder;
    protected boolean isHeld;
    protected boolean canBeHeld;

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

    public void pickup(Room currentRoom, Player player) {
        if(canBeHeld) {
            if(!isHeld) {
                currentRoom.removeItem(this.hashCode());
                player.addItem(this);
                itemHolder = player;
                isHeld = true;
            }
        }
    }

//    public void drop(Item Item) {
//        if(isHeld) {
//            room.addItem((Item) this);
//            itemHolder.dropItem((Item) this);
//            itemHolder = null;
//            isHeld = false;
//        }
//    }
}
