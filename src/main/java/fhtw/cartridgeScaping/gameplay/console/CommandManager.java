package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.networking.NetworkManager;
import fhtw.cartridgeScaping.util.View;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final HashMap<String, Runnable> possibleBasicInteractions = new HashMap<>();
    private final HashMap<String, Consumer<Item>> possibleAdvancedInteractions = new HashMap<>();
    private final HashMap<String, Consumer<Room>> possibleDynamicInteractions = new HashMap<>();

    public CommandManager() {
    }

    private void printHelp() {
        commands.forEach((k,v) -> {
            ViewManager.getInstance().devLog(
                    v.getCommandText().getHelp(
                            ViewManager.getInstance().getApplicationSettings().isVerbose()));
            ViewManager.getInstance().getCurrentOutputArea().appendText(
                    v.getCommandText().getHelp(ViewManager.getInstance().getApplicationSettings().isVerbose())
            );
        });
    }

    private void exit() {
        // TODO Add yes/no prompt to exit command
        NetworkManager.getInstance().connection().closeConnection();
        ViewManager.getInstance().switchTo(View.MAIN);
    }

//    NOTE getter for possibleInteractions - might be useful in the future
//    public HashMap<String, Runnable> getPossibleBasicInteractions() {
//        return possibleBasicInteractions;
//    }
//    public HashMap<String, Consumer<Item>> getPossibleAdvancedInteractions() {
//        return possibleAdvancedInteractions;
//    }
//    public HashMap<String, Consumer<Room>> getPossibleDynamicInteractions() {
//        return possibleDynamicInteractions;
//    }

    public void setVerbose(boolean verbose) {
        ViewManager.getInstance().getApplicationSettings().setVerbose(verbose);
    }

    public void init() {
        possibleBasicInteractions.put("help", this::printHelp);
        possibleBasicInteractions.put("exit", this::exit);
        possibleBasicInteractions.put("look", Player.getInstance()::look);
        possibleAdvancedInteractions.put("take", Player.getInstance()::pickupItem);
        possibleAdvancedInteractions.put("drop", Player.getInstance()::dropItem);
        possibleDynamicInteractions.put("look <object>", Player.getInstance()::lookObject);

        // NOTE This will add commands which are always available (like exit or help)
        /* NOTE Loop explanation:
         Iterates over all possible generic interactions which consist of String & Action Pairs
         The 'key' is a String representing the first word of the command
         The 'value' is a Runnable method-reference to be called by execute()
         */
        for(Map.Entry<String, Runnable> entry : possibleBasicInteractions.entrySet()) {
            commands.put(entry.getKey(), new Command(entry.getValue(),
                    CommandText.createCommandText(
                            entry.getKey()
            )));
        }
        // NOTE This will add commands related to generic movement (go <direction>)
        /* NOTE Static version with one 'go' command per direction
        String[] directions = Direction.getPossibleDirections(); // Possible directions in the game
        for (String direction: directions) {
            String cmd = "go " + direction;
            commands.put(cmd, new AdvancedCommand<>(
                    player::walk,
                    Direction.getDirection(direction),
                    CommandText.createCommandText(cmd)));
        }
         */
        // NOTE Dynamic version with only one go command for all possible directions
        commands.put("go", new DynamicCommand<>(
                Player.getInstance()::walk,
                CommandText.createCommandText("go")
        ));
        // NOTE This will add commands related to interactions with items
        for (Map.Entry<String, Consumer<Item>> entry : possibleAdvancedInteractions.entrySet()) {
            String cmd = entry.getKey() + " <item>";
            commands.put(cmd, new DynamicCommand<>(
                    entry.getValue(),
                    CommandText.createCommandText(cmd)));
        }
        // NOTE This will add commands r elated to interactions with rooms
        for (Map.Entry<String, Consumer<Room>> entry : possibleDynamicInteractions.entrySet()) {
            String cmd = entry.getKey() + " <room>";
            commands.put(cmd, new DynamicCommand<>(
                    entry.getValue(),
                    CommandText.createCommandText(cmd)));
        }
    }

    public Command parseInput(String input) {
        return commands.getOrDefault(input, null);
    }
}
