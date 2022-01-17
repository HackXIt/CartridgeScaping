package fhtw.cartridgeScaping.gameplay.text;

public class DoorDescription extends Description{
    private String insideDescription;
    private String outsideDescription;
    private String detailedDescription;

    private boolean isInside;

    public DoorDescription(String name) {
        super(name);
    }

    public DoorDescription(String name, String shortDescription) {
        super(name, shortDescription);
    }

    public DoorDescription(Description description) {
        super(description);
    }

    public DoorDescription(Description description, String detailedDescription) {
        super(description);
        this.detailedDescription = detailedDescription;
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

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
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
