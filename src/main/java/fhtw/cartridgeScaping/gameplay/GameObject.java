package fhtw.cartridgeScaping.gameplay;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fhtw.cartridgeScaping.gameplay.util.Holdable;
import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.Lookable;
import fhtw.cartridgeScaping.networking.NetworkManager;
import javafx.scene.image.Image;

public abstract class GameObject<T extends GameObject>
        implements Lookable, Inspectable, Holdable {
    @JsonIgnore
    protected Player itemHolder = null;
    @JsonIgnore
    protected boolean isHeld = false;
    protected boolean canBeHeld;
    @JsonIgnore
    protected Image objectImage = null;
    protected String objectTypeReference;
    protected String originalID;

    public GameObject() {}

    public GameObject(boolean canBeHeld) {
        this.canBeHeld = canBeHeld;
    }

    public GameObject(GameObject object, String originalID) {
        this.canBeHeld = object.canBeHeld();
        this.objectImage = object.getObjectImage();
        this.objectTypeReference = object.getObjectTypeReference();
        this.originalID = originalID;
    }

    public Image getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(Image objectImage) {
        this.objectImage = objectImage;
    }

    public String getObjectTypeReference() {
        if (objectTypeReference == null) {
            objectTypeReference = this.getClass().getName();
        }
        return objectTypeReference;
    }

    public void setObjectTypeReference(String objectTypeReference) {
        this.objectTypeReference = objectTypeReference;
    }

    public String getOriginalID() throws IllegalStateException {
        if(originalID == null) {
            if(NetworkManager.getInstance().isHost()) {
                originalID = String.valueOf(hashCode());
            }
        }
        return originalID;
    }

    public void setOriginalID(String originalID) {
        this.originalID = originalID;
    }

    public abstract String lookAt();

    public abstract String inspect();

    public abstract String getName();

    public abstract T cloneObject();

    @JsonIgnore
    public boolean isHeld() {
        return isHeld;
    }

    @JsonProperty("canBeHeld")
    public boolean canBeHeld() {
        return canBeHeld;
    }

    public void setCanBeHeld(boolean canBeHeld) {
        this.canBeHeld = canBeHeld;
    }

    public void pickup() {
        if(canBeHeld) {
            if(!isHeld) {
                Player.getInstance().getCurrentRoom().removeItem(this);
                Player.getInstance().addItem(this);
                itemHolder = Player.getInstance();
                isHeld = true;
            }
        }
    }

    public void drop() {
        if(canBeHeld) {
            if (isHeld) {
                Player.getInstance().getCurrentRoom().addItem(this);
                itemHolder.dropObject(this);
                itemHolder = null;
                isHeld = false;
            }
        }
    }
}
