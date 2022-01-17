package fhtw.cartridgeScaping.gameplay.text;

public class ItemDescription extends Description{
    private String roomDesc; // NOTE Description of item placed in room
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

    public ItemDescription(String name,
                           String shortDescription,
                           String longDescription,
                           String detailedDescription,
                           String roomDesc,
                           String inventoryDescription) {
        this(name, shortDescription, longDescription, detailedDescription);
        this.roomDesc = roomDesc;
        this.inventoryDescription = inventoryDescription;
    }

    public ItemDescription(ItemDescription itemDescription) {
        super(itemDescription);
        this.roomDesc = itemDescription.getRoomDesc();
        this.inventoryDescription = itemDescription.getInventoryDescription();
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public String getInventoryDescription() {
        return inventoryDescription;
    }

    public void setInventoryDescription(String inventoryDescription) {
        this.inventoryDescription = inventoryDescription;
    }

    @Override
    public Description cloneDescription() {
        return new ItemDescription(this);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
