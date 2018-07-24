package core.smartContract;

import core.blockchain.*;
import core.connection.ConnectionFactory;
import core.connection.DatabaseClassLoader;
import core.connection.SmartContractJDBCDAO;
import core.connection.VehicleHistory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws Exception {

//        TransactionDummy tr = new TransactionDummy();
//        tr.executeSmartContractMethod();

//        SmartContractJDBCDAO smartContractJDBCDAO = new SmartContractJDBCDAO();
//        VehicleHistory vehicleHistory = new VehicleHistory("a","a","a","a","a","a","a","a");
//
//        smartContractJDBCDAO.add(vehicleHistory);

//        System.out.println((getClassFromFile("VehicleRegistration")));

        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;

//        add(connection, ptmt);

        DatabaseClassLoader databaseClassLoader = new DatabaseClassLoader();
        Class<?> cl = databaseClassLoader.findClass("VehicleRegistration");

    }


    public static void add(Connection connection, PreparedStatement ptmt) throws FileNotFoundException {

        File file = new File("/home/sajinie/Desktop/VehicleRegistration.class");
        FileInputStream input = new FileInputStream(file);

        try {
            String queryString = "INSERT INTO `SmartContract`(`signature`, `code`, " +
                    "`owner`, `message`, `block_number`, `block_timestamp`) VALUES(?,?,?,?,?,?)";

            connection = ConnectionFactory.getInstance().getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, "sa");
            ptmt.setBinaryStream(2, input);
            ptmt.setString(3, "sa");
            ptmt.setString(4, "sa");
            ptmt.setInt(5, 3);
            ptmt.setString(6, "dss");
            ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String getFileContent(FileInputStream fis) throws IOException {
        StringBuilder sb = new StringBuilder();
        Reader r = new InputStreamReader(fis, "UTF-8");  //or whatever encoding
        int ch = r.read();
        while(ch >= 0) {
            sb.append(ch);
            ch = r.read();
        }
        return sb.toString();
    }

    public static String readCodeFromFile(String fileLocation) throws IOException {
        File file = new File(fileLocation);
        FileInputStream input = new FileInputStream(file);
        String contractCode = getFileContent(input);
        return contractCode;
    }


    public static void executeTransaction(Block block){
        BlockHeader blockHeader = block.getHeader();
        int blockNumber = (int)blockHeader.getBlockNumber();
        String blockHash = blockHeader.getHash();

        Transaction transaction = block.getTransaction();

        String sender = transaction.getSender();
        ArrayList<Validation> validation = transaction.getValidations();
        String transactionID = transaction.getTransactionID();
        TransactionInfo transactionInfo = transaction.getTransactionInfo();

        String smartContractSignature = transactionInfo.getSmartContractSignature();
        String smartContractMethod = transactionInfo.getSmartContractMethod();
        String[] parameters = transactionInfo.getParameters();
        String event = transactionInfo.getEvent();
        String data = transactionInfo.getData();

        int noOfValidators = validation.size();

        String validationArray[][] = new String[1][noOfValidators];
        for (int i=0; i<noOfValidators; i++){
            Validation validations = validation.get(i);
            validationArray[i][0] = validations.getValidator().getValidator();
            validationArray[i][1] = validations.getSignature().toString();
        }

        SmartContractJDBCDAO smartContractJDBCDAO = new SmartContractJDBCDAO();
        VehicleHistory vehicleHistory = new VehicleHistory("a",transactionID,
                blockNumber,blockHash,event,sender,validationArray.toString(),data);

        smartContractJDBCDAO.add(vehicleHistory);

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