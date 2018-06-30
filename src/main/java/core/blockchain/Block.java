package core.blockchain;

public class Block {

    private BlockHeader header;
    private Transaction[] transactions;

    public Block(BlockHeader header, Transaction[] transactions) {
        this.header = header;
        this.transactions = transactions;
    }

    //To do
    public Block mineBlock(){
        return this;
    }

    public Block createGenesis(){
        Block genesisBlock = new Block(header,transactions); // should change
        return this;
    }

    private byte[] getTransactionsEncoded(){
        return  null;
    }

}
