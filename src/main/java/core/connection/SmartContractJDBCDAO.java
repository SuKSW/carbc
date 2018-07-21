package core.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SmartContractJDBCDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public SmartContractJDBCDAO(){

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public void add(VehicleHistory vehicleHistory) {
        try {
            String queryString = "INSERT INTO VehicleHistory(vid, transaction_id, block_id, " +
                    "block_hash, event, sender, validation_array, data) VALUES(?,?,?,?,?,?,?,?)";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, vehicleHistory.getVid());
            ptmt.setString(2, vehicleHistory.getTransactionId());
            ptmt.setString(3, vehicleHistory.getBlockId());
            ptmt.setString(4, vehicleHistory.getBlockHash());
            ptmt.setString(5, vehicleHistory.getEvent());
            ptmt.setString(6, vehicleHistory.getSender());
            ptmt.setString(7, vehicleHistory.getValidationArray());
            ptmt.setString(8, vehicleHistory.getData());
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
}
