package chainUtil;

import core.blockchain.Block;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ChainUtil {

    private KeyGenerator keyGenerator;

    public ChainUtil() {
        keyGenerator = new KeyGenerator();
    }


    public static byte[] sign(PrivateKey privateKey,String data) throws InvalidKeyException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException {
        //sign the data
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(privateKey);
        byte[] byteArray = data.getBytes();
        dsa.update(byteArray);
        return dsa.sign();
    }

    public static boolean verify(PublicKey publicKey, byte[] signature, String data) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(publicKey);
        sig.update(data.getBytes(),0,data.getBytes().length);
        return sig.verify(signature);
    }

//    public publicKeyEncryption() {
//
//    }

    public static byte[] getHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String getBlockHash(Block block) {
        //get details in the blockheader
        //get details in the block
        //concat both get hash and return
        return "block";
    }

}