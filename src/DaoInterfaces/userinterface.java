package DaoInterfaces;

import Model.User;

import java.sql.SQLException;
//

public interface userinterface {
      public boolean login(String username,String password)throws SQLException,ClassNotFoundException;
      public void SignUp(User user1)throws SQLException,ClassNotFoundException;
     public int getuserId(String username)throws SQLException,ClassNotFoundException;
}
