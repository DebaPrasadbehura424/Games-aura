
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//  class  DebaException extends Exception{
//      public DebaException(String serverError) {
//          super(serverError);
//      }
//  }

public class Config {
    Connection connection;
    Statement statement;
    public  Config()
//            throws DebaException
    {
        String name="root";
        String key="sitaram";
        String Url="jdbc:mysql://localhost:3306/lastman";
        try {
            connection = DriverManager.getConnection(Url, name, key);
            statement=connection.createStatement();
        }catch (SQLException e) {
//            throw new DebaException("server error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
//            throws DebaException
    {
        new Config();
    }

}
