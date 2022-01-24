package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.controller.ViewManager;

public record CommandText(String name, String argument, boolean hasArgument) {

    // FIXME The help messages are not printing properly (Prints help & exit 3 times and the others not)
    public String getHelp() {
        HelpText helpText = HelpManager.getInstance().getHelpText(name);
        if (helpText == null) {
            return "No helpText available.\n";
        }
        StringBuilder strBuilder = new StringBuilder(name);
        strBuilder.append("\t\t--\t");
        strBuilder.append(helpText.shortDescription());
        strBuilder.append("\n");
        if (ViewManager.getInstance().getApplicationSettings().isVerbose()) {
            strBuilder.append("Usage:\n");
            strBuilder.append(helpText.usage());
            strBuilder.append("\nDetails:\n");
            strBuilder.append(helpText.longDescription());
            strBuilder.append("\n");
            strBuilder.append(helpText.actionExplanation());
            strBuilder.append("\n");
        }
        return strBuilder.toString();
    }

    static CommandText createCommandText(String command) {
        // TODO Implement CommandText with multiple arguments
        String arg = "";
        String name;
        if (command.contains(" ")) {
            String[] tmp = command.split(" ", 2); // Separates first word from rest of string
            name = tmp[0]; // Sets name to first word
            if (tmp[1].contains(" ")) { // Check if rest of argument is a single word
                arg = tmp[1].split(" ")[0]; // Accept only first argument, ignoring the rest
            } else {
                arg = tmp[1];
            }
        } else {
            name = command;
        }
        return new CommandText(name, arg, !arg.isEmpty());
    }
}
