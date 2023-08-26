package Music;
import java.io.*;
import java.util.*;
import java.sql.*;
import DaoImplemention.*;
import DaoInterfaces.*;
import javax.sound.sampled.*;
import Model.*;
import Jukebox.*;
import Databaseconnection.*;





public class Audio {
    static Scanner s = new Scanner(System.in);
    //Audiointerface a = new audioimplemention();
    static jukeboxcatalog ju=new jukeboxcatalog();
    static databaseconnection d=new databaseconnection();
    //static Play p=new Play();

    public int  playsong(int id) throws SQLException, ClassNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException,InputMismatchException {
        try {
            Connection con=d.getconnection();
            Statement st=con.createStatement();
            ResultSet set=st.executeQuery("select url from song where song_id="+id);
            set.next();
            String url=set.getString(1);
            Song obj=new Song();
            List<String>list=new ArrayList<>();
            AudioInputStream audio=AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            //clip.loop(clip.LOOP_CONTINUOUSLY);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P=Play, T=Pause, S=Stop, L=loop R=Reset,F=Forward,B=Reverse, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();

                switch (choice) {



            /*String url = songname;
            //File means abstract represenation of file and directory pathnames;
            File file = new File(url);
            //Audioinput stream is an input stream  with a specified audio format and length
            AudioInputStream audioinput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioinput);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P-play, T=Pause, S=Stop, L=loop R=Reset,F=Forward,B=Reverse, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();
                clip.loop(clip.LOOP_CONTINUOUSLY);
                switch (choice) {*/
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip Played From: " + milliSecondsToTimer(milliseconds));
                        //System.out.println("Songs in queue");
                        // SongsLeft(songname, songlist);
                        break;
                    }



                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip stopped at:" + milliSecondsToTimer(milliseconds));

                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        //break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;
                    }
                    case ("F"): {
                        clip.stop();
                        System.out.println("Enter The No of Seconds To Forward");
                        long sec=s.nextLong();
                        clip.start();
                        long timeposition=0L;
                        long total=0L;
                        //
                        // long duration = clip.getFrameLength();
                        // long current_time=
                        clip.setMicrosecondPosition(timeposition-sec*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();

                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;



                    }
                    case ("B"): {
                        clip.stop();
                        System.out.println("Enter the no of seconds for Reversing the song");
                        long timeposition=0L;
                        long total=0L;
                        long s2=s.nextLong();
                        clip.start();
                        clip.setMicrosecondPosition(timeposition-s2*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();

                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;

                    }
                    case ("R"): {
                        clip.setMicrosecondPosition(0);
                        break;
                    }
                    case ("N"): {

                        //Connection con=d.getconnection();
                        // Statement st=con.createStatement();
                        //String query="select * from song where"
                        //p.sound()
                        clip.stop();
                        id++;
                        Connection con1=d.getconnection();
                        Statement st1=con1.createStatement();
                        ResultSet set2=st1.executeQuery("select url from song where song_id="+id);
                        if(set2.next())
                        {
                            set2.getString(1);
                            playsong(id);

                        }


                        //p.sound(id);

                        //songname++;



                        /*SongsLeft(songname, songlist);
                        System.out.println("Choose the songid to be played next: ");
                        //s.next();
                        int songid = s.nextInt();
                        songinterface dao = new songimplemention();
                        String pathofsong = "";
                        for (Song song : songlist) {
                            if (song.getSong_id() == songid) {
                                pathofsong = song.getUrl();
                            }
                        }
                        clip.stop();
                        playsong(pathofsong, songlist);*/
                        break;

                    }
                    case ("Q"): {
                        clip.close();
                        ju.SongOperations();
                        break;
                    }
                    case ("RE"):
                        long tot = clip.getMicrosecondLength();
                        long micro = clip.getMicrosecondPosition();
                        //System.out.println(clip.getMicrosecondLength() / 1000000+" "+"seconds");
                        System.out.println("played for(seconds):" + micro / 1000000);
                        System.out.println("remaining time for this song :" + (tot - micro) / 1000000+" "+"seconds");
                        break;
                    default:
                        System.out.println("Not a valid response");


                }

            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            // playsong(String songname,List<Song> songlist);
        }
        return id;
    }


