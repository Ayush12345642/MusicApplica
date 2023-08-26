package Jukebox;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import Model.*;
import DaoInterfaces.*;
import DaoImplemention.*;
import Databaseconnection.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import App.*;





public class Main {
    static Scanner s = new Scanner(System.in);
    static jukeboxcatalog juke=new jukeboxcatalog();


    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException,InputMismatchException {
        try {
            juke.homepage();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        //juke.homemenu();








            /*choice=s.nextInt();

            //choice = s.nextInt();
            if (choice < 0 || choice > 3) {
                System.out.println("Please Enter Correct Number(1-3");
            }

            switch (choice) {
                case 1: {a
                    u.homepage();
                }

            }

        } while (choice != 3);*/
    }


}

