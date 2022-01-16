package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import javafx.application.Platform;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandManager {
    private final Player player;
    private boolean verbose = false;
    private final HashMap<String, Command> commands;
    private final HashMap<String, Pair<Runnable, HelpText>> possibleGenericInteractions = new HashMap<>();

    public CommandManager(Player player) {
        this.player = player;
        this.commands = this.init();
        possibleGenericInteractions.put(
                "help", new Pair<>(this::printHelp, HelpManager.getHelpText("help")));
        possibleGenericInteractions.put(
                "exit", new Pair<>(this::exit, HelpManager.getHelpText("exit")));
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
        Platform.exit();
    }

    public HashMap<String, Pair<Runnable, HelpText>> getPossibleGenericInteractions() {
        return possibleGenericInteractions;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    private HashMap<String, Command> init() {
        HashMap<String, Command> commands = new HashMap<>();
        // NOTE This will add commands which are always available (like exit or help)
        HashMap<String, Pair<Runnable, HelpText>> genericInteractions = this.getPossibleGenericInteractions();
        for(Map.Entry<String, Pair<Runnable, HelpText>> entry : genericInteractions.entrySet()) {
            commands.put(entry.getKey(), new Command(entry.getValue().getKey(),
                    CommandText.createCommandText(
                            entry.getValue().getValue().usage(),
                            entry.getValue().getValue())
            ));
        }
        // NOTE This will add commands related to generic movement (go <direction>)
        String[] directions = Direction.getPossibleDirections(); // Possible directions in the game
        for (String direction: directions) {
            String cmd = "go " + direction;
            commands.put(cmd, new AdvancedCommand<>(
                    player::walk,
                    Direction.getDirection(direction),
                    CommandText.createCommandText(cmd, HelpManager.getHelpText(cmd))));
        }
        // NOTE This will add commands related to interactions with items
        HashMap<String, Pair<Consumer<Item>, HelpText>> itemInteractions = player.getPossibleItemInteractions();
        for (Map.Entry<String, Pair<Consumer<Item>, HelpText>> entry : itemInteractions.entrySet()) {
            String cmd = entry.getKey() + " <item>";
            commands.put(cmd, new DynamicCommand<>(
                    entry.getValue().getKey(),
                    CommandText.createCommandText(cmd, entry.getValue().getValue())));
        }
        return commands;
    }
}
