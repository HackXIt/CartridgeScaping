package fhtw.cartridgeScaping.gameplay.items;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.text.ItemDescription;
import fhtw.cartridgeScaping.networking.NetworkManager;

@JsonAutoDetect(setterVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class Item extends GameObject<Item> {
    protected ItemDescription itemDescription;

    public Item(boolean canBeHeld) {
        super(canBeHeld);
    }

    public Item(boolean canBeHeld, ItemDescription itemDescription) {
        super(canBeHeld);
        this.itemDescription = itemDescription.cloneDescription();
    }

    public Item(Item item, String originalID) {
        super(item, originalID);
        this.itemDescription = item.getItemDescription().cloneDescription();
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

    public void setItemDescription(ItemDescription itemDescription) {
        this.itemDescription = itemDescription.cloneDescription();
    }

    /* NOTE Cloning items is only allowed for Host because of original reference to objectID */
    @Override
    public Item cloneObject() {
        if(NetworkManager.getInstance().isHost()) {
            // TODO Send HostMessage to clients, containing the newly created object
            return new Item(this, String.valueOf(hashCode()));
        }
        return null;
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
