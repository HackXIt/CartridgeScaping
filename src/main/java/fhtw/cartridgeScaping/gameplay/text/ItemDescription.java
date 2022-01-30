package fhtw.cartridgeScaping.gameplay.text;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDescription extends Description<ItemDescription>{
    private String placedDescription; // NOTE Description of item placed in room
    private String inventoryDescription; // NOTE Description of item inside inventory

    public ItemDescription(String name) {
        super(name);
    }
    public ItemDescription(String name, String shortDescription) {
        super(name, shortDescription);
    }

    public ItemDescription(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
    }

    public ItemDescription(String name, String shortDescription, String longDescription, String detailedDescription) {
        super(name, shortDescription, longDescription, detailedDescription);
    }

    @JsonCreator
    public ItemDescription(
            @JsonProperty("name") String name,
            @JsonProperty("shortDescription") String shortDescription,
            @JsonProperty("longDescription") String longDescription,
            @JsonProperty("detailedDescription") String detailedDescription,
            @JsonProperty("placedDescription") String placedDescription,
            @JsonProperty("inventoryDescription") String inventoryDescription) {
        super(name, shortDescription, longDescription, detailedDescription);
        this.placedDescription = placedDescription;
        this.inventoryDescription = inventoryDescription;
    }

    public ItemDescription(ItemDescription itemDescription) {
        super(itemDescription);
        this.placedDescription = itemDescription.getPlacedDescription();
        this.inventoryDescription = itemDescription.getInventoryDescription();
    }

    public String getPlacedDescription() {
        return placedDescription;
    }

    public void setPlacedDescription(String placedDescription) {
        this.placedDescription = placedDescription;
    }

    public String getInventoryDescription() {
        return inventoryDescription;
    }

    public void setInventoryDescription(String inventoryDescription) {
        this.inventoryDescription = inventoryDescription;
    }

    @Override
    public ItemDescription cloneDescription() {
        return new ItemDescription(this);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
