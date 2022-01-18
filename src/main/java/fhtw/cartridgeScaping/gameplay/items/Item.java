package fhtw.cartridgeScaping.gameplay.items;

import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.text.ItemDescription;
import fhtw.cartridgeScaping.gameplay.util.InventoryItem;

public class Item extends GameObject {
    protected ItemDescription itemDesc;

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

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder(itemDesc.toString());
        strBuilder.append(isHeld() ? itemDesc.getInventoryDescription() : itemDesc.getRoomDesc());
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
        return itemDesc.getDetailedDescription();
    }
}
