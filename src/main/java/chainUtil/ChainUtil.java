package chainUtil;

import java.lang.reflect.Array;
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

}