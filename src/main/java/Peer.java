import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Peer {
    private final Logger log = LoggerFactory.getLogger(Peer.class);

    public void init() {
        int index = 1;
        log.info("Peer:{} initialized", index);
    }


}
