package core.blockchain;

import java.security.PublicKey;

public class TransactionProposal {
    private PublicKey sender;
    private Validator[] validators;
    private byte[] data;
    private String proposalID;
    private String timestamp;
    private TransactionInfo transactionInfo;
    private Validation validation;


    public TransactionProposal(PublicKey sender, Validator[] validators, byte[] data, String proposalID, String timestamp, TransactionInfo transactionInfo, Validation validation) {
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

    public String getTimestamp() {
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

    public void setTimestamp(String timestamp) {
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

    public TransactionResponse signProposal(TransactionProposal proposal){
        if (isValid(proposal)) {
            byte[] signature = ChainUtil.sign();//signature of the proposal
            proposal.getValidators();
            //Validator validator = new Validator(ChainUtil.getPublicKey(),);
            //TransactionResponse response = new TransactionResponse(proposal.getProposalID(), , signature);
            //return response;
        }
       return null;
    }


    public boolean isValid(TransactionProposal proposal){

        return false;
    }


}
