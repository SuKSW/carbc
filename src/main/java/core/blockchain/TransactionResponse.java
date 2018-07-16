package core.blockchain;

import java.security.PublicKey;

public class TransactionResponse {
    private String ProposalID;
    private Validator validator;
    private byte[] signature;


    public TransactionResponse(String proposalID, Validator validator, byte[] signature) {
        ProposalID = proposalID;
        this.validator = validator;
        this.signature = signature;
    }


    public String getProposalID() {
        return ProposalID;
    }

    public Validator getValidator() {
        return validator;
    }

    public byte[] getSignature() {
        return signature;
    }


    public void setProposalID(String proposalID) {
        ProposalID = proposalID;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }


    public boolean sendResponse(TransactionProposal proposal){
        PublicKey sender = proposal.getSender();
        TransactionResponse response =  proposal.signProposal(proposal);
        if (response!=null){
            //create connection and send response to sender
            return  true;
        }
        return  false;
    }
}
