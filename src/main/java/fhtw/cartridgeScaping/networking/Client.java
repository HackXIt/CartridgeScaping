package fhtw.cartridgeScaping.networking;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.function.Consumer;

public class Client extends NetworkConnection{
    private final InetAddress ip;
    private final int port;

    public Client(InetAddress ip, int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getLinkLocalAddress() {
        return ip.getHostAddress();
    }

    @Override
    protected int getPort() {
        return port;
    }
    // TODO Implement Client
}
