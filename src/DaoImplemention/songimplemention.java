package DaoImplemention;
import DaoInterfaces.*;
import DaoInterfaces.songinterface;
import Model.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import Databaseconnection.*;


public class songimplemention implements songinterface {
    //static List<Song> list=new ArrayList<>();
     //static Set<String> set=new TreeSet<>();
    static databaseconnection db = new databaseconnection();

    public List<Song> displayallsong() throws SQLException, ClassNotFoundException {
        List<Song> list = new ArrayList<>();
        Connection con = db.getconnection();
        Statement st = con.createStatement();
        String query = "select * from song";
        ResultSet set = st.executeQuery(query);
        while (set.next()
        ) {
            int id = set.getInt("song_id");
            String songname = set.getString("song_name");
            String artistname = set.getString("artist_name");
            String albumname = set.getString("album_name");
            String genre = set.getString("genre");
            String url = set.getString("url");
            String duration = set.getString("duration");
            Song s = new Song(id, songname, artistname, albumname, genre, url, duration);
            list.add(s);


        }
        Collections.sort(list, (o1, o2) -> o1.getSong_name().compareTo(o2.getSong_name()));
        return list;
    }

    /* public void retrievesonga(String songname)throws SQLException,ClassNotFoundException
     {
         System.out.format("%-10s %-10s %-10s","songname","artistname","albumname");
         System.out.println("");
         Connection con=db. getconnection();
         Statement st=con.createStatement();
         String query="select song_name,artist_name,album_name from song";
         ResultSet set=st.executeQuery(query);
         while(set.next())
         {
             String soname=set.getString("song_name");
             String arname=set.getString("artist_name");
             String alname=set.getString("album_name");
             System.out.format("%-10s %-10s %-10s",soname,arname,alname);
             System.out.println("....");


         }
     }*/
    public List<Song> songlistbysongname(String songname) throws SQLException, ClassNotFoundException {
        List<Song> list1 = new ArrayList<>();
        Connection con = db.getconnection();
        Statement p = null;
        String query = "select * from song where song_name ='" + songname + "'";
        p = con.createStatement();

        ResultSet set = p.executeQuery(query);
        while (set.next()) {
            int songid = set.getInt(1);
            String soname = set.getString(2);
            String arname = set.getString(3);
            String alname = set.getString(4);
            String genre = set.getString(5);
            String url = set.getString(6);
            String duration = set.getString(7);
            Song s = new Song(songid, soname, arname, alname, genre, url, duration);
            list1.add(s);
        }
        Collections.sort(list1, (o1, o2) -> o1.getSong_name().compareToIgnoreCase(o2.getSong_name()));
        return list1;

    }

    public List<Song> songlistbygenre(String genre) throws SQLException, ClassNotFoundException {
        List<Song> list2 = new ArrayList<>();
        Connection con = db.getconnection();
        Statement p = null;
        String query = "select * from song where genre ='" + genre + "'";
        p = con.createStatement();

        ResultSet set = p.executeQuery(query);
        while (set.next()) {
            int songid = set.getInt(1);
            String soname = set.getString(2);
            String arname = set.getString(3);
            String alname = set.getString(4);
            String gen = set.getString(5);
            String url = set.getString(6);
            String duration = set.getString(7);
            Song s = new Song(songid, soname, arname, alname, gen, url, duration);
            list2.add(s);
        }
        Collections.sort(list2, (o1, o2) -> o1.getGenre().compareToIgnoreCase(o2.getGenre()));
        return list2;
    }

    public List<Song> songlistbyartistname(String artistname) throws SQLException, ClassNotFoundException {
        List<Song> list3 = new ArrayList<>();
        Connection con = db.getconnection();
        //Statement p=null;
        String query = "select * from song where artist_name ='" + artistname + "'";

        Statement p = con.createStatement();
        ResultSet set = p.executeQuery(query);
        while (set.next()) {

            int songid = set.getInt(1);
            String soname = set.getString(2);
            String arname = set.getString(3);
            String alname = set.getString(4);
            String gen = set.getString(5);
            String url = set.getString(6);
            String duration = set.getString(7);
            Song s = new Song(songid, soname, arname, alname, gen, url, duration);
            list3.add(s);
        }

        Collections.sort(list3, (o1, o2) -> o1.getArtist_name().compareTo(o2.getArtist_name()));
        return list3;
    }

    public List<Song> songlistbyalbum(String albumname) throws SQLException, ClassNotFoundException {
        List<Song> list4 = new ArrayList<>();
        Connection con = db.getconnection();
        Statement p = null;
        String query = "select * from song where album_name ='" + albumname + "'";

        p = con.createStatement();
        ResultSet set = p.executeQuery(query);
        while (set.next()) {
            int songid = set.getInt(1);
            String soname = set.getString(2);
            String arname = set.getString(3);
            String alname = set.getString(4);
            String gen = set.getString(5);
            String url = set.getString(6);
            String duration = set.getString(7);
            Song s = new Song(songid, soname, arname, alname, gen, url, duration);
            list4.add(s);
        }
        Collections.sort(list4, (o1, o2) -> o1.getAlbum_name().compareToIgnoreCase(o2.getAlbum_name()));

        return list4;
    }

    public Set<String> getAllGenre() throws SQLException, ClassNotFoundException {
        Set<String> set1 = new TreeSet<>();
        Connection con = db.getconnection();
        Statement st = con.createStatement();
        String query = "select * from song";
        ResultSet set = st.executeQuery(query);
        while (set.next()) {
            set1.add(set.getString("genre"));

        }
        // Collections.sort(list1);
        return set1;
    }

    public Set<String> getAllAlbumNme() throws SQLException, ClassNotFoundException {
       Set<String> set2 = new TreeSet<>();
        Connection con = db.getconnection();
        Statement st = con.createStatement();
        String query = "select * from song";
        ResultSet set = st.executeQuery(query);
        while (set.next()) {
            set2.add(set.getString("album_name"));

        }
        // Collections.sort(list1);
        return set2;
    }

    public Set<String> getAllArtistName() throws SQLException, ClassNotFoundException {
       Set<String> set3 = new TreeSet<>();
        Connection con = db.getconnection();
        Statement st = con.createStatement();
        String query = "select * from song";
        ResultSet set = st.executeQuery(query);
        while (set.next()) {
            set3.add(set.getString("artist_name"));

        }
        // Collections.sort(list1);
        return set3;
    }

    public Set<String> getAllSongName() throws SQLException, ClassNotFoundException {
        Set<String> set4 = new TreeSet<>();
        Connection con = db.getconnection();
        Statement st = con.createStatement();
        String query = "select * from song";
        ResultSet set = st.executeQuery(query);
        while (set.next()) {
            set4.add(set.getString("song_name"));

        }
        // Collections.sort(list1);
        return set4;
    }

    public boolean getSongPresentBysongid(int songid) throws SQLException, ClassNotFoundException {
        List<Song> list = new ArrayList<>();
        boolean y = false;
        list = displayallsong();
        for (Song song : list) {
            if (songid == song.getSong_id()) {
                y = true;
            }
        }
        return y;

    }


    }


