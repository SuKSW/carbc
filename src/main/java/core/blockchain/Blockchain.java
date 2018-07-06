package core.blockchain;

import java.util.ArrayList;
import java.util.Stack;

public class Blockchain {
    private Stack<Block> blockchain;

    public Blockchain() {
        this.blockchain = new Stack<Block>();
        this.blockchain.add(Block.createGenesis());
    }

    public void addBlock(Block block){
        this.blockchain.push(block);
    }
}
