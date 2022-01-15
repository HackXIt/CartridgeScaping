package fhtw.cartridgeScaping.gameplay;

public class Description {
    protected String name;
    protected String shortDescription;
    protected String longDescription;

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

    public Description(Description description) {
        this.name = description.getName();
        this.shortDescription = description.getShortDescription();
        this.longDescription = description.getLongDescription();
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
