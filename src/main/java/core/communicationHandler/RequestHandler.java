package core.communicationHandler;

import chainUtil.ChainUtil;
import chainUtil.KeyGenerator;
import core.blockchain.*;
import core.consensus.Consensus;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    public void handleRequest(Map headers, String data) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException {
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

    public void handleAgreementRequest(String data) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        Block requestAgreementBlock = JSONStringToBlock(data);
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

    public Block JSONStringToBlock(String JSONblock) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException {
        byte[] prevhash = ChainUtil.hexStringToByteArray("1234");
        byte[] hash = ChainUtil.hexStringToByteArray("5678");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        byte[] data = ChainUtil.hexStringToByteArray("1456");
        byte[] signatue1 = ChainUtil.hexStringToByteArray("3332");
        byte[] signatue2 = ChainUtil.hexStringToByteArray("3442");
        PublicKey publicKey = KeyGenerator.getInstance().getPublicKey();
        Validator validator1 = new Validator(publicKey,"owner",true,3);
        Validator validator2 = new Validator(publicKey,"seller",true,4);
        ArrayList<Validation> validations = new ArrayList<>();
        validations.add(new Validation(validator1,signatue1));
        validations.add(new Validation(validator2,signatue2));
        BlockHeader blockHeader = new BlockHeader("101",prevhash,hash,timestamp,
                KeyGenerator.getInstance().getPublicKey(),123,true);
        Transaction transaction = new Transaction(publicKey,validations,data,"tran1",new TransactionInfo());

        Block block = new Block(blockHeader,transaction);
        return block;
    }


}
