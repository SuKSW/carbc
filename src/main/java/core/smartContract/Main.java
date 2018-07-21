package core.smartContract;

import core.blockchain.*;
import core.connection.SmartContractJDBCDAO;
import core.connection.VehicleHistory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchProviderException, InvalidKeySpecException, InterruptedException {

//        // create a key pair generator
//        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
//
//        //initialize the key pair generator
//        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
//        keyGen.initialize(1024, random);
//
//        //generate the pair of keys
//        KeyPair pair = keyGen.generateKeyPair();
//        PrivateKey priv = pair.getPrivate();
//        PublicKey pub = pair.getPublic();
//
//        byte[] key = pub.getEncoded();

//        TransactionDummy tr = new TransactionDummy();
//        tr.executeSmartContractMethod();

        SmartContractJDBCDAO smartContractJDBCDAO = new SmartContractJDBCDAO();
        VehicleHistory vehicleHistory = new VehicleHistory("a","a","a","a","a","a","a","a");

        smartContractJDBCDAO.add(vehicleHistory);


    }


    public static void executeTransaction(Block block){
        BlockHeader blockHeader = block.getHeader();
        long blockNumber = blockHeader.getBlockNumber();

        Transaction transaction = block.getTransaction();

        String sender = transaction.getSender();
        ArrayList<Validation> validation = transaction.getValidations();
        byte[] data = transaction.getData();
        String transactionID = transaction.getTransactionID();
        TransactionInfo transactionInfo = transaction.getTransactionInfo();

//        String smartContractId = transactionInfo.getSmartContractId();
        String smartContractSignature = transactionInfo.getSmartContractSignature();
        String smartContractMethod = transactionInfo.getSmartContractMethod();
        String[] parameters = transactionInfo.getParameters();

        int noOfValidators = validation.size();

        String validationArray[][] = new String[1][noOfValidators];
        for (int i=0; i<noOfValidators; i++){
            Validation validations = validation.get(i);
            validationArray[0][1] = validations.getValidator().getValidator();
            validationArray[0][2] = validations.getSignature().toString();
        }


//
//        int smartContractId;
//        byte[] smartContractSignature;
//        String smartContractMethod;
//        String[] parameters;



    }
}




























//        String message;
//        JSONObject obj = new JSONObject();
//        JSONParser parser = new JSONParser();

//        obj.put("name", "foo");
//        obj.put("num", new Integer(100));
//        obj.put("balance", new Double(1000.21));
//        obj.put("is_vip", new Boolean(true));
//
//        byte[] objAsBytes = obj.toString().getBytes("UTF-8");
//
//        System.out.println(objAsBytes.toString());
//
//
//
//        JSONObject testV = new JSONObject(new String(objAsBytes));
//
//        System.out.println(testV.toString());
//        String testV=new JSONObject(new String(responseBody)).toString();