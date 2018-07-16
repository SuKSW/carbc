package core.blockchain;

import java.security.PublicKey;

public class BlockHeader {
    private String version;
    private byte[] previoushash;
    private byte[] hash;
    private String timestamp;
    private int txCount;
    private PublicKey signer;
    private long blockNumber;
    private boolean isApproved;


    //constructor


    public BlockHeader(String version, byte[] previoushash,byte[] hash, String timestamp, int txCount, PublicKey signer, long blockNumber, boolean isApproved) {
        this.version = version;
        this.previoushash = previoushash;
        this.hash = hash;
        this.timestamp = timestamp;
        this.txCount = txCount;
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

    public String getTimestamp() {
        return timestamp;
    }

    public int getTxCount() {
        return txCount;
    }

    public PublicKey getSigner() {
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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTxCount(int txCount) {
        this.txCount = txCount;
    }

    public void setSigner(PublicKey signer) {
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
