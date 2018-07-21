package core.blockchain;

import chainUtil.ChainUtil;

import java.security.*;
import java.util.ArrayList;

public class Transaction {
    private String sender;
    private ArrayList<Validation> validations;
    private byte[] data;
    private String transactionID;
    private TransactionInfo transactionInfo; //sell, insure, repair, register & etc


    // timestamp should assign current time


    public Transaction(String sender, ArrayList<Validation> validations, byte[] data, String transactionID, TransactionInfo transactionInfo) {
        this.sender = sender;
        this.validations = validations;
        this.data = data;
        this.transactionID = transactionID;
        this.transactionInfo = transactionInfo;
    }


    public String getSender() {
        return sender;
    }

    public ArrayList<Validation> getValidations() {
        return validations;
    }

    public byte[] getData() {
        return data;
    }

    public String getTransactionID() {
        return transactionID;
    }


    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }


    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setValidations(ArrayList<Validation> validations) {
        this.validations = validations;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }


    private String validationArrayToString(ArrayList<Validation> validations1){
        String validationStr= "";
        for (Validation validation: validations1){
            validationStr = validationStr + validation.toString() + " ";
        }
        return validationStr;
    }


//    @Override
//    public String toString(){
//        return "'Sender:'" +this.sender +"'Validations'" + this.validationArrayToString(this.validations) + "'Data:'" +
//                ChainUtil.bytesToHex(data) + "'TransactionInfo:'" + this.transactionInfo.toString();
//    }

}
