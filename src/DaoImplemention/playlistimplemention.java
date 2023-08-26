package DaoImplemention;
import java.sql.*;
import Model.*;
import DaoInterfaces.*;
import Databaseconnection.*;
import java.util.*;

public class playlistimplemention implements PlaylistIbterfaces {
    static databaseconnection db = new databaseconnection();

    @Override
    public Set<String> getAllPlaylist(int userid) throws SQLException, ClassNotFoundException {
        Set<String>list=new TreeSet<>();
        Connection con=db.getconnection();
        Statement st=con.createStatement();
        String query="select * from playlist where user_id="+userid;
        ResultSet set=st.executeQuery(query);
        while(set.next())
        {
            list.add(set.getString("playlist_name"));
        }
        return list;
    }

    @Override
    public void addsongToPlaylist(int userid , String playlistname) throws SQLException, ClassNotFoundException {
        Scanner s=new Scanner(System.in);
        Connection con=db.getconnection();
        Statement st=con.createStatement();
        try {
            int i;
            do {
                System.out.format("%-10s %-10s", "SONGID", "SONGNAME");
                System.out.println("");

                String query = "select song_id,song_name from song";
                ResultSet set = st.executeQuery(query);
                while (set.next()) {
                    System.out.format("%-10s %-10s", set.getInt("song_id"), set.getString("song_name"));
                    System.out.println(" ");
                }
                System.out.println("Enter Songid to Add song in playlist");
                int songid = s.nextInt();
                String query1 = "insert into playlist(song_id,user_id,playlist_name) values(" + songid + "," + userid + ",'" + playlistname + "')";
                st.executeUpdate(query1);
                System.out.println("Press 1 for Enter more song 0 for Exit");
                i = s.nextInt();
            } while (i == 1);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }




    }

    @Override
    public Set<String> getAllSongsOfPlaylist(int userid) throws SQLException, ClassNotFoundException {
        Scanner s=new Scanner(System.in);
        Set<String>list=new TreeSet<>();
        Connection con=db.getconnection();
        Statement st=con.createStatement();
        String query="select distinct(playlist_name) from playlist where user_id="+userid;
        ResultSet set=st.executeQuery(query);
        while(set.next())
        {

            System.out.println(set.getString("Playlist_name"));


        }
        System.out.println("Enter the Playlist Name");
        String pname=s.next();
        System.out.format("%-10s %-10s","SONG ID","SONG NAME");
        System.out.println("");
        String query1="select distinct(playlist.song_id),song.song_name from playlist right join song on song.song_id=playlist.song_id where playlist_name='"+pname+"'and user_id="+userid;

        ResultSet set1=st.executeQuery(query1);
        while(set1.next())
        {
            System.out.format("%-10s %-10s",set1.getInt(1),set1.getString(2));
            System.out.println("");
            list.add(set1.getString(2));
        }
        // Collections.sort(list);
        return list;
    }


}


