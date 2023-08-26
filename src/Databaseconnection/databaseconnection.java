package Databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseconnection {
    public static Connection getconnection() throws ClassNotFoundException, SQLException {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jukebox";
            String user_name = "root";
            String pass_word = "root";
            con = DriverManager.getConnection(url, user_name, pass_word);

        } catch (SQLException s) {
            System.out.println(s.getMessage());
        } catch (ClassNotFoundException s) {
            System.out.println(s.getMessage());
        }
        return con;
    }
}
