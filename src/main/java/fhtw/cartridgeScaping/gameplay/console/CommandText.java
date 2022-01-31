package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.Player;
import javafx.util.Pair;

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
        Pair<String, String> splitCommand = Player.getInstance().getCommandManager().splitCommand(command);
        return new CommandText(splitCommand.getKey(), splitCommand.getValue(), !splitCommand.getValue().isEmpty());
    }
}
