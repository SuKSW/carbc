
package core.consensus;

import chainUtil.ChainUtil;
import chainUtil.KeyGenerator;
import core.blockchain.Block;
import core.blockchain.Blockchain;
import core.blockchain.Transaction;
import core.blockchain.Validation;
import core.communicationHandler.MessageSender;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Consensus {

    private static Consensus consensus;
    ArrayList<AgreementCollector> agreementCollectors;
    ArrayList<Transaction> agreedTransactiions;
    ArrayList<Block> agreementRequestBlocks;

    private Consensus() {
        agreedTransactiions = new ArrayList<>();
        agreementCollectors = new ArrayList<>();
        agreementRequestBlocks = new ArrayList<>();
    }

    public static Consensus getInstance() {
        if(consensus == null) {
            consensus = new Consensus();
        }
        return consensus;
    }


    public boolean requestAgreementForBlock(Block block) throws NoSuchAlgorithmException {
        addToAgreementCollectors(block);
        System.out.println("Agreement collector added");
        ArrayList<String> validators = new ArrayList<>();
        ArrayList<Validation> validations = block.getTransaction().getValidations();
        for(Validation validation: validations) {
            validators.add(validation.getValidator().getValidator());
            MessageSender.getInstance().requestAgreement(block,1);
        }

        //send the block to validators in the validators array for agreements
        return true;
    }

    public boolean responseForBlockAgreement(Block block, String agreed, int neighbourIndex) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException, IOException, SignatureException, InvalidKeyException {
        if(checkAgreementForBlock(block)) {
            sendAgreementForBlock(block,agreed,neighbourIndex);
            return true;
        }
        return false;
    }

    public boolean sendAgreementForBlock(Block block, String agreed, int neighbourIndex) throws
            InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException, IOException, SignatureException, InvalidKeyException {
        MessageSender.getInstance().sendAgreement(block,1,agreed,
                ChainUtil.sign(KeyGenerator.getInstance().getPrivateKey(),agreed));
        //remove from agreementRequestBlocks array
        return true;
    }

    public boolean checkAgreementForBlock (Block block) {
        if(agreedTransactiions.contains(block.getTransaction())){ //changed
            return true;
        }
        return false;
    }

    public boolean agreedTransaction(Transaction transaction) {
        if (!agreedTransactiions.contains(transaction)) {
            agreedTransactiions.add(transaction);
            return true;
        } else {
            return false;
        }
    }

    public void addRequestAgreementBlock(Block block) {
        agreementRequestBlocks.add(block);
        System.out.println("added to agreementRequestBlocks array");
    }

    public boolean handleAgreementResponse(Block block, String agreedNodePublicKey, String signatureString, String data) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException, SignatureException, InvalidKeyException {
        PublicKey agreedNode = KeyGenerator.getInstance().getPublicKey(agreedNodePublicKey);
        System.out.println(AgreementCollector.generateAgreementCollectorId(block));
        byte[] signature = ChainUtil.hexStringToByteArray(signatureString);
        boolean verfied = ChainUtil.verify(agreedNode,signature,data);
        if(verfied) {
            System.out.println("response verified");
            boolean status = addAgreedNodeForBlock(block,agreedNodePublicKey);
            if(status) {
                System.out.println("agreedNOde added successfully");
                return true;
            }
        }
        return false;
    }

    public boolean addAgreedNodeForBlock(Block block, String agreedNodePublicKey) throws NoSuchAlgorithmException {
        if(getAgreementCollectorByBlock(block) != null) {
            boolean status = getAgreementCollectorByBlock(block).addAgreedNode(agreedNodePublicKey);
            if(status) {
                checkForEligibilty(block);
                System.out.println("agreement added successfully ");
                return true;
            }else{
                System.out.println("agreement not added");
                return false;
            }
        }else{
            System.out.println("no Agreement collector found");
            return false;
        }

    }

    public AgreementCollector getAgreementCollectorByBlock(Block block) throws NoSuchAlgorithmException {
        long id = AgreementCollector.generateAgreementCollectorId(block);
        for(int i = 0; i< agreementCollectors.size(); i++) {
            if(agreementCollectors.get(i).getId() == id) {
                return agreementCollectors.get(i);
            }
        }
        return null;
    }

    public boolean insertBlock(Block block) {
        long receivedBlockNumber = block.getHeader().getBlockNumber();
        Timestamp receivedBlockTimestamp = block.getHeader().getTimestamp();

        if (!blockExistence(block)) {
            Blockchain.getBlockchain().addBlock(block);
            return true;
        } else {
            Block existBlock = Blockchain.getBlockchain().getBlockByNumber(receivedBlockNumber);

            if (existBlock.getHeader().getTimestamp().after(receivedBlockTimestamp)) {
                Blockchain.getBlockchain().rollBack(receivedBlockNumber);
                Blockchain.getBlockchain().addBlock(block);
                return true;
            } else if (existBlock.getHeader().getTimestamp() == receivedBlockTimestamp) {
                Blockchain.getBlockchain().rollBack(receivedBlockNumber);
                return false;
            }
            return false;
        }
    }

    public boolean blockExistence(Block block) {
        if(Blockchain.getBlockchain().getBlockchainArray().size() > block.getHeader().getBlockNumber()) {
            return true;
        }
        return false;
    }

    public void BlockHandler(Block block) throws NoSuchAlgorithmException {
        if(agreedTransaction(block.getTransaction())) {
            System.out.println("Agreed block");
            insertBlock(block);
        }else {
            System.out.println("Not agreed block. requesting agreements");
            requestAgreementForBlock(block);
        }
    }

    public void checkForEligibilty(Block block) throws NoSuchAlgorithmException {
        int threshold = 1; //get from the predefined rules

        if(getAgreementCollectorByBlock(block).getAgreedNodesCount() == threshold) {
            System.out.println("Agreements received upto threshold level");
            insertBlock(block);
        }
    }

    public boolean addToAgreementCollectors(Block block) throws NoSuchAlgorithmException {
        AgreementCollector agreementCollector = new AgreementCollector(block);
        System.out.println(agreementCollector.getId());
        return  agreementCollectors.add(agreementCollector);
    }

}
