package fhtw.cartridgeScaping.networking;

import fhtw.cartridgeScaping.controller.ViewManager;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.function.Consumer;

public class NetworkFactory {
    public static Server createServer(Consumer<Serializable> onReceiveCallback, int minPort) {
        ViewManager.getInstance().devLog(String.format("--- Creating server on port %d ---", minPort));
        return new Server(minPort, onReceiveCallback);
    }
    public static Client createClient(Consumer<Serializable> onReceiveCallback, InetAddress ip, int minPort) {
        ViewManager.getInstance().devLog(
                String.format("--- Creating Client on port %d, connecting to %s ---", minPort, ip.toString()));
        return new Client(ip, minPort, onReceiveCallback);
    }
}
