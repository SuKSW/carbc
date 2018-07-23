package core.blockchain;

import chainUtil.ChainUtil;

import java.security.PublicKey;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class Block {

    private BlockHeader header;
    private Transaction transactions;

    private String genesisMsg;


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
    public Block(String version, String previousHash, Timestamp timestamp, String signer, long blockNumber, boolean isAprrroved, Transaction transaction){
        BlockHeader blockHeader = new BlockHeader(version,previousHash,timestamp,signer,blockNumber,false);
        new Block(blockHeader,transaction);
    }


    public Block(BlockHeader genesisHeader){
        this.header =genesisHeader;
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
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        BlockHeader blockHeader = new BlockHeader("1","genesis Block",currentTimestamp,"developer",
                (long)1,true);
        Block genesisBlock = new Block(blockHeader);
        return genesisBlock;
    }

    public void broadcast(){

    }
    public boolean sendBlockToValidator(Validator validator){
        String publicKey = validator.getValidator();
        //send block to validator
        return false;
    }

}
