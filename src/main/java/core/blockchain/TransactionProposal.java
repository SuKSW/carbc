package core.blockchain;

import chainUtil.ChainUtil;
import chainUtil.KeyGenerator;
import com.google.gson.Gson;
import core.communicationHandler.MessageSender;
import core.consensus.Consensus;
import org.json.JSONObject;

import java.io.IOException;
import java.security.*;
import java.sql.Timestamp;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TransactionProposal {
    private String sender;
    private ArrayList<Validator> validators;
    private String data;
    private String datas;
    //private String pID;
    private String proposalID;
    private String timestamp;
    private TransactionInfo transactionInfo;
    private Validation validation;

    //to store send proposals
    private static HashMap<String,TransactionProposal> proposals;


    public TransactionProposal(String datas, String sender){
        this.proposalID = datas;
        this.sender = sender;
    }
    public TransactionProposal(String sender, ArrayList<Validator> validators, String data, String proposalID,
                               String timestamp, TransactionInfo transactionInfo) {
        this.sender = sender;
        this.validators = validators;
        this.data = data;
        this.proposalID = proposalID;
        this.setTimestamp(timestamp);
        this.transactionInfo = transactionInfo;

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


    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
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
//        JSONObject jsonProposal = new JSONObject(proposal);
//        return (jsonProposal.toString());
        Gson gson = new Gson();
        return gson.toJson(proposal);
    }

//    public TransactionProposal createTransactionProposal(){
//        //save proposal in proposals hashmap
//        return this;
//    }

    
    public boolean sendProposal(){
        //save proposal in proposals hashmap
        proposals = TransactionProposal.getProposals();
        proposals.put(this.getProposalID(),this);
        for (Validator validator: this.validators){
            String validatorPublicKey = validator.getValidator();
            // create socket connection and send proposal and return true
            MessageSender.getInstance().reqestTransactionValidation(this,1); //change neighbour
        }
        return false;
    }

    public TransactionResponse signProposal() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException, SignatureException, InvalidKeyException {
        System.out.println("signature: ");
        System.out.println(TransactionProposal.getProposalString(this));

            ArrayList<Validator> validators = this.getValidators();
            for (Validator validator1:validators){
                System.out.println("validator1" + validator1.getValidator());
                System.out.println("my public key" + KeyGenerator.getInstance().getEncodedPublicKeyString(KeyGenerator.getInstance().getPublicKey()));
                if (validator1.getValidator().equals(KeyGenerator.getInstance().getEncodedPublicKeyString(KeyGenerator.getInstance().getPublicKey())) ){
                    byte[] signature = ChainUtil.sign(KeyGenerator.getInstance().getPrivateKey(), TransactionProposal.getProposalString(this));//signature of the proposal
                    System.out.println("signature" + ChainUtil.bytesToHex(signature));
                    //testing
                    String sigstring = ChainUtil.bytesToHex(signature);
                    boolean status = ChainUtil.verify(KeyGenerator.getInstance().getPublicKey(),
                            ChainUtil.hexStringToByteArray(sigstring),TransactionProposal.getProposalString(this));
                    System.out.println("**************************");
                    System.out.println("status: "+ status);
                    System.out.println("sending data: "+TransactionProposal.getProposalString(this));
                    Validator  validator = validator1;
                    TransactionResponse response = new TransactionResponse(this.getProposalID(), validator,ChainUtil.bytesToHex(signature));
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

        Transaction transaction = new Transaction(this.getSender(),validations, this.getProposalID(),this.getTransactionInfo());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timestampString = String.valueOf(timestamp);
        byte[] hash = ChainUtil.getHash(transaction.toString());
        BlockHeader blockHeader = new BlockHeader("1",Blockchain.getBlockchain().getBlockchainArray().getLast().getHeader().getHash(),timestampString,this.sender,Blockchain.getBlockchain().getBlockchainArray().size()+1,true);

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
        System.out.println("is this valid? yes/No ");
        System.out.println(TransactionProposal.getProposalString(this));
        String isValid = scanner.next();
        if (isValid.equalsIgnoreCase("yes")){
            String sender = this.getSender();
            TransactionResponse response =  this.signProposal();
            if (response!=null){
                //connection and send
                //sendResponse();
                Consensus.getInstance().agreedTransaction(this.proposalID);
                MessageSender.getInstance().sendTransactionValidation(response,1);  //should send transaction response not proposal
                System.out.println(response);
                System.out.println("sending response");
            }
        }
        else if (isValid.equalsIgnoreCase("no")){
            String error = "not agreed with: " + this.getProposalID();
            //connection and send
        }else {
            System.out.println("please enter yes or no");
            isValid = scanner.next();
        }
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getProposalID() {
        return proposalID;
    }

    public void setProposalID(String proposalID) {
        this.proposalID = proposalID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
