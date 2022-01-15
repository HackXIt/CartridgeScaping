package fhtw.cartridgeScaping.gameplay;

import java.util.HashMap;

public class Player {
    private String playerName;
    private HashMap<Integer, Item> inventory;

    public Player(String playerName) {
        this.playerName = playerName;
        this.inventory = new HashMap<>();
    }

    public Player(String playerName, HashMap<Integer, Item> inventory) {
        this.playerName = playerName;
        this.inventory = inventory;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.put(item.getId(), item);
    }
}
