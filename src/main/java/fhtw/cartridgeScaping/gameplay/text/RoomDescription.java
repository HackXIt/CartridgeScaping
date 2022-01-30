package fhtw.cartridgeScaping.gameplay.text;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomDescription extends Description<RoomDescription>{

    public RoomDescription(String name) {
        super(name);
    }

    public RoomDescription(String name, String shortDescription) {
        super(name, shortDescription);
    }

    @JsonCreator
    public RoomDescription(
            @JsonProperty("name") String name,
            @JsonProperty("shortDescription") String shortDescription,
            @JsonProperty("longDescription") String longDescription) {
        super(name, shortDescription, longDescription);
    }

    public RoomDescription(RoomDescription roomDescription) {
        super(roomDescription);
    }

    @Override
    public RoomDescription cloneDescription() {
        return new RoomDescription(this);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
