package fhtw.cartridgeScaping.gameplay.items;

import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.text.ItemDescription;

public class Item {
    protected ItemDescription itemDesc;
    protected Player itemHolder;
    protected boolean isHeld;

    public Item(ItemDescription itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Item(Item item) {
        // TODO Implement deep-copy of Item
        this.itemDesc = (ItemDescription) item.getItemDesc().cloneDescription();
        this.itemHolder = item.itemHolder;
        this.isHeld = item.isHeld;
    }

    public String getName() {
        return itemDesc.getName();
    }

    public String getRoomDesc() {
        return itemDesc.getRoomDesc();
    }

    public ItemDescription getItemDesc() {
        return itemDesc;
    }

    public int getId() {
        return this.hashCode();
    }

    public boolean isHeld() {
        return isHeld;
    }

    public Item cloneItem() {
        return new Item(this);
    }

    public void drop(Room room) {
        if(isHeld()) {
            room.addItem(this);
            itemHolder.dropItem(this);
            itemHolder = null;
            isHeld = false;
        }

    }

    public void pickup(Room room, Player player) {
        if(!isHeld()) {
            room.removeItem(this.getName());
            player.addItem(this);
            itemHolder = player;
        }
    }

    @Override
    public String toString() {
        return itemDesc.toString();
    }
}
