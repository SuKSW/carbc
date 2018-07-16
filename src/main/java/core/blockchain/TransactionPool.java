package core.blockchain;

import java.util.ArrayList;
import java.util.Stack;

public class TransactionPool {

    private static ArrayList<Transaction> transactionPool;

    public TransactionPool() {
        transactionPool = new ArrayList<Transaction>();
    }

    public static ArrayList<Transaction> getTransactionPool() {
        return transactionPool;
    }

    //To do

    public void addToPool(Transaction transaction){
        if(transaction.isValid(transaction)) {
            this.transactionPool.add(transaction);
            return;
        }else {
            return;
        }
    }

    public void sendPool(){
        //create socket connection to earby peers and send transactionPool
    }
}
