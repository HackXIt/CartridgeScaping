package fhtw.cartridgeScaping.gameplay;

import fhtw.cartridgeScaping.cartridge.CartridgeModel;
import fhtw.cartridgeScaping.controller.ViewManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class GameManager {
    private static GameManager singleton_instance;
    private final ArrayList<Player> players = new ArrayList<>();
    private final HashMap<Player, Boolean> playerStates = new HashMap<>();
    private CartridgeModel model;

    private GameManager() {}

    public static GameManager getInstance() {
        if(singleton_instance == null) {
            singleton_instance = new GameManager();
        }
        return singleton_instance;
    }

    public void handleLoadError(Consumer<String> consumer) {
        consumer.accept("The loaded cartridge is invalid.");
    }

    public void handleLoadSuccess(Consumer<String> consumer) {
        consumer.accept("The cartridge was loaded successfully.");
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public HashMap<Player, Boolean> getPlayerStates() {
        return playerStates;
    }

    public boolean allReady() {
        if(ViewManager.getInstance().developerMode()) {
            playerStates.forEach((player, state) -> {
                System.out.printf("%s -> %s", player.getName(), state.toString());
            });
        }
        return !playerStates.containsValue(false);
    }

    public void setPlayerState(Player player, boolean state) {
        if(players.contains(player)) {
            playerStates.replace(player, state);
        }
    }

    public void startGame() {
        // TODO Implement start game
    }

    public void admitCartridge(CartridgeModel model) {
        this.model = model;
    }
}
