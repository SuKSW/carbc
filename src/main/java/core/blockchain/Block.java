package core.blockchain;

public class Block {

    private BlockHeader header;
    private Transaction[] transactions;

    public Block(BlockHeader header, Transaction[] transactions) {
        this.header = header;
        this.transactions = transactions;
    }

    public Block() {}
    //To do
    public Block createBlock(){
        return this;
    }

    public static Block createGenesis(){

         Block genesisBlock = new Block(); // should change

        return genesisBlock;
    }

    private byte[] getTransactionsEncoded(){
        return  null;
    }

}
