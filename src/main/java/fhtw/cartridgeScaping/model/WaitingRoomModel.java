package fhtw.cartridgeScaping.model;

import fhtw.cartridgeScaping.gameplay.Player;

import java.net.Inet4Address;
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
public class WaitingRoomModel extends Model{
    private final ArrayList<Player> players = new ArrayList<>();
    private final HashMap<Player, Inet4Address> playerIps = new HashMap<>();
    private final HashMap<Player, Boolean> playerStates = new HashMap<>();
    private Player host;
    private Inet4Address hostIp;
    private boolean allReady;

    public HashMap<Player, Inet4Address> getPlayerIps() {
        return playerIps;
    }
    public void addPlayer(Player player, Inet4Address address) {
        players.add(player);
        playerIps.put(player, address);
        playerStates.put(player, false);
    }

    public Player getHost() {
        return host;
    }

    public void setHost(Player host, Inet4Address hostIp) {
        this.host = host;
        this.hostIp = hostIp;
        players.add(host);
        playerIps.put(host, hostIp);
    }

    public Inet4Address getHostIp() {
        return hostIp;
    }

    public boolean allReady() {
        return allReady;
    }

    public void setAllReady(boolean allReady) {
        this.allReady = allReady;
    }

    public void setPlayerState(Player player, boolean state) {
        if(players.contains(player)) {
            playerStates.replace(player, state);
        }
    }
}
