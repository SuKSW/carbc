package core.blockchain;

public class Transaction {
    private String senderAddress;
    private String[] validators;
    private byte[] data;
    private long transactionID;
    private long timestamp;

    // timestamp shoul get current time

    public Transaction(String senderAddress, String[] validators, byte[] data , long transactionID, long timestamp) {
        this.senderAddress = senderAddress;
        this.validators = validators;
        this.data = data;
        this.transactionID = transactionID;
        //this.timestamp =
    }

    //getters


    public String getSenderAddress() {
        return senderAddress;
    }

    public String[] getValidators() {
        return validators;
    }

    public byte[] getData() {
        return data;
    }


    public long getTransactionID() {
        return transactionID;
    }
    //setters


    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public void setvalidators(String[] validators) {
        this.validators = validators;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    //To do
    public Transaction createTransaction(TransactionProposal proposal){

        return this;
    }


    public boolean isValid(Transaction transaction){

        return false;
    }

}
