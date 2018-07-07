package core.smartContract;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;


public class TransactionDummy {

    private PublicKey senderAddress;
    private String method;

    public byte[] getSenderAddress() {
        byte[] key = this.senderAddress.getEncoded();
        return key;
    }

    public TransactionDummy(byte[] pubkey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.senderAddress =
                KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubkey));
    }

    public TransactionDummy(){

    }

    public void executeSmartContractMethod() throws IOException, InterruptedException {


        ProcessBuilder build = new ProcessBuilder("java","-cp", "/home/sajinie/Desktop/vehiclesmartcontract.jar", "core.SmartContract.Main");
        File log = new File("log");
        build.redirectErrorStream(true);
        build.redirectOutput(ProcessBuilder.Redirect.appendTo(log));

        Process process = build.start();

        assert build.redirectInput() == ProcessBuilder.Redirect.PIPE;
        assert build.redirectOutput().file() == log;
        assert process.getInputStream().read() == -1;

        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s = "";
        while((s = in.readLine()) != null){
            System.out.println(s);
        }
        int status = process.waitFor();
        System.out.println("Exited status: " + status);

    }

    public void deploySmartContract(String sc){

    }
}
