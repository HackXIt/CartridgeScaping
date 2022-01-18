package fhtw.cartridgeScaping.model;

import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.messages.MessageHandler;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * INFO Header of RoomModel.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/model
 * @project CartridgeScaping
 */
public class WaitingRoomModel extends Model {
    private final ArrayList<Player> players = new ArrayList<>();
    private final HashMap<Player, InetAddress> playerIps = new HashMap<>();
    private final HashMap<Player, Boolean> playerStates = new HashMap<>();
    private final MessageHandler messageHandler = new MessageHandler();

    public WaitingRoomModel() {
    }

    public MessageHandler MessageHandler() {
        return messageHandler;
    }

    public HashMap<Player, InetAddress> getPlayerIps() {
        return playerIps;
    }
    public void addPlayer(Player player, InetAddress address) {
        players.add(player);
        playerIps.put(player, address);
        playerStates.put(player, false);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean allReady() {
        return !playerStates.containsValue(false);
    }
}
