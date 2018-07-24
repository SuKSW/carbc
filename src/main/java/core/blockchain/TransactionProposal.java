package core.blockchain;

import chainUtil.ChainUtil;
import chainUtil.KeyGenerator;
import core.communicationHandler.MessageSender;
import org.json.JSONObject;

import java.io.IOException;
import java.security.*;
import java.sql.Timestamp;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TransactionProposal {
    private String sender;
    private ArrayList<Validator> validators;
    private String data;
    private String pID;
    private Timestamp timestamp;
    private TransactionInfo transactionInfo;
    private Validation validation;

    //to store send proposals
    private static HashMap<String,TransactionProposal> proposals;


    public TransactionProposal(String sender, ArrayList<Validator> validators, String data, String pID,
                               Timestamp timestamp, TransactionInfo transactionInfo) {
        this.sender = sender;
        this.validators = validators;
        this.data = data;
        this.pID = pID;
        this.timestamp = timestamp;
        this.transactionInfo = transactionInfo;
        //this.validation = validation;
    }


    public String getSender() {
        return sender;
    }

    public ArrayList<Validator> getValidators() {
        return validators;
    }

    public String getData() {
        return data;
    }

    public String getpID() {
        return pID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setValidators(ArrayList<Validator> validators) {
        this.validators = validators;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }


    //To do


    public static HashMap<String, TransactionProposal> getProposals() {
        if (proposals == null){
            proposals = new HashMap<>();
        }
        return proposals;
    }

    public static void setProposals(HashMap<String, TransactionProposal> proposals) {
        TransactionProposal.proposals = proposals;
    }


    public static String getProposalString(TransactionProposal proposal) throws NoSuchAlgorithmException {
        JSONObject jsonProposal = new JSONObject(proposal);
        return (jsonProposal.toString());
    }

//    public TransactionProposal createTransactionProposal(){
//        //save proposal in proposals hashmap
//        return this;
//    }

    
    public boolean sendProposal(){
        //save proposal in proposals hashmap
        proposals = TransactionProposal.getProposals();
        proposals.put(this.pID,this);
        for (Validator validator: this.validators){
            String validatorPublicKey = validator.getValidator();
            // create socket connection and send proposal and return true
            MessageSender.getInstance().reqestTransactionValidation(this,1); //change neighbour
        }
        return false;
    }

    public TransactionResponse signProposal() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException, SignatureException, InvalidKeyException {

        System.out.println(TransactionProposal.getProposalString(this));
            byte[] signature = ChainUtil.sign(KeyGenerator.getInstance().getPrivateKey(), TransactionProposal.getProposalString(this));//signature of the proposal
        System.out.println("signature" + ChainUtil.bytesToHex(signature));

            ArrayList<Validator> validators = this.getValidators();
            for (Validator validator1:validators){
                System.out.println("validator1" + validator1.getValidator());
                System.out.println("my public key" + KeyGenerator.getInstance().getEncodedPublicKeyString(KeyGenerator.getInstance().getPublicKey()));
                if (validator1.getValidator().equals(KeyGenerator.getInstance().getEncodedPublicKeyString(KeyGenerator.getInstance().getPublicKey())) ){
                    Validator  validator = validator1;
                    TransactionResponse response = new TransactionResponse(this.pID, validator,ChainUtil.bytesToHex(signature));
                    System.out.println(response); //print responseye
                    return response;
                }else{
                    System.out.println("Transaction response -> sign proposal -> not correct validator");
                }
            }
       return null;
    }


    public Block createBlock(String proposalID) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {

        ArrayList<Validation> validations = new ArrayList<Validation>();

        ArrayList responses = TempResponsePool.getResponsePool().get(proposalID);
        TransactionProposal proposal = TransactionProposal.getProposals().get(proposalID);
        String proposalString = proposal.toString();
        for (Object resp:responses){
            TransactionResponse response = (TransactionResponse)resp;

                Validation validation = new Validation(response.getValidator(),response.getSignature());
                validations.add(validation);

        }

        Transaction transaction = new Transaction(this.getSender(),validations,this.pID,this.getTransactionInfo());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        byte[] hash = ChainUtil.getHash(transaction.toString());
        BlockHeader blockHeader = new BlockHeader("1",Blockchain.getBlockchain().getBlockchainArray().getLast().getHeader().getHash(),timestamp,this.sender,Blockchain.getBlockchain().getBlockchainArray().size()+1,true);

        Block block = new Block(blockHeader,transaction);
        //convert to string

        String blockHash = ChainUtil.getBlockHashString(block);

        // set hash to blockheader
        block.getHeader().setHash(blockHash);

        return block;
    }



    public void isValid() throws NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.toString());
        System.out.println("is this valid? ");
        String isValid = scanner.next();
        if (isValid.equalsIgnoreCase("yes")){
            String sender = this.getSender();
            TransactionResponse response =  this.signProposal();
            if (response!=null){
                //connection and send
                //sendResponse();
                MessageSender.getInstance().sendTransactionValidation(response,1);  //should send transaction response not proposal
                System.out.println(response);
                System.out.println("sending response");
            }
        }
        else if (isValid.equalsIgnoreCase("no")){
            String error = "not agreed with (proposal id)";
            //connection and send
        }else {
            System.out.println("please enter yes or no");
        }
    }
}
