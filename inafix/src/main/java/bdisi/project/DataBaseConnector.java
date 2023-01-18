package bdisi.project;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class DataBaseConnector {

    public static void main( String[] args ) {
        final String url = "jdbc:mysql://localhost:3306/Inafix";

        final String username = "root";

        final String password = "sined";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Dzial;");

            while(resultSet.next()){
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDouble(3));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
