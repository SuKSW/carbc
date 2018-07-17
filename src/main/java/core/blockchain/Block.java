package core.blockchain;

public class Block {

    private BlockHeader header;
    private Transaction transactions;

    public Block(BlockHeader header, Transaction transactions) {
        this.header = header;
        this.transactions = transactions;
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

    public Block() {} //remove
    //To do
    public Block createBlock(){
        return this;
    }

    public static Block createGenesis(){

      //  BlockHeader genesisHeader = new BlockHeader("1",null,null,null,"",0,"",1,true);

      //  Block genesisBlock = new Block(genesisHeader,new Transaction[0]); // should change

      //  return genesisBlock;

        return null;
    }

    private byte[] getTransactionsEncoded(){
        return  null;
    }

    public void broadcast(){

    }
    public boolean sendBlockToValidator(Validator validator){

        return false;
    }

}
