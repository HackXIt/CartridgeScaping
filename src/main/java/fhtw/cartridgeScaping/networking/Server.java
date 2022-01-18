package fhtw.cartridgeScaping.networking;

import java.io.Serializable;
import java.net.Inet4Address;
import java.util.function.Consumer;

public class Server extends NetworkConnection {
    private boolean isServer = true;
    private int port;

    public Server(int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.port = port;
    }

    @Override
    public boolean isServer() {
        return isServer;
    }

    @Override
    public String getIp() {
        return null;
    }

    @Override
    public int getPort() {
        return port;
    }
    // TODO Implement Server
}
