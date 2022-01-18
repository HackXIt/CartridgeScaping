package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.controller.ViewManager;

public record CommandText(String name, String argument, boolean hasArgument) {

    public String getHelp(boolean verbose) {
        HelpText helpText = HelpManager.getHelpText(name);
        if (helpText == null) {
            return "No helpText available.\n";
        }
        StringBuilder strBuilder = new StringBuilder(name);
        strBuilder.append("\t-|-\t");
        strBuilder.append(helpText.shortDescription());
        if (verbose) {
            strBuilder.append(helpText.longDescription());
            strBuilder.append("\nDetails:\n");
            strBuilder.append(helpText.actionExplanation());
            strBuilder.append("\nUsage:\n");
            strBuilder.append(helpText.usage());
        }
        ViewManager.getInstance().devLog(String.format("%s", strBuilder));
        return strBuilder.toString();
    }

    static CommandText createCommandText(String command) {
        // TODO Implement CommandText with multiple arguments
        String argument = "";
        String name;
        if (command.contains(" ")) {
            String[] tmp = command.split(" ", 2); // Separates first word from rest of string
            name = tmp[0]; // Sets name to first word
            if (tmp[1].contains(" ")) { // Check if rest of argument is a single word
                argument = tmp[1].split(" ")[0]; // Accept only first argument, ignoring the rest
            } else {
                argument = tmp[1];
            }
        } else {
            name = command;
        }
        return new CommandText(name, argument, !argument.isEmpty());
    }
}
