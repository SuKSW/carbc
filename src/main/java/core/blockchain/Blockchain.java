package core.blockchain;

import java.util.LinkedList;

public class Blockchain {
    private static LinkedList<Block> blockchainArray;
    private static Blockchain blockchain;

    private Blockchain() {
        this.blockchainArray = new LinkedList<Block>();
        this.blockchainArray.add(Block.createGenesis());
    }

    public static Blockchain getBlockchain() {
        return blockchain;
    }

    public static LinkedList<Block> getBlockchainArray() {
        return blockchainArray;
    }

    public void addBlock(Block block){
        this.blockchainArray.add(block);
    }

    public static void rollBack(long blockNumber){
        LinkedList<Block> validBlockchain = new LinkedList<Block>();
        validBlockchain = (LinkedList<Block>) blockchainArray.subList(0, (int) blockNumber);
        blockchainArray = validBlockchain;
    }

    public Block getBlockByNumber(long blockNumber){
        Block block = blockchainArray.get((int) blockNumber);
        return block;
    }

}
