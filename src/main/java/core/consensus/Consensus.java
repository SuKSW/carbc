package core.consensus;

import core.blockchain.Block;
import core.blockchain.Blockchain;
import core.blockchain.Transaction;
import java.security.PublicKey;
import java.security.Timestamp;
import java.util.ArrayList;

public class Consensus {

    ArrayList<AgreementCollector> agreementCollectors;
    ArrayList<Transaction> agreedTransactiions;

    public Consensus() {
        agreementCollectors = new ArrayList<>();
        agreedTransactiions = new ArrayList<>();
    }

    public boolean agreedTransaction(Transaction transaction) {
        if(!agreedTransactiions.contains(transaction)) {
            agreedTransactiions.add(transaction);
            return true;
        }else {
            return false;
        }
    }

    public boolean requestAgreementForBlock(Block block) {
        //broadcast block to every peer requesting agreement
        return true;
    }

    public boolean sendAgreementForBlock(Block block, boolean agreed, PublicKey receiver) {
        return true;
    }

    public boolean checkAgreement(Block block) {
//        if(agreedTransactiions.contains(block.getTransaction())){
//            return true;
//        }
        return false;
    }

    public boolean addAgreedNode(Block block, PublicKey agreedNode) {
       return getAgreementCollectorByBlock(block).addAgreedNode(agreedNode);
    }

    public AgreementCollector getAgreementCollectorByBlock(Block block) {
        //logic to get AgreementCollector by a Block

        return new AgreementCollector(block);
    }

    public boolean RollBack(long blockNumber) {
        //remove all the blocks from given blocknumber
        return true;
    }

//    public boolean insertBlock(Block block) {
//        long receivedBlockNumber = block.getBlockNumber();
//        Timestamp receivedBlockTimestamp = block.getTimeStamp();
//
//        if(!blockExistence(block)) {
//            Blockchain.addBlock(block);
//            return true;
//        }else {
//            Block existBlock = Blockchain.getBlockbyBlockNumber(receivedBlockNumber);
//
//            if(existBlock.getTimeStamp > receivedBlockTimestamp) {
//                Blockchain.rollBack(receivedBlockNumber);
//                Blockchain.addBlock(block);
//                return true;
//            }else if(existBlock.getTimeStamp == receivedBlockTimestamp) {
//                Blockchain.rollBack(receivedBlockNumber);
//                return false;
//            }
//            return false;
//        }
//    }

    public boolean blockExistence(Block block) {
//        long blockNumber = block.getNumber();
        //if blockchain length is longer than blockNumber
        //return true, otherwise false
        return true;
    }



}
