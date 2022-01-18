package fhtw.cartridgeScaping.gameplay.text;

import java.io.Serializable;

public class Description implements Serializable {
    protected String name;
    protected String shortDescription;
    protected String longDescription;
    protected String detailedDescription;

    public Description() { }

    public Description(String name) {
        this.name = name;
    }

    public Description(String name, String shortDescription) {
        this(name);
        this.shortDescription = shortDescription;
    }

    public Description(String name, String shortDescription, String longDescription) {
        this(name, shortDescription);
        this.longDescription = longDescription;
    }

    public Description(String name, String shortDescription, String longDescription, String detailedDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.detailedDescription = detailedDescription;
    }

    public Description(Description description) {
        this.name = description.getName();
        this.shortDescription = description.getShortDescription();
        this.longDescription = description.getLongDescription();
        this.detailedDescription = description.getDetailedDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public Description cloneDescription() {
        return new Description(this);
    }

    @Override
    public String toString() {
        return String.format("%s - (%s)\n%s\n",
                name,
                shortDescription,
                longDescription);
    }
}
