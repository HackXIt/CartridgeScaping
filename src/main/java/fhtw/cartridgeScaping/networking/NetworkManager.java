package fhtw.cartridgeScaping.networking;

import fhtw.cartridgeScaping.gameplay.Player;

import java.net.InetAddress;

public class NetworkManager {
    private static Player self;
    private static InetAddress selfIp;
    private static int selfPort;
    private static boolean selfReady;
    private static NetworkConnection selfConnection;

    public static Player getSelf() {
        return self;
    }
    public static void setSelf(Player self) {
        NetworkManager.self = self;
    }

    public static InetAddress getSelfIp() {
        return selfIp;
    }

    public static void setSelfIp(InetAddress selfIp) {
        NetworkManager.selfIp = selfIp;
    }

    public static int getSelfPort() {
        return selfPort;
    }

    public static void setSelfPort(int selfPort) {
        NetworkManager.selfPort = selfPort;
    }

    public static boolean isSelfReady() {
        return selfReady;
    }

    public static void setSelfReady(boolean selfReady) {
        NetworkManager.selfReady = selfReady;
    }

    public static NetworkConnection getSelfConnection() {
        return selfConnection;
    }

    public static void setSelfConnection(NetworkConnection selfConnection) {
        NetworkManager.selfConnection = selfConnection;
    }

    public static void callback() {
        System.out.println("Callback triggered!");
    }
}
