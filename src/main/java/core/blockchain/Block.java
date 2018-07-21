package core.blockchain;

import java.security.PublicKey;
import java.sql.Timestamp;

public class Block {

    private BlockHeader header;
    private Transaction transactions;

    public Block(BlockHeader header, Transaction transactions) {
        this.header = header;
        this.transactions = transactions;
    }

//       private String version;
//    private byte[] previoushash;
//    private byte[] hash;
//    private Timestamp timestamp;
//    //private int txCount;
//    private PublicKey signer;
//    private long blockNumber;
//    private boolean isApproved;
    public Block(String version, byte[] previousHash, byte[] hash, Timestamp timestamp, PublicKey signer, long blockNumber, boolean isAprrroved, Transaction transaction){
        BlockHeader blockHeader = new BlockHeader(version,previousHash,hash,timestamp,signer,blockNumber,false);
        new Block(blockHeader,transaction);
    }




    public BlockHeader getHeader() {
        return header;
    }

    public Transaction getTransaction() {
        return transactions;
    }


    public void setHeader(BlockHeader header) {
        this.header = header;
    }

    public void setTransaction(Transaction transactions) {
        this.transactions = transactions;
    }


    //To do
    public Block createBlock(){
        return this;
    }

    public static Block createGenesis(){

        return null;
    }

    public void broadcast(){

    }
    public boolean sendBlockToValidator(Validator validator){
        PublicKey publicKey = validator.getValidator();
        //send block to validator
        return false;
    }

}
