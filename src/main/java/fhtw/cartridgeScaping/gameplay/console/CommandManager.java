package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.model.GameplayModel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandManager {
    private final Player player;
    private boolean verbose = false;
    private final HashMap<String, Command> commands;
    private final HashMap<String, Runnable> possibleBasicInteractions = new HashMap<>();
    private final HashMap<String, Consumer<Item>> possibleAdvancedInteractions = new HashMap<>();
    private final HashMap<String, Consumer<Room>> possibleDynamicInteractions = new HashMap<>();

    public CommandManager(Player player) {
        this.player = player;
        this.commands = this.init();
        possibleBasicInteractions.put("help", this::printHelp);
        possibleBasicInteractions.put("exit", this::exit);
        possibleBasicInteractions.put("look", player::look);
        possibleAdvancedInteractions.put("take", player::pickupItem);
        possibleAdvancedInteractions.put("drop", player::dropItem);
        possibleDynamicInteractions.put("look <object>", player::lookObject);
    }

    private void printHelp() {
        commands.forEach((k,v) -> {
            if(ViewManager.isDeveloperMode()) {
                System.out.println(v.getCommandText().getHelp(verbose));
            } else {
                // TODO Send printHelp() to game client textArea
            }
        });
    }

    private void exit() {
//        Platform.exit();
//        TODO exit() code in CommandManager - Refactor for returning to main menu instead of quitting
    }

    public HashMap<String, Runnable> getPossibleBasicInteractions() {
        return possibleBasicInteractions;
    }
    public HashMap<String, Consumer<Item>> getPossibleAdvancedInteractions() {
        return possibleAdvancedInteractions;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    private HashMap<String, Command> init() {
        HashMap<String, Command> commands = new HashMap<>();
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
                player::walk,
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
        return commands;
    }
}
