package core.blockchain;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

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


}
