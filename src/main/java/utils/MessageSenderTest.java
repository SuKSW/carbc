package utils;

import chainUtil.ChainUtil;
import chainUtil.KeyGenerator;
import core.blockchain.*;
import core.communicationHandler.MessageSender;
import org.json.JSONObject;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Timestamp;

public class MessageSenderTest {

    public static void main(String[] args) {

        try {
            byte[] prevhash = ChainUtil.hexStringToByteArray("1234");
            byte[] hash = ChainUtil.hexStringToByteArray("5678");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            byte[] data = ChainUtil.hexStringToByteArray("1456");
            byte[] signatue1 = ChainUtil.hexStringToByteArray("3332");
            byte[] signatue2 = ChainUtil.hexStringToByteArray("3442");
            PublicKey publicKey = KeyGenerator.getInstance().getPublicKey();
            Validator validator1 = new Validator(publicKey,"owner",true,3);
            Validator validator2 = new Validator(publicKey,"seller",true,4);
            Validation[] validations = {new Validation(validator1,signatue1), new Validation(validator2,signatue2)};

            BlockHeader blockHeader = new BlockHeader("101",prevhash,hash,timestamp,
                    KeyGenerator.getInstance().getPublicKey(),123,true);
            Transaction transaction = new Transaction(publicKey,validations,data,"tran1",new TransactionInfo());

            Block block = new Block(blockHeader,transaction);
            JSONObject jsonObject = new JSONObject(block);
            String myJson = jsonObject.toString();
            System.out.println(myJson);

        } catch (Exception e) {
            e.getMessage();
        }


    }
}
