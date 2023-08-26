package DaoImplemention;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import DaoInterfaces.*;
import Databaseconnection.*;
import Model.*;



public class userimplemention implements userinterface {
    static databaseconnection db = new databaseconnection();
    static List<User> list = new ArrayList<>();
    // static User user1;

   /* @Override
    public List<User> getalluser() throws SQLException, ClassNotFoundException {

        try {
            Connection con = db.getconnection();
            Statement st = con.createStatement();
            String query = "select * from users";
            ResultSet set = st.executeQuery(query);
            while (set.next()
            ) { User user1=new User();
                user1.setUser_name(set.getString("username"));
                user1.setPassword(set.getString("passwords"));
                user1.setPhoneno(set.getString("phoneno"));
                list.add(user1);


            }
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }


        return list;
    }*/

    @Override
    public void SignUp(User user1) throws SQLException, ClassNotFoundException {
        try {
            Connection con = db.getconnection();
            PreparedStatement p = con.prepareStatement("insert into users (username,passwords,phoneno) values(?,?,?)");
           // String user_name = null;
           // String user_name = " ";
            p.setString(1,user1.getUser_name());
            p.setString(2, user1.getPassword());
            p.setString(3, user1.getPhoneno());
            int rows = p.executeUpdate();
            if (rows > 0) {
                System.out.println("Account created succesfully");
            }
            else{
                System.out.println("Please check ur details");
            }


        } catch (SQLException s) {
            System.out.println(s.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        try {

            Connection con = db.getconnection();
            Statement st = con.createStatement();
            String query = "select * from users";
            ResultSet set = st.executeQuery(query);
            while (set.next()) {
                String u_name = set.getString("username");
                String u_password = set.getString("passwords");
                if (u_name.equals(username) && u_password.equals(password)) {
                    //System.out.println("Login successfully!!");
                    return true;
                }
            }

        } catch (SQLException s) {
            System.out.println(s.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public int getuserId(String username) throws SQLException, ClassNotFoundException {
        try {
            Connection con = db.getconnection();
            Statement st = con.createStatement();
            String query = " select * from users where username='" + username + "'";
            ResultSet set = st.executeQuery(query);
            while (set.next()) {
                return set.getInt("id");
            }
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println(c.getMessage());
        }
        return 0;
    }
}


