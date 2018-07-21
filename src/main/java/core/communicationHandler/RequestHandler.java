package core.communicationHandler;

import core.blockchain.Block;
import core.consensus.Consensus;

import java.util.Map;

public class RequestHandler {

    private static RequestHandler requestHandler;

    private RequestHandler() {}

    public static RequestHandler getInstance() {
        if(requestHandler == null) {
            requestHandler = new RequestHandler();
        }
        return requestHandler;
    }

    public void handleRequest(Map headers, String data) {
        System.out.println("********requestHandler*******");
        String messageType = (String)headers.get("messageType");
        System.out.println(messageType);
        switch (messageType) {
            case "TransactionProposalRequest":
                handleTransactionProposalRequest(data);
                break;

            case "TransactionProposalResponse":
                handleTransactionProposalResponse(data);
                break;

            case "AgreementRequest":
                handleAgreementRequest(data);
                break;

            case "AgreementResponse":
                handleAgreementResponse(data);
                break;

            case "BlockBroadcast":
                handleBroadcastBlock(data);
                break;

        }
    }

    public void handleTransactionProposalRequest(String data) {
        System.out.println("handleTransactionProposalRequest");
    }

    public void handleTransactionProposalResponse(String data) {
        System.out.println("handleTransactionProposalResponse");
    }

    public void handleAgreementRequest(String data) {

        System.out.println("handleAgreementRequest");

    }

    public void handleAgreementResponse(String data) {
        System.out.println("handleAgreementResponse");
        System.out.println("############");
        System.out.println("data is "+data);
    }

    public void handleBroadcastBlock(String data) {
        System.out.println("handleBroadcastBlock");

    }

    public Block JSONStringToBlock(String JSONblock) {
        return new Block();
    }


}
