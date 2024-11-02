package bank.management.system;

import javax.swing.*;
import java.sql.*;

public class Con {
    Connection connection;
    Statement statement;

    public Con() {
        try {
            // Use a constant for the database URL and credentials
            String url = "jdbc:mysql://localhost:3306/banksystem";
            String user = "root";
            String password = "sitaram";

            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally handle exceptions like logging or notifying user
        }
    }


    String pin;

//    public int UpadteAmount(String pin) {
//        this.pin = pin;
//        int sum = 0;
//        int cash = 0;
//        try {
//            ResultSet rs = statement.executeQuery("select SUM(amount) from depositBar where pin = '" + pin + "' or type='deposit'");
//            if (rs.next()) {
//                sum = rs.getInt(1);
//            }
//            ResultSet rs2 = statement.executeQuery("select  SUM(amount) from depositBar where pin = '" + pin + "' or type='withdrawal'");
//            if (rs2.next()) {
//                cash = rs2.getInt(1);
//            }
//            int total = sum - cash;
//
//            if (total > 0) {
//                return total;
//            }else {
//                JOptionPane.showMessageDialog(null,"Withdrawal fail Failed");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//       return 0;
//    }

    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
