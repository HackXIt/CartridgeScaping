package fhtw.cartridgeScaping.gameplay;

import java.util.Objects;

public class Item {
    protected ItemDescription itemDesc;
    protected int id;

    public Item(ItemDescription itemDesc) {
        this.itemDesc = itemDesc;
        this.id = Objects.hash(itemDesc.getName(),
                itemDesc.getShortDescription(),
                itemDesc.getLongDescription(),
                itemDesc.getDetailedDescription());
    }

    public Item(Item item) {
        // TODO Implement deep-copy of Item
        this.itemDesc = (ItemDescription) item.getItemDesc().cloneDescription();
        this.id = Objects.hash(itemDesc.getName(),
                itemDesc.getShortDescription(),
                itemDesc.getLongDescription(),
                itemDesc.getDetailedDescription());
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
        return id;
    }

    public Item cloneItem() {
        return new Item(this);
    }

    public void drop(Room room) {
        room.addItem(this);
    }

    public void pickup(Room room, Player player) {
        room.removeItem(this.getName());
        player.addItem(this);
    }

    @Override
    public String toString() {
        return itemDesc.toString();
    }
}
