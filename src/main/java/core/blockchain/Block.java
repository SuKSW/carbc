package core.blockchain;

import chainUtil.ChainUtil;

import java.security.PublicKey;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

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
    public Block(String version, byte[] previousHash, byte[] hash, Timestamp timestamp, String signer, long blockNumber, boolean isAprrroved, Transaction transaction){
        BlockHeader blockHeader = new BlockHeader(version,previousHash,hash,timestamp,signer,blockNumber,false);
        new Block(blockHeader,transaction);
    }


    public Block(){

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





    public static Block createGenesis(){

      //  BlockHeader blockHeader = new BlockHeader("1",ChainUtil.hexStringToByteArray("Genesis Bolock"),ChainUtil.hexStringToByteArray("Genesis Block"),);
        return null;
    }

    public void broadcast(){

    }
    public boolean sendBlockToValidator(Validator validator){
        String publicKey = validator.getValidator();
        //send block to validator
        return false;
    }

}
