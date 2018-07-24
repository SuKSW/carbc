package core.communicationHandler;

import chainUtil.ChainUtil;
<<<<<<< HEAD
import core.blockchain.*;
=======
import chainUtil.KeyGenerator;
import core.blockchain.Block;
import core.blockchain.BlockHeader;
import core.blockchain.Transaction;
import core.blockchain.TransactionProposal;
>>>>>>> new changes
import network.Client.RequestMessage;
import network.Node;
import network.Protocol.BlockMessageCreator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public class MessageSender {

    private static  MessageSender messageSender;

    private MessageSender() {};

    public static MessageSender getInstance() {
        if(messageSender == null) {
            messageSender = new MessageSender();
        }
        return messageSender;
    }

    public void BroadCastBlock(Block block) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("block",blockToJSON(block).toString());
        RequestMessage blockMessage = BlockMessageCreator.createBlockMessage(jsonObject);
        blockMessage.addHeader("keepActive", "false");
        blockMessage.addHeader("messageType", "BlockBroadcast");
        for(int i = 1; i< 3; i++) {
            Node.getInstance().sendMessageToNeighbour(i, blockMessage);
        }
    }

    public void requestAgreement(Block block, int neighbourIndex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("block",blockToJSON(block).toString());
        RequestMessage blockMessage = BlockMessageCreator.createBlockMessage(jsonObject);
        blockMessage.addHeader("keepActive", "false");
        blockMessage.addHeader("messageType", "AgreementRequest");
        Node.getInstance().sendMessageToNeighbour(neighbourIndex, blockMessage);
    }

    public void sendAgreement(Block block, int neighbourIndex, String agreement, byte[] signature) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("block",blockToJSON(block).toString());
        jsonObject.put("agreement",agreement);
        jsonObject.put("signature",ChainUtil.bytesToHex(signature));
        jsonObject.put("publickey",KeyGenerator.getInstance().getPublicKeyAsString());
        RequestMessage blockMessage = BlockMessageCreator.createBlockMessage(jsonObject);
        blockMessage.addHeader("keepActive", "false");
        blockMessage.addHeader("messageType", "AgreementResponse");
        Node.getInstance().sendMessageToNeighbour(neighbourIndex, blockMessage);
    }

    public void reqestTransactionValidation(TransactionProposal transactionProposal, int neighbourIndex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transactionProposal",new JSONObject(transactionProposal).toString());
        RequestMessage blockMessage = BlockMessageCreator.createBlockMessage(jsonObject);
        blockMessage.addHeader("keepActive", "false");
        blockMessage.addHeader("messageType", "TransactionProposal");
        Node.getInstance().sendMessageToNeighbour(neighbourIndex, blockMessage);
    }

    public void sendTransactionValidation(TransactionResponse transactionResponse, int neighbourIndex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transactionResponse",new JSONObject(transactionResponse).toString());
        //jsonObject.put("signature",ChainUtil.bytesToHex(signature));
        RequestMessage blockMessage = BlockMessageCreator.createBlockMessage(jsonObject);
        blockMessage.addHeader("keepActive", "false");
        blockMessage.addHeader("messageType", "TransactionValidation");
        Node.getInstance().sendMessageToNeighbour(neighbourIndex, blockMessage);
    }

    public JSONObject blockToJSON(Block block) {
        return new JSONObject(block);
    }

    public JSONObject transactionToJSON(Transaction transaction) {
        return new JSONObject(transaction);

    }
}
