package Model;

public class User {
    private int user_id ;
   private String user_name;
     private String password;
     private String phoneno;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", phoneno='" + phoneno + '\'' +
                '}';

    }
   public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

   /* public User( int userid,String user_name, String password, String phoneno) {
        this.user_id = userid;
        this.user_name = user_name;
        this.password = password;
        this.phoneno = phoneno;
    }*/

   public User(String user_name,String password,String phoneno)
    {
        this.user_name=user_name;
        this.password=password;
        this.phoneno=phoneno;
    }
    public User()
    {

    }

}
