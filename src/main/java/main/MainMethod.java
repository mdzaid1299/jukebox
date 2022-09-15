package main;

import Display.Display;
import Podcast.Podcast;
import Song.Song;
import connection.connection;
import playlist.PlayList;
import sortingsearching.Searching;
import user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMethod {
    public static int displayMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("press 1 for  Listening song");
        System.out.println("press 2 for  Listening podcast");
        System.out.println("press 3 for  See all song ");
        System.out.println("press 4 for  See all podcast");
        System.out.println("press 5 for  Searching");
        System.out.println("press 6 for  Playlist");
        System.out.println("press 7 for  EXIT FROM THE APP ");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter the choice :");
        int c = sc.nextInt();
        return c;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        connection cp = new connection();
        Connection cd = cp.connection();
        User up = new User();
        User uq = new User();

        try {
            System.out.println("Welcome to the JukeBox Music ");

            int userid = User.signin(cd);

            boolean ans = User.condition();

            while (!ans) {
                int l = User.signin(cd);
            }


            while (ans) {
                int c = displayMenu();

                Song son = new Song();
                Podcast p = new Podcast();
                Display d = new Display();
                Searching srt = new Searching();

                switch (c) {
                    case 1:

                        son.playsong(cd, userid);
                        break;

                    case 2:


                        p.playpodcast(cd, userid);
                        break;

                    case 3:

                        //displaying songs
                        d.displaysongs(cd, userid);
                        break;

                    case 4:

                        //displaying podcast
                        d.displaypodcasts(cd, userid);
                        break;


                    case 5:
                        //searching part
                        System.out.println("What you want to Search\n1 : Song\n2 : podcast");
                        int choice1 = sc.nextInt();
                        if (choice1 == 1) {
                            srt.searchingSong(cd, userid);
                        } else if (choice1 == 2) {
                            srt.searchingPodcast(cd, userid);
                        } else {
                            System.out.println("Invalid input");
                        }
                        break;

                    case 6:

                        PlayList playList = new PlayList();
                        playList.playlist(cd, userid);

                        break;

                    case 7:

                        System.out.println("Thank you for choosing our application");
                        ans = false;
                        break;

                    default:
                        System.out.println("Try again and enter a valid input");
                        System.out.println("****************************************");
                        break;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

