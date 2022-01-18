package fhtw.cartridgeScaping.networking;

import fhtw.cartridgeScaping.controller.ViewManager;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.function.Consumer;

public class Client extends NetworkConnection{
    private boolean isServer = false;
    private final InetAddress ip;
    private final int port;

    public Client(InetAddress ip, int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    public boolean isServer() {
        return isServer;
    }

    @Override
    public String getLinkLocalAddress() {
        ViewManager.getInstance().devLog(String.format("Requested IP: %s", ip.getHostAddress()));
        return ip.getHostAddress();
    }

    @Override
    public int getPort() {
        ViewManager.getInstance().devLog(String.format("Requested Port: %s", port));
        return port;
    }
    // TODO Implement Client
}
