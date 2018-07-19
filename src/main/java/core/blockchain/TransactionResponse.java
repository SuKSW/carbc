package core.blockchain;

import chainUtil.ChainUtil;

import java.security.*;
import java.util.ArrayList;
import java.util.HashMap;

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



    public void addResponse() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {

        String proposalID = this.getProposalID();
        TransactionProposal proposal = TransactionProposal.getProposals().get(proposalID);
        String proposalString = proposal.toString();
        if (proposalID != null & ChainUtil.verify(this.getValidator().getValidator(),this.getSignature(),proposalString)){ //

            HashMap<String, ArrayList<TransactionResponse>> responsePool = TempResponsePool.getResponsePool();

            if (responsePool.get(proposalID) != null && !responsePool.get(proposalID).isEmpty()) {
                ArrayList<TransactionResponse> responseArray = responsePool.get(proposalID);
                if (!responseArray.contains(this)) {
                    responseArray.add(this);
                    responsePool.replace(proposalID, responseArray);
                    if (responseArray.size() > proposal.getTransactionInfo().getThreshod()){
                        int mandatorySignCount = 0;
                        int mandatorySignCountInProposal = 0;

                        for (TransactionResponse response: responseArray){
                            if(response.getValidator().isMandotory()){
                                mandatorySignCount++;
                            }
                        }

                        for (Validator validator:proposal.getValidators()){
                            if (validator.isMandotory()){
                                mandatorySignCountInProposal++;
                            }
                        }

                        if (mandatorySignCount==mandatorySignCountInProposal){
                            proposal.createBlock(proposal.getProposalID());
                        }
                    }
                }
                return;
            } else {
                ArrayList<TransactionResponse> responseArray = new ArrayList<TransactionResponse>();
                responseArray.add(this);
                responsePool.put(proposalID, responseArray);
                TempResponsePool.setResponsePool(responsePool);
                return;
            }
        }
        else {
            System.out.println("invalid");
            return;
        }
    }

    @Override
    public String toString(){
        return "'ProposalID:'" + this.ProposalID +"'Validator:'" + this.validator + "'Signatute'" + ChainUtil.bytesToHex(this.signature);
    }

}
