package core.blockchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class TempResponsePool {
    private static  HashMap<String,ArrayList<TransactionResponse>> responsePool;
    //private String proposalID;


    public TempResponsePool() {
        this.responsePool = new HashMap<String,ArrayList<TransactionResponse>>();
    }

    public void addResponse(TransactionResponse response){

        String proposalID = response.getProposalID();

        if (proposalID != null){ //
            if (responsePool.get(proposalID) != null && !responsePool.get(proposalID).isEmpty()){
                ArrayList<TransactionResponse> proposalArray = responsePool.get(proposalID);
                proposalArray.add(response);
                responsePool.replace(proposalID,proposalArray);
                return;
            }
            else {
                ArrayList<TransactionResponse> responseArray = new ArrayList<TransactionResponse>();
                responseArray.add(response);
                responsePool.put(proposalID, responseArray);
                return;
            }
        }
        else {
            return;
        }
    }


    public static HashMap<String, ArrayList<TransactionResponse>> getResponsePool() {
        return responsePool;
    }

    public ArrayList getResponses(String proposalID){
        ArrayList <TransactionResponse> signedProposals = responsePool.get(proposalID);
        return signedProposals;
    }
}
