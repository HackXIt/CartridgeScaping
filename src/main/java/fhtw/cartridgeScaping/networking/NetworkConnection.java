package fhtw.cartridgeScaping.networking;

/* NOTE When dealing with complex things like IO, external, database, ...
... useful to have abstraction layer for which to communicate with the rest of the program
 */

import fhtw.cartridgeScaping.controller.ViewManager;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {
    /* NOTE Explanation of attributes for NetworkConnection
    TODO Explanation of attributes for NetworkConnection
     */
    private final Consumer<Serializable> onReceiveCallback;
    private final ConnectionThread connectionThread = new ConnectionThread();

    public NetworkConnection(Consumer<Serializable> onReceiveCallback) {
        this.onReceiveCallback = onReceiveCallback;
        connectionThread.setDaemon(true); // TODO Explain reason/usage/purpose of setDaemon(true)
    }


    /* NOTE Not ideal to have client & server in same class
    There should be different implementations and/or classes for server & client.
    Primarily because of maintainability, but also to be able to do different things in each of them.
    */

    /* Attributes for both client & server */
    public abstract boolean isServer();
    public abstract String getIp();
    public abstract int getPort();

    //region Networking
    public boolean startConnection() {
        try {
            connectionThread.start();
            return true;
        } catch (Exception e) {
            ViewManager.getInstance().errorLog("Failed to start connection.", e);
            return false;
        }

    }

    public boolean send(Serializable data) {
        try {
            connectionThread.out.writeObject(data);
            return true;
        } catch (Exception e) {
            ViewManager.getInstance().errorLog("Failed to send message.", e);
            return false;
        }
    }

    public boolean closeConnection() {
        try {
            connectionThread.socket.close();
            return true;
        } catch (Exception e) {
            ViewManager.getInstance().errorLog("Failed to close connection.", e);
            return false;
        }
    }
    //endregion

    /* NOTE Threads are essential for networking / parallel execution
    Writing & Reading from socket/stream cannot happen sequentially
    It has to happen simultaneous for the program to be able to write AND read.

    Otherwise the application will always wait for methods to finish
     */
    private class ConnectionThread extends Thread {
        // Create new Thread by extending class Thread (old fashioned)
        /* This is where we'll do network stuff */

        private Socket socket;
        private ObjectOutputStream out;
        @Override
        public void run() {
            try (
                    // NOTE When using 0 as Port number, the next free port is automatically chosen.
                    ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                    Socket socket = isServer() ? server.accept() : new Socket(getIp(), getPort());
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                        this.socket = socket;
                        this.out = out;
                        socket.setTcpNoDelay(true);

                        while (true) {
                            Serializable data = (Serializable) in.readObject();
                            onReceiveCallback.accept(data);
                        }
            } catch (Exception e) {
                ViewManager.getInstance().errorLog("ConnectionThread - Error during execution of run().", e);
                onReceiveCallback.accept("Connection closed.\n");
            }
        }
    }
}
