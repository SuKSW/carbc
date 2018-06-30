package core.blockchain;

public class BlockHeader {
    private String version;
    private byte[] previoushash;
    private byte[] merkleRoot;
    private long timestamp;
    private int txCount;

    //constructor


    public BlockHeader(String version, byte[] previoushash, byte[] merkleRoot, long timestamp, int txCount) {
        this.version = version;
        this.previoushash = previoushash;
        this.merkleRoot = merkleRoot;
        this.timestamp = timestamp;
        this.txCount = txCount;
    }

    // getters

    public String getVersion() {
        return version;
    }

    public byte[] getPrevioushash() {
        return previoushash;
    }

    public byte[] getMerkleRoot() {
        return merkleRoot;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getTxCount() {
        return txCount;
    }

    //setters


    public void setVersion(String version) {
        this.version = version;
    }

    public void setPrevioushash(byte[] previoushash) {
        this.previoushash = previoushash;
    }

    public void setMerkleRoot(byte[] merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTxCount(int txCount) {
        this.txCount = txCount;
    }
}
