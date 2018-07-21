import chainUtil.ChainUtil;
import chainUtil.KeyGenerator;
import core.blockchain.Blockchain;
import core.consensus.Consensus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

public class ChainUtilTest {
    public static void main(String[] args) {

        //getHashTest
        String testData = "message";
        try {
            System.out.println("expected output");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            System.out.println(ChainUtil.bytesToHex(digest.digest(testData.getBytes(StandardCharsets.UTF_8))));

            System.out.println("method output");
            System.out.println(ChainUtil.bytesToHex(ChainUtil.getHash(testData)));

        } catch (Exception e) {
            e.getMessage();
        }

        Consensus c = Consensus.getInstance();
        Consensus c2 = Consensus.getInstance();
        int hash = Objects.hashCode(c);
        int hash2 = Objects.hashCode(Blockchain.getBlockchain());
        System.out.println("*********hashvalue*********");
        System.out.println(hash);
        System.out.println(hash2);
        try {
            System.out.println("*********signature*********");
            byte[] signature = ChainUtil.sign(KeyGenerator.getInstance().getPrivateKey(),Integer.toString(hash));
            System.out.println(ChainUtil.bytesToHex(signature));

            System.out.println("*********verification*********");
            System.out.println(ChainUtil.verify(KeyGenerator.getInstance().getPublicKey(),signature,Integer.toString(hash)));
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
