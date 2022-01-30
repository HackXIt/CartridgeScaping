package fhtw.cartridgeScaping.gameplay.text;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fhtw.cartridgeScaping.gameplay.rooms.Door;

public class DoorDescription extends Description<DoorDescription>{
    private String insideDescription;
    private String outsideDescription;

    private boolean isInside;

    public DoorDescription(String name) {
        super(name);
    }

    public DoorDescription(String name, String shortDescription) {
        super(name, shortDescription);
    }

    public DoorDescription(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
    }

    @JsonCreator
    public DoorDescription(
            @JsonProperty("name") String name,
            @JsonProperty("shortDescription") String shortDescription,
            @JsonProperty("longDescription") String longDescription,
            @JsonProperty("insideDescription") String insideDescription,
            @JsonProperty("outsideDescription") String outsideDescription) {
        super(name, shortDescription, longDescription);
        this.insideDescription = insideDescription;
        this.outsideDescription = outsideDescription;
    }

    public DoorDescription(DoorDescription doorDescription) {
        super(doorDescription);
        this.insideDescription = doorDescription.getInsideDescription();
        this.outsideDescription = doorDescription.getOutsideDescription();
    }

    @Override
    public DoorDescription cloneDescription() {
        return new DoorDescription(this);
    }

    public DoorDescription(Description description, String insideDescription, String outsideDescription) {
        super(description);
        this.insideDescription = insideDescription;
        this.outsideDescription = outsideDescription;
    }

    public String getInsideDescription() {
        return insideDescription;
    }

    public void setInsideDescription(String insideDescription) {
        this.insideDescription = insideDescription;
    }

    public String getOutsideDescription() {
        return outsideDescription;
    }

    public void setOutsideDescription(String outsideDescription) {
        this.outsideDescription = outsideDescription;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }

    @Override
    public String toString() {
        // TODO Improve toString() of DoorDescription (Seems faulty)
        return super.toString() + String.format("\n%s", isInside ? insideDescription : outsideDescription);
    }
}
