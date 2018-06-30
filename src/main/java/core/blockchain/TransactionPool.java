package core.blockchain;

import java.util.Stack;

public class TransactionPool {

    private Stack transactionPool;


    //To do

    public void addToPool(Transaction transaction){
        this.transactionPool.push(transaction);
        return;

    }

    public void sendPool(){
        //create socket connection to earby peers and send transactionPool
    }
}
