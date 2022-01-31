package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.GameManager;
import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import fhtw.cartridgeScaping.networking.NetworkManager;
import fhtw.cartridgeScaping.util.View;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandManager {
    private final HashMap<String, CMD> commands = new HashMap<>();
    private final HashMap<String, Runnable> possibleBasicInteractions = new HashMap<>();
//    private final HashMap<String, Consumer<Item>> possibleAdvancedInteractions = new HashMap<>();
    private final HashMap<String, Consumer<GameObject>> possibleDynamicInteractions = new HashMap<>();

    public CommandManager() {
    }

    private void printHelp() {
        // k = String of command
        // v = Command object
        commands.forEach((k,v) -> {
            ViewManager.getInstance().devLog(
                    v.getCommandText().getHelp());
            ViewManager.getInstance().getCurrentOutputArea().appendText(v.getCommandText().getHelp());
        });
    }

    private void exit() {
        // TODO Add yes/no prompt to exit command
        NetworkManager.getInstance().connection().closeConnection();
        ViewManager.getInstance().switchTo(View.Main);
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
        possibleDynamicInteractions.put("take", Player.getInstance()::pickupObject);
        possibleDynamicInteractions.put("drop", Player.getInstance()::dropObject);
        possibleDynamicInteractions.put("look", Player.getInstance()::lookObject);

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
        /*
        for (Map.Entry<String, Consumer<Item>> entry : possibleAdvancedInteractions.entrySet()) {
            String cmd = entry.getKey();
            commands.put(cmd, new DynamicCommand<>(
                    entry.getValue(),
                    CommandText.createCommandText(cmd + " <item>")));
        }
         */
        // NOTE This will add commands related to interactions with GameObjects
        for (Map.Entry<String, Consumer<GameObject>> entry : possibleDynamicInteractions.entrySet()) {
            String cmd = entry.getKey();
            commands.put(cmd, new DynamicCommand<>(
                    entry.getValue(),
                    CommandText.createCommandText(cmd + " <object>")));
        }
    }

    public Pair<String, String> splitCommand(String command) {
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
        return new Pair<>(name, arg);
    }

    public CMD parseInput(String input) {
        Pair<String, String> splitInput = splitCommand(input);
        CMD cmd = commands.getOrDefault(input, null);
        // FIXME Find better way to implement commands with arguments which are evaluated during runtime
        // NOTE This is a workaround, because I couldn't get dynamicCommand working properly
        if(possibleDynamicInteractions.containsKey(splitInput.getKey())) {
            int objId = GameManager.getInstance().getGame().getObjects().get(splitInput.getValue());
            switch (splitInput.getKey()) {
                case "go" -> {
                    Direction dir =  Direction.getDirection(splitInput.getValue());
                    AdvancedCommand<Direction> advancedCommand = new AdvancedCommand<>(
                            Player.getInstance()::walk, dir, null);
                    return advancedCommand;
                }
                case "pickup" -> {
                    GameObject object = Player.getInstance().getCurrentRoom().getItems().get(objId);
                    AdvancedCommand<GameObject> advancedCommand = new AdvancedCommand<>(
                            Player.getInstance()::pickupObject, object, null);
                    return advancedCommand;
                }
                case "drop" -> {
                    GameObject object = Player.getInstance().getInventory().get(objId);
                    AdvancedCommand<GameObject> advancedCommand = new AdvancedCommand<>(
                            Player.getInstance()::dropObject, object, null);
                    return advancedCommand;
                }
                case "look" -> {
                    GameObject object;
                    if(objId != Player.getInstance().getCurrentRoom().hashCode()) {
                        object = Player.getInstance().getCurrentRoom().getItems().get(objId);
                    } else {
                        object = Player.getInstance().getCurrentRoom();
                    }
                    AdvancedCommand<GameObject> advancedCommand = new AdvancedCommand<>(
                            Player.getInstance()::lookObject, object, null);
                    return advancedCommand;
                }
            }
        }
        return cmd;
    }
}
