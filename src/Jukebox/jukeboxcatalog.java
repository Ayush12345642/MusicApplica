package Jukebox;

import DaoImplemention.playlistimplemention;
import DaoImplemention.songimplemention;
import DaoImplemention.userimplemention;
import DaoInterfaces.PlaylistIbterfaces;
import DaoInterfaces.songinterface;
import DaoInterfaces.userinterface;
import Model.Song;
import Model.User;
import Music.Audio;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class jukeboxcatalog {
    // jukeboxcatalog j=new jukeboxcatalog();
    static userimplemention ui = new userimplemention();
    static songinterface si = new songimplemention();
    //static PlaylistIbterfaces pi = (PlaylistIbterfaces) new playlistimplemention();
    static PlaylistIbterfaces pi = new playlistimplemention();
    static Scanner s = new Scanner(System.in);
    static String name;
    static int id;
    static boolean check_user = false;
    //static Playlist_function pf = new Playlist_function();
    //static Song_function sf = new Song_function();
    //static user_function uf=new user_function();

    public static void homepage() throws SQLException, ClassNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException, InputMismatchException {
        // int choicee=0;
        // do{
        try {
            String q = "";
            while (!q.equalsIgnoreCase("q")) {
            System.out.println(" +\"\"\"\"\"+ ");
            System.out.println("[| o o |]");
            System.out.println(" |  ^  | ");
            System.out.println(" | '-' | ");
            System.out.println(" +-----+ ");
               /* System.out.println("|           | |------- |       |-------  |---------| |---------| |---------    ---|--- |--------|       | |      | |    |  |------- |-----| |------| |       |");
                System.out.println("|           | |        |       |         |         | | |    |  | |                |    |        |       | |      | |   |   |        |     | |      |   |   |");
                System.out.println("|    _ _    | |------- |       |         |         | |    |    | |---------       |    |        |       | |      | | |     |------- |-----| |      |     |");
                System.out.println("|   |   |   | |        |       |         |         | |         | |                |    |        |       | |      | |   |   |        |     | |      |    |  |");
                System.out.println("|___|   |___| |_______ |_______|________ |---------| |         | |_________       |    |--------|  _____| |______| |     | |_______ |_____| |------|   |      |");*/
            System.out.println("");


            System.out.println("...................................................................................");
            System.out.println((""));

            System.out.println(".................WELCOME TO THE MUSIC WORLD.........................................");
            System.out.println("");
            System.out.println("....................................................................................");
            System.out.println("");
            System.out.println("A).Login B).SignUp Q).Exit");
            System.out.println((""));
            System.out.println("....................................................................................");
                String choice = s.next();
                choice = choice.toUpperCase();
                // again=false;
                switch (choice) {
                    case ("A"): {
                        login();
                        homemenu();
                        break;
                    }
                    case ("B"): {
                        signup();
                        System.out.println("");
                        login();
                        homemenu();
                        break;
                    }
                    case ("Q"):
            System.exit(0);
                    default:
            System.out.println("");
            System.out.println("Please Choose the Right Choice");
            System.out.println("");
                        homepage();

                }
            }
            // System.out.println(id);
            homemenu();
        } catch (InputMismatchException e) {
            System.out.println("Please choose the correct number");
            // again=false;
            //homepage();

        }
        // System.out.println("Enter 0 for continue and 1 for exit");
    }//while(choicee!=0);
    // homepage();

    //}
    //homepage();


    //j.homepage();

    public static void login() throws SQLException, ClassNotFoundException, InputMismatchException {
        try {
            System.out.println("....................................LOGIN PAGE.........................................");
            System.out.println("");
            System.out.println("........................................................................................");
            System.out.println("Enter username");
            // System.out.println("");
            String user_name =s.next();
            // System.out.println("");
            System.out.println("........................................................................................");
            System.out.println("Enter password");
            String pass_word =s.next();
            System.out.println("........................................................................................");
            if (ui.login(user_name, pass_word)) {
                id = ui.getuserId(user_name);
                //System.out.println(id);
            System.out.println("Succesfully Login");
            System.out.println("....................................................................................");
                check_user = true;
            } else {
            System.out.println("Invalid username and password");
            System.out.println("....................................................................................");
            System.out.println("1).Login 2).SignUp 3).Exit");
            System.out.println("....................................................................................");
                int choice = s.nextInt();
                switch (choice) {
                    case 1: {
                        login();
                        break;
                    }
                    case 2: {
                        signup();
                        System.out.println("");
                        login();

                        break;
                    }
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please make a right choice");
                        System.out.println("");
                        //System.out.println("....");
                }
               // System.out.println(id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public static void signup() throws SQLException, ClassNotFoundException, InputMismatchException {
        try {
            System.out.println(".......................... SIGNUP PAGE..................................................");
            System.out.println("........................................................................................");
            System.out.println("Enter Username");
            System.out.println("........................................................................................");
            String name_ = s.next();
            System.out.println("Enter Password");
            System.out.println("........................................................................................");
            String password_ = s.next();
            System.out.println("Enter phoneno");
            String phoneno = s.next();
            System.out.println("........................................................................................");

            ui.SignUp(new User(name_, password_, phoneno));

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    /* public static void catalog() throws SQLException, ClassNotFoundException,InputMismatchException {
        try { String q="";
            while(!q.equalsIgnoreCase("q"))
            {



            System.out.println("What do u want to do");
            System.out.println("A). songs");
            System.out.println("Q).Exit");
        }catch(Exception e)
        {
            System.out.println("Please Choose The Correct number");

        }
    }*/

    public static void homemenu() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException, InputMismatchException {

        if (check_user) {

            String q = "";
            while (!q.equalsIgnoreCase("q")) {


                System.out.println(" What do u want to do");
                System.out.println("....................................................................................");
                System.out.println("A). songs");
                System.out.println("....................................................................................");
                System.out.println("Q).Exit");
                System.out.println("....................................................................................");
                String choice = s.next();
                System.out.println("");
                System.out.println("....................................................................................");
                choice = choice.toUpperCase();
                switch (choice) {
                    case ("A"): {
                        SongOperations();
                        break;
                    }
                    case ("Q"):
                        System.exit(0);
                        break;
                    default:
                        //System.out.println("a");
                        System.out.println("                                                                                   Please Enter correct choice");
                        System.out.println("");
                }
            }

        }
    }


    public static void SongOperations() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException, InputMismatchException {
        try {
            String q = "";
            while (!q.equalsIgnoreCase("q")) {
                System.out.println("OPERATIONS");
                System.out.println("....................................................................................");
                System.out.println("A).Play Songs");
                System.out.println("B). Search Songs");
                System.out.println("C). To add playlist");
                System.out.println("D) Go Back");
                System.out.println("Q) Exit");
                System.out.println("....................................................................................");
                //System.out.println("..................................................................................");

                //System.out.println(" Press 1 for Playing Song 2 for Search Songs 3 for Creating playlist 4 for Go Back 5 for Exit");
                String choice = s.next();
                choice = choice.toUpperCase();
                switch (choice) {
                    case ("A"): {
                        Playsong();
                        break;
                    }
                    case ("B"): {
                        songsearch();
                        break;
                    }
                    case ("C"): {
                        addsongtoplaylist();
                        break;
                    }
                    case ("D"): {
                        homemenu();
                        break;
                    }
                    case ("Q"):
                        System.exit(0);
                        break;
                    default:
                        System.out.println(" Please Choose the Right Choice");
                        System.out.println("");
                }
            }


        } catch (Exception e) {
            System.out.println("Please Choose The Right Choice");
            //SongOperations();
        }
        System.out.println("");
    }

    public static void addsongtoplaylist() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException, InputMismatchException {
        try {
            String q = "";
            while (!q.equalsIgnoreCase("q")) {

                System.out.println("....................................................................................");
                System.out.println("Playlist");
                System.out.println("A). Add to Existing Playlist");
                System.out.println("B). Add to new playlist");
                System.out.println("C) Go Back");
                System.out.println("Q). exit");
                System.out.println("....................................................................................");
                String choice = s.next();
                choice = choice.toUpperCase();
                switch (choice) {
                    case ("A"): {
                        //uf.allplaylist();
                        // System.out.println("Enter id ");
                        // int uid=s.nextInt();
                        //int id=ui.getuserId(username);
                        Set<String> list = pi.getAllPlaylist(id);

                        for (String ss : list) {
                            System.out.println(ss);
                        }
                        System.out.println("Enter Playlistname to add song");
                        String playlistname = s.next();
               /* List<Song> song = si.displayallsong();
                for (Song sss : song) {
                    System.out.println(sss.getSong_id() + " " + sss.getSong_name());
                }*/
                /*int i = 0;
                while (i == 0) {
                    System.out.println("Enter SongID to add to playlist");
                    int sid = s.nextInt();
                    for (Song sss : song) {
                        if (sid == sss.getSong_id()) */
                        pi.addsongToPlaylist(id, playlistname);

                        // }
                   /* }
                    System.out.println("Enter 0 for continue ,press 1 for exit");
                    int flag = s.nextInt();
                    i = flag;
                }*/
                        SongOperations();
                        break;
                    }
                    case ("B"): {
                        //System.out.println(id);
                        System.out.println("Enter New Playlist Name");
                        String newPlaylist = s.next();
                        List<Song> song = si.displayallsong();
               /* for (Song sss : song) {
                    System.out.println(sss.getSong_id() + " " + sss.getSong_name());
                }*/
                /*int j = 0;
                while (j == 0) {

                    System.out.println("Enter SongId to add into playlist");
                    int sid = s.nextInt();
                    for (Song sss : song) {
                        if (sid == sss.getSong_id()) {*/
                        pi.addsongToPlaylist(id, newPlaylist);

                       /* }
                    }
                    System.out.println("Enter 0 for continue press 1 for exit");
                    int flag = s.nextInt();
                    j = flag;

                }*/
                        SongOperations();
                        break;
                    }
                    case ("C"): {
                        SongOperations();
                        break;
                    }
                    case ("Q"):
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Please Choose the Right Choice ");
                        System.out.println("");
                }
            }
        } catch (Exception e) {
            //System.out.println("Please Choose The Right Number");
            System.out.println(e.getMessage());
            // addsongtoplaylist();
        }
    }

    public static void Playsong() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        List<Song> list = si.displayallsong();
        System.out.println("");
        //System.out.println( "+SONG ID+"  "+SONG NAME", "ARTIST NAME", "ALBUM NAME", "GENRE", "DURATION");
        System.out.format("%-10s %-25s %-20s %-20S %-20S %-20S", "SONG ID", "SONG NAME", "ARTIST NAME", "ALBUM NAME", "GENRE", "DURATION");
        System.out.println("");
        for (Song s : list) {
            System.out.format("%-10s %-25s %-20s %-20S %-20S %-20S", s.getSong_id(), s.getSong_name(), s.getArtist_name(), s.getAlbum_name(), s.getGenre(), s.getDuration());

            //System.out.println(" s.getSong_id()+","+ s.getSong_name(), s.getArtist_name(), s.getAlbum_name(), s.getGenre(), s.getDuration());
            System.out.println("");

        }
        System.out.println("Enter SongId to play song");
        int id = s.nextInt();
        for (Song s : list) {
            if (id == s.getSong_id()) {
                Audio a = new Audio();
                a.playsong(s.getSong_id());
            }
        }
    }


    public static void songsearch() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException, InputMismatchException {
        try {
            String q = "";
            while (!q.equalsIgnoreCase("q")) {
                System.out.println("Search By");
                System.out.println("A).Artist");
                System.out.println("B).Genre");
                System.out.println("C).Album");
                System.out.println("D).Playlist");
                System.out.println("E).GoBack");
                System.out.println(" Q).Exit");
                String songsearch = s.next();
                songsearch = songsearch.toUpperCase();
                switch (songsearch) {
                    case ("A"): {
                        songbyartist();
                        break;
                    }
                    case ("B"): {
                        songbygenre();
                        break;
                    }
                    case ("C"): {
                        songbyalbum();
                        break;
                    }
                    case ("D"): {
                        songbyplaylist();
                        break;
                    }
                    case ("E"): {
                        SongOperations();
                        break;
                    }
                    case ("Q"):
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please Choose the Right Choice");
                        System.out.println("");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //System.out.println("Please Choose The Right Input");
            // songsearch();
        }
    }

    public static void songbyartist() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Audio audio = new Audio();
        List<Song> list = si.displayallsong();

        Set<String> set = si.getAllArtistName();
        System.out.println("Available Artist");
        System.out.println(".....................");
        for (String s : set) {

            System.out.println(s);
            //System.out.println("");
        }
        System.out.println("Enter Artist name");
        String artist = s.next();
        // s.nextLine();
        // List<Song> sortedlist = new ArrayList<>();       // List<Song> sortedlist = new ArrayList<>();
        // Predicate su= list.stream().filter(a -> a.getArtist_name().equalsIgnoreCase(artist)).forEach(b -> sortedlist.add(b));
        List<Song> song = si.songlistbyartistname(artist);
        //System.out.format("|%-10s %-20s |%-20s","SONGID","SONGNAME","ARTISTNAME");
        //System.out.println("");

        for (Song so : song) {

            //System.out.println("");
          //System.out.format("|%-10s %-20s |%-20s",so.getSong_id(),so.getSong_name(),so.getArtist_name());
            //System.out.println(" ");
            System.out.println(so.getSong_id() + " " + so.getSong_name() + " " + so.getArtist_name());
            //System.out.println("");
        }
        //list.stream().filter(a -> a.getArtist_name().equals(artist)).forEach(b -> sortedlist.add(b));
        /*for(Song s:song)
        {
            System.out.println(s.getArtist_name());
        }*/

        // Set<String> ss = si.getAllArtistName();
        // if (si.getAllArtistName().equals(artist)) {
        for (Song so : song) {
            //4
            // System.out.println( so.getSong_id() + " " + so.getSong_name() + " " + so.getArtist_name());
            //System.out.println("");

            // if(so.getArtist_name().equalsIgnoreCase(ss);

            System.out.println("Enter Song Id to Play Music");
            int song_id = s.nextInt();
            for (Song s : song) {
                if (song_id == s.getSong_id()) {
                    audio.playsong(s.getSong_id());
                }

            }
        }
        /*}else{
            System.out.println("Please Choose the coorect artist");
            songbyartist();
        }*/
    }


    public static void songbygenre() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Audio audio = new Audio();
        Set<String> list = si.getAllGenre();
        System.out.println("Available Genre");
        for (String s : list) {

            System.out.println(s);
            // System.out.println("");
        }
        System.out.println("Enter Genre name");
        String genre = s.next();
        List<Song> song = si.songlistbygenre(genre);
        for (Song so : song) {
            //System.out.format("|%-10s %-20s %-20s|","SONGID","SONGNAME","GENRE");
            //System.out.println("");
            //System.out.format("|%-10s %-20s %-20s|",so.getSong_id(),so.getSong_name(),so.getArtist_name());
            //System.out.format("|%-10s %-20s %-20s|",so.getSong_id(),so.getSong_name(),so.getGenre());
            System.out.println(so.getSong_id() + " " + so.getSong_name() + " " + so.getGenre());
           //System.out.println("");
        }
        for (Song so : song) {
            // System.out.println(so.getSong_id() + " " + so.getSong_name() + " " + so.getGenre());
            //System.out.println("");

            System.out.println("Enter Song Id to Play Music");
            int song_id = s.nextInt();
            for (Song s : song) {
                if (song_id == s.getSong_id()) {
                    audio.playsong(s.getSong_id());
                }
            }
        }
    }

    public static void songbyalbum() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Audio audio = new Audio();
        Set<String> list = si.getAllAlbumNme();
        System.out.println("Available Album");
        for (String s : list) {

            System.out.println(s);
            //System.out.println("");
        }
        System.out.println("Enter Album name");
        String album = s.next();
        List<Song> song = si.songlistbyalbum(album);
        for (Song so : song) {
            //System.out.format("%|-10s %-20s %-20s|","SONGID","SONGNAME","GENRE");
            //System.out.println("");
            //System.out.format("%|-10s %-20s %-20s|",so.getSong_id(),so.getSong_name(),so.getAlbum_name());
            System.out.println(so.getSong_id() + " " + so.getSong_name() + " " + so.getAlbum_name());
            //System.out.println("");
        }
        for (Song so : song) {
           // System.out.println(so.getSong_id() + " " + so.getSong_name() + " " + so.getAlbum_name());
            System.out.println("");

            System.out.println("Enter Song Id to Play Music");
            int song_id = s.nextInt();
            for (Song s : song) {
                if (song_id == s.getSong_id()) {
                    audio.playsongbyalbumname(s.getArtist_name(),s.getSong_id());
                }else{
                    System.out.println("Songid not present");
                }
            }
        }
    }

    public static void songbyplaylist() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {

       /* Set<String> list = pi.getAllPlaylist(id);
        System.out.println(id);
        for (String s : list) {
            System.out.println(s);
        }
       // System.out.println("Enter the Playlist name");
       // String playlist = s.next();
        //id= ui.getuserId();
        /*Set<Integer> list1 =*/
        pi.getAllSongsOfPlaylist(id);
       /* for (Integer i : list1) {
            System.out.println(i);*/


        List<Song> song = si.displayallsong();
        //song.stream().filter(a->a.getSong_id()==i);//forEach(b-> song.add(b));


           /* for (Song sung : song) {
               // System.out.println(sung.getSong_id());
                if (sung.getSong_id() == i) {


                    System.out.println("");
                    System.out.format("%-10s %-25s %-20s", "SONG ID", "SONG NAME", "ARTIST NAME");
                    System.out.println("");
                    //  for (Song ss : song) {
                    System.out.format("%-10s %-25s %-20s", sung.getSong_id(), sung.getSong_name(), sung.getArtist_name());
                    System.out.println("");*/

        // for (Song s : song) {
        System.out.println("............................................................................................................................................................................");
        System.out.println("Enter id to play song");
        int idd = s.nextInt();
        for (Song songgss : song) {
            if (idd == songgss.getSong_id()) {
                Audio a = new Audio();
                a.playsong(songgss.getSong_id());
            }
        }
    }
}


