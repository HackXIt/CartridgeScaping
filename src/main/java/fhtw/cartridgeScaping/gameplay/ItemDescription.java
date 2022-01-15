package fhtw.cartridgeScaping.gameplay;

public class ItemDescription extends Description{
    private String roomDesc; // NOTE Description of item placed in room
    private String inventoryDescription; // NOTE Description of item inside inventory
    private String detailedDescription; // NOTE Description of item upon closer inspection

    public ItemDescription(String name,
                           String shortDescription,
                           String longDescription,
                           String roomDesc,
                           String inventoryDescription,
                           String detailedDescription) {
        this(name, shortDescription, longDescription);
        this.roomDesc = roomDesc;
        this.inventoryDescription = inventoryDescription;
        this.detailedDescription = detailedDescription;
    }

    public ItemDescription(String name) {
        super(name);
    }

    public ItemDescription(String name, String shortDescription) {
        super(name, shortDescription);
    }

    public ItemDescription(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
    }

    public ItemDescription(ItemDescription itemDescription) {
        super(itemDescription);
        this.roomDesc = itemDescription.getRoomDesc();
        this.inventoryDescription = itemDescription.getInventoryDescription();
        this.detailedDescription = itemDescription.getDetailedDescription();
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

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
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
