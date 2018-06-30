package core.blockchain;

public class TransactionProposal {
    private String senderAddress;
    private Validator[] validators;
    private String data;
    private int signCount;
    private boolean isValid;

    // should set sign count to zero

    public TransactionProposal(String senderAddress, Validator[] validators, String data, int signCount, boolean isValid) {
        this.senderAddress = senderAddress;
        this.validators = validators;
        this.data = data;
        this.signCount = signCount;
        this.isValid = false;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public Validator[] getvalidators() {
        return validators;
    }

    public String getData() {
        return data;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public void setvalidators(Validator[] validators) {
        this.validators = validators;
    }

    public void setData(String data) {
        this.data = data;
    }

    //To do
    public TransactionProposal createTransactionProposal(String senderAddress, Validator[] validators, String data, int signCount){
        TransactionProposal proposal = new TransactionProposal(senderAddress,validators,data,signCount,false);
        return this;
    }

    
    public void sendTransactionProposal(TransactionProposal proposal){
        for (Validator validator: proposal.validators){
            String validatorAddress = validator.getAddress();
            // create socket connection and send proposal
        }
    }

    public void signTransactionProposal(TransactionProposal proposal){

    }

    public void sendBack(TransactionProposal proposal){
        if (proposal.isValid) {

            String senderAddress = proposal.senderAddress;
            // create socket connection and send back
        }
    }

}
