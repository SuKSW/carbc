package core.consensus;

import core.blockchain.Block;
import core.blockchain.Blockchain;
import core.blockchain.Transaction;
import core.blockchain.Validation;

import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Consensus {

    ArrayList<AgreementCollector> agreementCollectors;
    ArrayList<Transaction> agreedTransactiions;

    public Consensus() {
        agreementCollectors = new ArrayList<>();
        agreedTransactiions = new ArrayList<>();
    }

    public boolean agreedTransaction(Transaction transaction) {
        if (!agreedTransactiions.contains(transaction)) {
            agreedTransactiions.add(transaction);
            return true;
        } else {
            return false;
        }
    }

    public boolean requestAgreementForBlock(Block block) {
        ArrayList<PublicKey> validators = new ArrayList<>();
       // Validation[] validations = block.getTransaction().getValidations(); //changed
        ArrayList<Validation> validations = block.getTransaction().getValidations();

        for(Validation validation: validations) {
            validators.add(validation.getValidator().getValidator());
        }

        //send the block to validators in the validators array for agreements
        return true;
    }

    public boolean sendAgreementForBlock(Block block, boolean agreed, PublicKey receiver) {
        return true;
    }

    public boolean checkAgreementForBlock (Block block) {
        if(agreedTransactiions.contains(block.getTransaction())){ //changed
            return true;
        }
        return false;
    }

    public boolean addAgreedNodeForBlock(Block block, PublicKey agreedNode) {
        //when agreed node add to the array we need to check whether k number of agreement received
        return getAgreementCollectorByBlock(block).addAgreedNode(agreedNode);
    }

    public AgreementCollector getAgreementCollectorByBlock(Block block) {
        String id = AgreementCollector.generateAgreementCollectorId(block);
        for(int i = 0; i< agreementCollectors.size(); i++) {
            if(agreementCollectors.get(i).getId() == id) {
                return agreementCollectors.get(i);
            }
        }
        return null;
    }

//    public boolean insertBlock(Block block) {
//        long receivedBlockNumber = block.getHeader().getBlockNumber();
//        Timestamp receivedBlockTimestamp = block.getHeader().getTimestamp();
//
//        if (!blockExistence(block)) {
//            Blockchain.addBlock(block);
//            return true;
//        } else {
//            Block existBlock = Blockchain.getBlockByNumber(receivedBlockNumber);
//
//            if (existBlock.getHeader().getTimestamp().after(receivedBlockTimestamp)) {
//                Blockchain.rollBack(receivedBlockNumber);
//                Blockchain.addBlock(block);
//                return true;
//            } else if (existBlock.getHeader().getTimestamp() == receivedBlockTimestamp) {
//                Blockchain.rollBack(receivedBlockNumber);
//                return false;
//            }
//            return false;
//        }
//    }

    public boolean blockExistence(Block block) {
        if(Blockchain.getBlockchainArray().size() > block.getHeader().getBlockNumber()) {
            return true;
        }
        return false;
    }


}
