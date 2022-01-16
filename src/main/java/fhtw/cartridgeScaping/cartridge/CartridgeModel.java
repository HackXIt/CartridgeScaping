package fhtw.cartridgeScaping.cartridge;

import fhtw.cartridgeScaping.util.IOResult;

import java.net.InetAddress;
import java.nio.file.Path;

public class CartridgeModel {
    private Cartridge cartridge;
    private int port;
    private InetAddress host;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getHost() {
        return host;
    }

    public void setHost(InetAddress host) {
        this.host = host;
    }

    public IOResult<Cartridge> load(Path file) {
        return (IOResult<Cartridge>) IOResult.failure("Not implemented", new UnsupportedOperationException());
    }
}
