package fhtw.cartridgeScaping.gameplay.items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fhtw.cartridgeScaping.gameplay.GameObject;
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

    @JsonIgnore
    public String getPlacedDescription() {
        return itemDescription.getPlacedDescription();
    }

    public ItemDescription getItemDescription() {
        return itemDescription;
    }

    @JsonIgnore
    public int getId() {
        return this.hashCode();
    }

    public Item cloneItem() {
        return new Item(this);
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder(itemDescription.toString());
        strBuilder.append(isHeld() ? itemDescription.getInventoryDescription() : itemDescription.getPlacedDescription());
        return strBuilder.toString();
    }

    @Override
    public String lookAt() {
        return toString();
    }

    @Override
    public String getName() {
        return itemDescription.getName();
    }

    @Override
    public String inspect() {
        return itemDescription.getDetailedDescription();
    }
}
