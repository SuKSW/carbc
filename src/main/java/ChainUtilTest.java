import chainUtil.ChainUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    }
}
