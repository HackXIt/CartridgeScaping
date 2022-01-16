package fhtw.cartridgeScaping.gameplay.text;

public class DoorDescription extends Description{
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
