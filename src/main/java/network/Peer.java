package network;

import Exceptions.FileUtilityException;
import config.PeerConfig;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileUtils;

import java.util.Random;

public final class Peer {
    private final Logger log = LoggerFactory.getLogger(Peer.class);
    private static final Peer instance = new Peer();

    private PeerConfig peerConfig;

    private Peer() {}

    public static Peer getInstance() {
        return instance;
    }

    public void init(int listenerPort, String resourceFilePath) {

        /* Set config and its parameters */
        Random random = new Random();
        long peerID = random.nextLong();
        this.peerConfig = new PeerConfig(peerID);

        try {
            JSONObject jsonPeerConfig = new JSONObject(FileUtils.readFileContentAsText(resourceFilePath));
        } catch (FileUtilityException e) {
            //throw
        }
        peerConfig.setListenerPort(listenerPort);

        log.info("Initializing Peer:{}", peerID);

        /* Initialize listener */
        Listener listener = new Listener(peerConfig);
        listener.init();
        log.info("Initialized listener");

        /* Initialize queryClient */

        log.info("Initialized queryClient");
    }

}
