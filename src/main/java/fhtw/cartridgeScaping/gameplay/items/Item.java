package fhtw.cartridgeScaping.gameplay.items;

import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.text.ItemDescription;

public class Item extends GameObject {
    protected ItemDescription itemDescription;

    public Item(ItemDescription itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Item(Item item) {
        // TODO Implement deep-copy of Item
        this.itemDescription = (ItemDescription) item.getItemDescription().cloneDescription();
        this.itemHolder = item.itemHolder;
        this.isHeld = item.isHeld;
    }

    public String getName() {
        return itemDescription.getName();
    }

    public String getRoomDesc() {
        return itemDescription.getRoomDesc();
    }

    public ItemDescription getItemDescription() {
        return itemDescription;
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

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder(itemDescription.toString());
        strBuilder.append(isHeld() ? itemDescription.getInventoryDescription() : itemDescription.getRoomDesc());
        return strBuilder.toString();
    }

    @Override
    public String lookAt() {
        return toString();
    }

    public void dropItem(Room room) {
        if(isHeld) {
            room.addItem(this);
            itemHolder.dropItem(this);
            itemHolder = null;
            isHeld = false;
        }
    }

    @Override
    public String inspect() {
        return itemDescription.getDetailedDescription();
    }
}
