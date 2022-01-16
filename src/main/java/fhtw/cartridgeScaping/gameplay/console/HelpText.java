package fhtw.cartridgeScaping.gameplay.console;

public record HelpText(String usage, String actionExplanation, String shortDescription, String longDescription) {

    public HelpText(String usage, String actionExplanation) {
        this(usage, actionExplanation, null, null);
    }

    public HelpText(String usage, String actionExplanation, String shortDescription) {
        this(usage, actionExplanation, shortDescription, null);
    }
}
