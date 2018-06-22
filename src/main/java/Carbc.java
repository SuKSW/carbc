import network.Peer;
import org.slf4j.impl.SimpleLogger;

public class Carbc {
    public static void main(String[] args) {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");
        Peer peer = Peer.getInstance();


        peer.init(peerConfig);
    }
}
