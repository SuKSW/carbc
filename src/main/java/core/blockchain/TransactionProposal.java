package core.blockchain;

import chainUtil.ChainUtil;
import chainUtil.KeyGenerator;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class TransactionProposal {
    private PublicKey sender;
    private Validator[] validators;
    private byte[] data;
    private String proposalID;
    private Timestamp timestamp;
    private TransactionInfo transactionInfo;
    private Validation validation;


    public TransactionProposal(PublicKey sender, Validator[] validators, byte[] data, String proposalID, Timestamp timestamp, TransactionInfo transactionInfo, Validation validation) {
        this.sender = sender;
        this.validators = validators;
        this.data = data;
        this.proposalID = proposalID;
        this.timestamp = timestamp;
        this.transactionInfo = transactionInfo;
        this.validation = validation;
    }


    public PublicKey getSender() {
        return sender;
    }

    public Validator[] getValidators() {
        return validators;
    }

    public byte[] getData() {
        return data;
    }

    public String getProposalID() {
        return proposalID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setProposalID(String proposalID) {
        this.proposalID = proposalID;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setSender(PublicKey sender) {
        this.sender = sender;
    }

    public void setValidators(Validator[] validators) {
        this.validators = validators;
    }

    public void setData(byte[] data) {
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
    public TransactionProposal createTransactionProposal(){

        return this;
    }

    
    public boolean sendProposal(TransactionProposal proposal){
        for (Validator validator: proposal.validators){
            PublicKey validatorPublicKey = validator.getValidator();
            // create socket connection and send proposal and return true
        }
        return false;
    }

    public TransactionResponse signProposal() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException, SignatureException, InvalidKeyException {
//        if (isValid(proposal)) {
            KeyGenerator keygen = new KeyGenerator();
            byte[] signature = ChainUtil.sign(keygen.getPrivateKey(), this.toString());//signature of the proposal
            this.getValidators();

            Validator[] validators = this.getValidators();
            for (Validator validator1:validators){
                if (validator1.getValidator()==keygen.getPublicKey()){
                    Validator  validator = validator1;
                    TransactionResponse response = new TransactionResponse(this.proposalID, validator,signature);
                    keygen = null;
                    return response;
                }
            }

       return null;
    }


    public boolean sendResponse() throws NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {
        PublicKey sender = this.getSender();
        TransactionResponse response =  this.signProposal();
        if (response!=null){
            //create connection and send response to sender
            return  true;
        }
        return  false;
    }


    public boolean isValid() throws NoSuchAlgorithmException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.toString());
        System.out.println("is this valid? ");
        String isValid = scanner.next();
        if (isValid.equalsIgnoreCase("yes")){
            sendResponse();
            System.out.println("sending response");
        }
        else if (isValid.equalsIgnoreCase("no")){
            
        }else {
            System.out.println("please enter yes or no");
        }
        return false;
    }


}