    public static String milliSecondsToTimer(long milliseconds)
    {
        String finalTimerString=" ";
        String secondString=" ";
        int hour=(int) (milliseconds/(1000 *60 *60));
        int minutes=(int) (milliseconds %(1000 *60 *60)) /(1000 *60);
        int second=(int) ((milliseconds %(1000 * 60 *60)) %(1000 *60) /1000);
        if(hour>0)
        {
            finalTimerString=hour+":";

        }
        if(second<10)
        {
            secondString="0"+second;

        }else{
            secondString=""+second;
        }
        finalTimerString=finalTimerString+minutes+":"+secondString;
        return finalTimerString;
    }
    public static void SongsLeft(String songname,List<Song> songlist)
    {
        System.out.println(" ");
        System.out.format("%-10s %-25s %-20s","SONGID","SONGNAME","ARTISTNAME");
        System.out.println(" ");
        songlist.stream().filter(a->!a.getSong_name().equals(songname)).forEach(b->{
            System.out.format("%-10s %-25s %-20s",b.getSong_id(),b.getSong_name(),b.getArtist_name());
            System.out.println(" ");
        });
    }
    public int playsongbyartistname(String artistname,int id) throws SQLException, ClassNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException,InputMismatchException
    {try {
        Connection con=d.getconnection();
        Statement st=con.createStatement();
        ResultSet set=st.executeQuery("select url from song where song_id="+id);
        set.next();
        String url=set.getString(1);
        Song obj=new Song();
        List<String>list=new ArrayList<>();
        AudioInputStream audio=AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
        Clip clip=AudioSystem.getClip();
        clip.open(audio);
        //clip.loop(clip.LOOP_CONTINUOUSLY);
        String choice = "";
        while (!choice.equalsIgnoreCase("q")) {
            System.out.println("P=Play, T=Pause, S=Stop, L=loop R=Reset,F=Forward,B=Reverse, N=Next Song,RE=Remaining time,Q=quit");
            choice = s.next();
            choice = choice.toUpperCase();

            switch (choice) {



            /*String url = songname;
            //File means abstract represenation of file and directory pathnames;
            File file = new File(url);
            //Audioinput stream is an input stream  with a specified audio format and length
            AudioInputStream audioinput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioinput);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P-play, T=Pause, S=Stop, L=loop R=Reset,F=Forward,B=Reverse, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();
                clip.loop(clip.LOOP_CONTINUOUSLY);
                switch (choice) {*/
                case ("P"): {
                    clip.start();
                    long clip_position = clip.getMicrosecondPosition();
                    long milliseconds = clip_position / 1000;
                    System.out.println("Clip Played From: " + milliSecondsToTimer(milliseconds));
                    //System.out.println("Songs in queue");
                    // SongsLeft(songname, songlist);
                    break;
                }



                case ("T"): {
                    clip.stop();
                    long clip_position = clip.getMicrosecondPosition();
                    long milliseconds = clip_position / 1000;
                    System.out.println("Clip stopped at:" + milliSecondsToTimer(milliseconds));

                    break;
                }
                case ("S"): {
                    clip.setMicrosecondPosition(0);
                    clip.stop();
                    //break;
                }
                case ("L"): {
                    clip.start();
                    clip.loop(clip.LOOP_CONTINUOUSLY);
                    break;
                }
                case ("F"): {
                    clip.stop();
                    System.out.println("Enter The No of Seconds To Forward");
                    long sec=s.nextLong();
                    clip.start();
                    long timeposition=0L;
                    long total=0L;
                    //
                    // long duration = clip.getFrameLength();
                    // long current_time=
                    clip.setMicrosecondPosition(timeposition-sec*1000000);
                    total=clip.getMicrosecondLength();
                    clip.getMicrosecondPosition();

                    System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                    System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                    System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                    break;



                }
                case ("B"): {
                    clip.stop();
                    System.out.println("Enter the no of seconds for Reversing the song");
                    long timeposition=0L;
                    long total=0L;
                    long s2=s.nextLong();
                    clip.start();
                    clip.setMicrosecondPosition(timeposition-s2*1000000);
                    total=clip.getMicrosecondLength();
                    clip.getMicrosecondPosition();

                    System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                    System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                    System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                    break;

                }
                case ("R"): {
                    clip.setMicrosecondPosition(0);
                    break;
                }
                case ("N"): {

                    //Connection con=d.getconnection();
                    // Statement st=con.createStatement();
                    //String query="select * from song where"
                    //p.sound()
                    clip.stop();
                    id++;
                    Connection con1=d.getconnection();
                    Statement st1=con1.createStatement();
                    ResultSet set2=st1.executeQuery("select url from song where artist_name='"+artistname+"' and song_id="+id );
                    if(set2.next())
                    {
                        String url1=set2.getString(1);
                        playsongbygenre(artistname,id);

                    }


                    //p.sound(id);

                    //songname++;



                        /*SongsLeft(songname, songlist);
                        System.out.println("Choose the songid to be played next: ");
                        //s.next();
                        int songid = s.nextInt();
                        songinterface dao = new songimplemention();
                        String pathofsong = "";
                        for (Song song : songlist) {
                            if (song.getSong_id() == songid) {
                                pathofsong = song.getUrl();
                            }
                        }
                        clip.stop();
                        playsong(pathofsong, songlist);*/
                    break;

                }
                case ("Q"): {
                    clip.close();
                    ju.SongOperations();
                    break;
                }
                case ("RE"):
                    long tot = clip.getMicrosecondLength();
                    long micro = clip.getMicrosecondPosition();
                    //System.out.println(clip.getMicrosecondLength() / 1000000+" "+"seconds");
                    System.out.println("played for(seconds):" + micro / 1000000);
                    System.out.println("remaining time for this song :" + (tot - micro) / 1000000+" "+"seconds");
                    break;
                default:
                    System.out.println("Not a valid response");


            }

        }
    }catch(Exception e)
    {
        System.out.println(e.getMessage());
        // playsong(String songname,List<Song> songlist);
    }
        return id;
       /* try {
            String url = songname;
            File file = new File(url);
            AudioInputStream audioinput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioinput);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P-play, T=Pause, S=Stop, L=loop,F=Forward,B=Reverse, R=Reset, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();
                switch (choice) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip Played Drom: " + milliSecondsToTimer(milliseconds));
                        // System.out.println("Songs in queue");
                        //SongsLeft(songname, songlist);
                        break;


                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip stopped at:" + milliSecondsToTimer(milliseconds));

                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    case ("F"): {
                        System.out.println("Enter The No of Seconds To Forward");
                        long sec=s.nextLong();
                        long timeposition=0L;
                        long total=0L;
                        //
                        // long duration = clip.getFrameLength();
                        // long current_time=
                        clip.setMicrosecondPosition(timeposition-sec*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();
                        clip.start();
                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;



                    }
                    case ("B"): {
                        System.out.println("Enter the no of seconds for Reversing the song");
                        long timeposition=0L;
                        long total=0L;
                        long s2=s.nextLong();
                        clip.setMicrosecondPosition(timeposition-s2*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();
                        clip.start();
                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;
                    }
                    case ("R"): {
                        clip.setMicrosecondPosition(0);
                        break;
                    }
                    case ("N"): {
                        clip.stop();

                        SongsLeft(songname, songlist);
                        System.out.println("Choose the songid to be played next: ");
                        //s.next();
                        int songid = s.nextInt();
                        songinterface dao = new songimplemention();
                        String pathofsong = "";
                        for (Song song : songlist) {
                            if (song.getSong_id() == songid) {
                                pathofsong = song.getUrl();
                            }
                        }
                        clip.stop();
                        playsongbyartistname(pathofsong, songlist);
                        break;

                    }
                    case ("Q"): {
                        clip.close();
                        ju.SongOperations();
                        break;
                    }
                    case ("RE"):
                        long tot = clip.getMicrosecondLength();
                        long micro = clip.getMicrosecondPosition();
                        // System.out.println(clip.getMicrosecondLength() / 1000000);
                        System.out.println("played in seconds:" + micro / 1000000+" "+"seconds");
                        System.out.println("remaining time for this song :" + (tot - micro) / 1000000+" "+"seconds");
                        break;
                    default:
                        System.out.println("Not a valid response");


                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }
    public int  playsongbygenre(String genre,int id) throws SQLException, ClassNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException,InputMismatchException
    {
        try {
            Connection con=d.getconnection();
            Statement st=con.createStatement();
            ResultSet set=st.executeQuery("select url from song where song_id="+id);
            set.next();
            String url=set.getString(1);
            Song obj=new Song();
            List<String>list=new ArrayList<>();
            AudioInputStream audio=AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            //clip.loop(clip.LOOP_CONTINUOUSLY);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P=Play, T=Pause, S=Stop, L=loop R=Reset,F=Forward,B=Reverse, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();

                switch (choice) {



            /*String url = songname;
            //File means abstract represenation of file and directory pathnames;
            File file = new File(url);
            //Audioinput stream is an input stream  with a specified audio format and length
            AudioInputStream audioinput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioinput);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P-play, T=Pause, S=Stop, L=loop R=Reset,F=Forward,B=Reverse, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();
                clip.loop(clip.LOOP_CONTINUOUSLY);
                switch (choice) {*/
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip Played From: " + milliSecondsToTimer(milliseconds));
                        //System.out.println("Songs in queue");
                        // SongsLeft(songname, songlist);
                        break;
                    }



                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip stopped at:" + milliSecondsToTimer(milliseconds));

                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        //break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;
                    }
                    case ("F"): {
                        clip.stop();
                        System.out.println("Enter The No of Seconds To Forward");
                        long sec=s.nextLong();
                        clip.start();
                        long timeposition=0L;
                        long total=0L;
                        //
                        // long duration = clip.getFrameLength();
                        // long current_time=
                        clip.setMicrosecondPosition(timeposition-sec*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();

                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;



                    }
                    case ("B"): {
                        clip.stop();
                        System.out.println("Enter the no of seconds for Reversing the song");
                        long timeposition=0L;
                        long total=0L;
                        long s2=s.nextLong();
                        clip.start();
                        clip.setMicrosecondPosition(timeposition-s2*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();

                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;

                    }
                    case ("R"): {
                        clip.setMicrosecondPosition(0);
                        break;
                    }
                    case ("N"): {

                        //Connection con=d.getconnection();
                        // Statement st=con.createStatement();
                        //String query="select * from song where"
                        //p.sound()
                        clip.stop();
                        id++;
                        Connection con1=d.getconnection();
                        Statement st1=con1.createStatement();
                        ResultSet set2=st1.executeQuery("select url from song where genre='"+genre+"' and song_id="+id );
                        if(set2.next())
                        {
                            String url1=set2.getString(1);
                            playsongbygenre(genre,id);

                        }


                        //p.sound(id);

                        //songname++;



                        /*SongsLeft(songname, songlist);
                        System.out.println("Choose the songid to be played next: ");
                        //s.next();
                        int songid = s.nextInt();
                        songinterface dao = new songimplemention();
                        String pathofsong = "";
                        for (Song song : songlist) {
                            if (song.getSong_id() == songid) {
                                pathofsong = song.getUrl();
                            }
                        }
                        clip.stop();
                        playsong(pathofsong, songlist);*/
                        break;

                    }
                    case ("Q"): {
                        clip.close();
                        ju.SongOperations();
                        break;
                    }
                    case ("RE"):
                        long tot = clip.getMicrosecondLength();
                        long micro = clip.getMicrosecondPosition();
                        //System.out.println(clip.getMicrosecondLength() / 1000000+" "+"seconds");
                        System.out.println("played for(seconds):" + micro / 1000000);
                        System.out.println("remaining time for this song :" + (tot - micro) / 1000000+" "+"seconds");
                        break;
                    default:
                        System.out.println("Not a valid response");


                }

            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            // playsong(String songname,List<Song> songlist);
        }
        return id;
    }

       /* try {
            String url = songname;
            File file = new File(url);
            AudioInputStream audioinput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioinput);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P-play, T=Pause, S=Stop, L=loop,F=Forward,B=Reverse, R=Reset, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();
                switch (choice) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip Played Drom: " + milliSecondsToTimer(milliseconds));
                        //System.out.println("Songs in queue");
                        //SongsLeft(songname, songlist);
                        break;


                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip stopped at:" + milliSecondsToTimer(milliseconds));

                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        break;
                    }
                    case ("F"): {
                        System.out.println("Enter The No of Seconds To Forward");
                        long sec=s.nextLong();
                        long timeposition=0L;
                        long total=0L;
                        //
                        // long duration = clip.getFrameLength();
                        // long current_time=
                        clip.setMicrosecondPosition(timeposition-sec*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();
                        clip.start();
                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;




                    }
                    case ("B"): {
                        System.out.println("Enter the no of seconds for Reversing the song");
                        long timeposition=0L;
                        long total=0L;
                        long s2=s.nextLong();
                        clip.setMicrosecondPosition(timeposition-s2*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();
                        clip.start();
                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;

                    }
                    case ("R"): {
                        clip.setMicrosecondPosition(0);
                        break;
                    }
                    case ("N"): {
                        SongsLeft(songname, songlist);
                        System.out.println("Choose the songid to be played next: ");
                        //s.next();
                        int songid = s.nextInt();
                        songinterface dao = new songimplemention();
                        String pathofsong = "";
                        for (Song song : songlist) {
                            if (song.getSong_id() == songid) {
                                pathofsong = song.getUrl();
                            }
                        }
                        clip.stop();
                        playsongbygenre(pathofsong, songlist);
                        break;

                    }
                    case ("Q"): {
                        clip.close();
                        ju.SongOperations();
                        break;
                    }
                    case ("RE"):
                        long tot = clip.getMicrosecondLength();
                        long micro = clip.getMicrosecondPosition();
                        // System.out.println(clip.getMicrosecondLength() / 1000000);
                        System.out.println("played for(seconds):" + micro / 1000000 + "seconds");
                        System.out.println("remaining time for this song :" + (tot - micro) / 1000000 + " " + "seconds");
                        break;
                    default:


                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/


    public int playsongbyalbumname(String albumname,int id) throws SQLException, ClassNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException,InputMismatchException{
        try {
            Connection con=d.getconnection();
            Statement st=con.createStatement();
            ResultSet set=st.executeQuery("select url from song where song_id="+id);
            set.next();
            String url=set.getString(1);
            Song obj=new Song();
            List<String>list=new ArrayList<>();
            AudioInputStream audio=AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            //clip.loop(clip.LOOP_CONTINUOUSLY);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P=Play, T=Pause, S=Stop, L=loop R=Reset,F=Forward,B=Reverse, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();

                switch (choice) {



            /*String url = songname;
            //File means abstract represenation of file and directory pathnames;
            File file = new File(url);
            //Audioinput stream is an input stream  with a specified audio format and length
            AudioInputStream audioinput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioinput);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P-play, T=Pause, S=Stop, L=loop R=Reset,F=Forward,B=Reverse, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();
                clip.loop(clip.LOOP_CONTINUOUSLY);
                switch (choice) {*/
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip Played From: " + milliSecondsToTimer(milliseconds));
                        //System.out.println("Songs in queue");
                        // SongsLeft(songname, songlist);
                        break;
                    }



                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip stopped at:" + milliSecondsToTimer(milliseconds));

                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        //break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;
                    }
                    case ("F"): {
                        clip.stop();
                        System.out.println("Enter The No of Seconds To Forward");
                        long sec=s.nextLong();
                        clip.start();
                        long timeposition=0L;
                        long total=0L;
                        //
                        // long duration = clip.getFrameLength();
                        // long current_time=
                        clip.setMicrosecondPosition(timeposition-sec*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();

                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;



                    }
                    case ("B"): {
                        clip.stop();
                        System.out.println("Enter the no of seconds for Reversing the song");
                        long timeposition=0L;
                        long total=0L;
                        long s2=s.nextLong();
                        clip.start();
                        clip.setMicrosecondPosition(timeposition-s2*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();

                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;

                    }
                    case ("R"): {
                        clip.setMicrosecondPosition(0);
                        break;
                    }
                    case ("N"): {

                        //Connection con=d.getconnection();
                        // Statement st=con.createStatement();
                        //String query="select * from song where"
                        //p.sound()
                        clip.stop();
                        id++;
                        Connection con1=d.getconnection();
                        Statement st1=con1.createStatement();
                        ResultSet set2=st1.executeQuery("select url from song where album_name='"+albumname+"' and song_id="+id );
                        if(set2.next())
                        {
                            String url1=set2.getString(1);
                            playsongbyalbumname(albumname,id);

                        }


                        //p.sound(id);

                        //songname++;



                        /*SongsLeft(songname, songlist);
                        System.out.println("Choose the songid to be played next: ");
                        //s.next();
                        int songid = s.nextInt();
                        songinterface dao = new songimplemention();
                        String pathofsong = "";
                        for (Song song : songlist) {
                            if (song.getSong_id() == songid) {
                                pathofsong = song.getUrl();
                            }
                        }
                        clip.stop();
                        playsong(pathofsong, songlist);*/
                        break;

                    }
                    case ("Q"): {
                        clip.close();
                        ju.SongOperations();
                        break;
                    }
                    case ("RE"):
                        long tot = clip.getMicrosecondLength();
                        long micro = clip.getMicrosecondPosition();
                        //System.out.println(clip.getMicrosecondLength() / 1000000+" "+"seconds");
                        System.out.println("played for(seconds):" + micro / 1000000);
                        System.out.println("remaining time for this song :" + (tot - micro) / 1000000+" "+"seconds");
                        break;
                    default:
                        System.out.println("Not a valid response");


                }

            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            // playsong(String songname,List<Song> songlist);
        }
        return id;
    }
    }
        /*try {
            String url = songname;
            File file = new File(url);
            AudioInputStream audioinput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioinput);
            String choice = "";
            while (!choice.equalsIgnoreCase("q")) {
                System.out.println("P-play, T=Pause, S=Stop, L=loop,F=Forward,B=Reverse,  R=Reset, N=Next Song,RE=Remaining time,Q=quit");
                choice = s.next();
                choice = choice.toUpperCase();
                switch (choice) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip Played Drom: " + milliSecondsToTimer(milliseconds));
                        // System.out.println("Songs in queue");
                        //SongsLeft(songname, songlist);
                        break;


                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip stopped at:" + milliSecondsToTimer(milliseconds));

                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        break;
                    }
                    case ("F"): {
                        System.out.println("Enter The No of Seconds To Forward");
                        long sec=s.nextLong();
                        long timeposition=0L;
                        long total=0L;
                        //
                        // long duration = clip.getFrameLength();
                        // long current_time=
                        clip.setMicrosecondPosition(timeposition-sec*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();
                        clip.start();
                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;


                    }
                    case ("B"): {
                        System.out.println("Enter the no of seconds for Reversing the song");
                        long timeposition=0L;
                        long total=0L;
                        long s2=s.nextLong();
                        clip.setMicrosecondPosition(timeposition-s2*1000000);
                        total=clip.getMicrosecondLength();
                        clip.getMicrosecondPosition();
                        clip.start();
                        System.out.println("Total Time of the Song ="+clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in seconds="+clip.getMicrosecondPosition()/1000000);
                        System.out.println("Remaning time for the song="+(total-clip.getMicrosecondPosition())/1000000);

                        break;
                    }
                    case ("R"): {
                        clip.setMicrosecondPosition(0);
                        break;
                    }
                    case ("N"): {
                        SongsLeft(songname, songlist);
                        System.out.println("Choose the songid to be played next: ");
                        //s.next();
                        int songid = s.nextInt();
                        songinterface dao = new songimplemention();
                        String pathofsong = "";
                        for (Song song : songlist) {
                            if (song.getSong_id() == songid) {
                                pathofsong = song.getUrl();
                            }
                        }
                        clip.stop();
                        playsongbyalbumname(pathofsong, songlist);
                        break;

                    }
                    case ("Q"): {
                        clip.close();
                        ju.SongOperations();
                        break;
                    }
                    case ("RE"):
                        long tot = clip.getMicrosecondLength();
                        long micro = clip.getMicrosecondPosition();
                        // System.out.println(clip.getMicrosecondLength() / 1000000);
                        System.out.println("played for(seconds):" + micro / 1000000+"seconds");
                        System.out.println("remaining time for this song :" + (tot - micro) / 1000000+" "+"seconds");
                        break;
                    default:
                        System.out.println("Not a valid response");


                }

            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }*/





