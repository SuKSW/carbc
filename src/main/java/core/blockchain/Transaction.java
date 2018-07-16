package core.blockchain;

import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
    private PublicKey sender;
    private Validation[] validations;
    private byte[] data;
    private String transactionID;
    private String timestamp;
    private TransactionInfo transactionInfo; //sell, insure, repair, register & etc


    // timestamp should assign current time


    public Transaction(PublicKey sender, Validation[] validations, byte[] data, String transactionID, String timestamp, TransactionInfo transactionInfo) {
        this.sender = sender;
        this.validations = validations;
        this.data = data;
        this.transactionID = transactionID;
        this.timestamp = timestamp;
        this.transactionInfo = transactionInfo;
    }


    public PublicKey getSender() {
        return sender;
    }

    public Validation[] getValidations() {
        return validations;
    }

    public byte[] getData() {
        return data;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }


    public void setSender(PublicKey sender) {
        this.sender = sender;
    }

    public void setValidations(Validation[] validations) {
        this.validations = validations;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }



    //To do
    public Transaction createTransaction(String proposalID){
        int mandatoryCount = 0;
        int signCount = 0;
        int mandatorySignCount = 0;
        ArrayList<Validation> validations = new ArrayList<Validation>();

        ArrayList responses = TempResponsePool.getResponsePool().get(proposalID);
        for (Object response:responses){
            TransactionResponse respons = (TransactionResponse)response;
            Validation validation = new Validation(respons.getValidator(),respons.getSignature());




        }

//        Validator[] validators =proposal.getvalidators();
//
//        for (int i = 0; i<validators.length;i++){
//            if(validators[i].isMandotory()){
//                mandatoryCount++;
//
//            }
//            else {


//                signCount++;
//            }
//        }

        return this;
    }


    public boolean isValid(Transaction transaction){

        return false;
    }

}
