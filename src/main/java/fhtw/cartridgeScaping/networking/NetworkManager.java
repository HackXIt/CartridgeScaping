package fhtw.cartridgeScaping.networking;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.messages.Message;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Formatter;
import java.util.function.Consumer;

public class NetworkManager {
    private static NetworkManager singleton_instance;
    private InetAddress targetIp;
    private int port;
    private NetworkConnection connection;
    private boolean isHost;
    private Consumer<Serializable> currentCallback;
    // TODO Implement usage of formatter in Messages
    private Formatter messageFormatter = new Formatter();

    private NetworkManager() { }

    public static NetworkManager getInstance() {
        if(singleton_instance == null) {
            singleton_instance = new NetworkManager();
        }
        return singleton_instance;
    }

    public InetAddress getTargetIp() {
        return targetIp;
    }

    public void setTargetIp(InetAddress targetIp) {
        this.targetIp = targetIp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public NetworkConnection connection() {
        return connection;
    }

    public void setConnection(NetworkConnection connection) {
        this.connection = connection;
        this.isHost = connection.isServer();
    }

    public boolean isHost() {
        return isHost;
    }

    public void setCallback(Consumer<Serializable> callback) {
        this.currentCallback = callback;
    }

    public void callback(Serializable data) {
        ViewManager.getInstance().devLog("Callback triggered!");
        ViewManager.getInstance().devLog(String.format("%s", data));
        currentCallback.accept(data);
        // TODO Also implement this in multithreading (tasks and whatnot)
    }

    public void connectGame() {
        // TODO Implement network implementation for starting game
    }
}
