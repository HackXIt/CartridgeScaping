package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.gameplay.util.Direction;

import java.util.HashMap;

public class HelpManager {
    private static final HashMap<String, HelpText> helpTexts = new HashMap<>();
    static {
        helpTexts.put("help", new HelpText(
                "help",
                "Prints the contents of this help message.",
                "Print help message."));
        helpTexts.put("exit", new HelpText(
                "exit",
                "Type & enter to exit the game.",
                "Exit the game"));
        helpTexts.put("take <item>", new HelpText("take notepad",
                        "Take item from the current room.",
                        "Take the <item>",
                "This command will take the given item from the current that you are in."));
        helpTexts.put("drop <item>", new HelpText("drop notepad",
                "Drop item in the current room.",
                "Drop the <item>",
                "This command will drop the given item in the current that you are in."));
        // DONE Improve direction helpText (Currently creating helpText per Direction, which isn't very readable)
        helpTexts.put(Direction.getPossibleDirectionsAsSyntax(), new HelpText(
                "go west",
                "Type 'go <direction>' to go in specified direction.",
                "Go in direction",
                "This command allows you to walk or move in the specified direction." +
                        "The syntax shows all usable directions in the game, but your room might not have all of those."
        ));
    }

    public static HelpText getHelpText(String command) {
        return helpTexts.get(command);
    }
}
