import java.sql.*;

public class Link {
    Connection connection;
    public Statement statement;

    public Link() {
        try {


            String url = "jdbc:mysql://localhost:3306/office";
            String user = "root";
            String pass = "sitaram";
            connection = DriverManager.getConnection(url,user,pass);
            statement=connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public int getHighScore(String name) {
        try {
            String query = "SELECT score FROM officebar WHERE name='" + name + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString("score"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Default score if not found
    }

    public void updateHighScore(String name, int newScore) {
        try {
            String query = "UPDATE officebar SET score='" + newScore + "' WHERE name='" + name + "'";
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
