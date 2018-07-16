package core.consensus;

import chainUtil.ChainUtil;
import core.blockchain.Block;
import java.security.PublicKey;
import java.util.ArrayList;

public class AgreementCollector {


    String id;
    Block block;
    ArrayList<PublicKey> agreedNodes;

    public AgreementCollector(Block block) {
//        id = ChainUtil.bytesToHex(ChainUtil.getHash(block))
        getAgreedNodes();
        this.block = block;
        agreedNodes = new ArrayList<>();
    }

    public Block getBlock() {
        return block;
    }

    public boolean addAgreedNode(PublicKey agreedNode) {
        if(!agreedNodes.contains(agreedNode)){
            agreedNodes.add(agreedNode);
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<PublicKey> getAgreedNodes() {
        return agreedNodes;
    }

//    public byte[] getBlockHash() {
//
//    }

    public static String generateAgreementCollectorId(Block block) {
//        return ChainUtil.getBlockHash(block)+ block.getBlockNumber();
        return "hash1234";
    }
}
