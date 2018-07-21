package core.blockchain;

import java.security.PublicKey;
import java.sql.Timestamp;

public class BlockHeader {
    private String version;
    private byte[] previoushash;
    private byte[] hash;
    private Timestamp timestamp;
    //private int txCount;
    private String signer;
    private long blockNumber;
    private boolean isApproved;


    //constructor


    public BlockHeader(String version, byte[] previoushash,byte[] hash, Timestamp timestamp, String signer, long blockNumber, boolean isApproved) {
        this.version = version;
        this.previoushash = previoushash;
        this.hash = hash;
        this.timestamp = timestamp;
        this.signer = signer;
        this.blockNumber = blockNumber;
        this.isApproved = false;
    }


    // getters

    public String getVersion() {
        return version;
    }

    public byte[] getPrevioushash() {
        return previoushash;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getSigner() {
        return signer;
    }

    public long getBlockNumber() {
        return blockNumber;
    }

    public byte[] getHash() {
        return hash;
    }

    public boolean isApproved() {
        return isApproved;
    }

    //setters


    public void setVersion(String version) {
        this.version = version;
    }

    public void setPrevioushash(byte[] previoushash) {
        this.previoushash = previoushash;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public void setBlockNumber(long blockNumber) {
        this.blockNumber = blockNumber;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
