package playlist;
import Player.Play;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class PlayList implements playlistInterface
{
    @Override
    public void playlist(Connection con,int user_id)
    {
        Scanner sc=new Scanner(System.in);
        try {

            System.out.println();
            System.out.println("Playlists for\n1 : Songs\n2 : Podcast");
            System.out.println("please enter  : ");
            int a = sc.nextInt();
            if(a==1)
            {
                System.out.println("want to make new playlist y for YES or n for DISPLAY ");
                String choice = sc.next();

                if (choice.equalsIgnoreCase("y"))
                {

                    //displaying Songs  ******************************************

                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM songs order by song_name;";
                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next())
                    {
                        System.out.println("Song's Id :  " + rs.getInt(1) + "    " + "Song Name  : " + rs.getString(2) + "    " + "Artist Name  : " + rs.getString(3));
                    }

                    System.out.println("********************************************************************************************************************");

                    //making playlist ************************************

                    Random rm = new Random();
                    int playlist_id = rm.nextInt(500);

                    System.out.println("GIVE A PLAYLIST NAME :  ");
                    String play_name = sc.next();
                    System.out.println("Which song you want to add to your playlist enter it's id : ");
                    int song_id = sc.nextInt();

                    String q2 = "insert into  playlist values(?,?,?,?);";
                    PreparedStatement ps2 = con.prepareStatement(q2);
                    ps2.setInt(1, playlist_id);
                    ps2.setString(2,play_name);
                    ps2.setInt(3, user_id);
                    ps2.setInt(4, song_id);

                    int res = ps2.executeUpdate();
                    System.out.println("Song is added successfully  into your playlist  :" + play_name);
                    boolean zoom=true;

                    while(zoom)
                    {
                        System.out.println("Want to add more Songs into the playlist  :  "+play_name);
                        System.out.println("y for YES n for NO");
                        String addmore=sc.next();
                        if(addmore.equalsIgnoreCase("y"))
                        {
                            System.out.println("Enter the song's id which you want to add into playlist :  " + play_name);
                            int vid = sc.nextInt();
                            String query6 = "insert into playlist values(?,?,?,?);";
                            PreparedStatement ps3 = con.prepareStatement(query6);
                            ps3.setInt(1, playlist_id);
                            ps3.setString(2,play_name);
                            ps3.setInt(3, user_id);
                            ps3.setInt(4,vid);
                            int res32 = ps3.executeUpdate();

                            System.out.println(" Another Song is added successfully  into your playlist  :" + play_name);
                        }
                        else
                        {
                            zoom=false;
                        }

                    }
                }
                else if (choice.equalsIgnoreCase("n"))
                {
                    System.out.println("checking your playlist and play the song from the playlist");

                    System.out.println();

                    //displaying songs from playlist

                    Statement stmt4 = con.createStatement();
                    String query4 = " SELECT * FROM playlist join songs on playlist.song_id=songs.song_id  where playlist.user_id= " + user_id + " \n ";

                    ResultSet rs4 = stmt4.executeQuery(query4);

                    System.out.println("************************************************************************************************");
                    while (rs4.next())
                    {
                        System.out.println("PlayList's Name :  " + rs4.getString(2) + "    Song's id  : " + rs4.getInt(4) + "    " +
                                " song's name  : " + rs4.getString(6) + "     Song Arist :  " + rs4.getString(7));
                    }
                    System.out.println("*************************************************************************************************");
                    System.out.println();
                    System.out.println("Which Song you want to play from playlist Enter it's id :  ");
                    int song_id = sc.nextInt();
                    String q2 = "Select * from songs where song_id=?;";
                    PreparedStatement ps2 = con.prepareStatement(q2);
                    ps2.setInt(1, song_id);
                    ResultSet res1 = ps2.executeQuery();
                    //System.out.println(res);
                    String filepath="";
                    while(res1.next())
                    {
                        filepath = res1.getString(7);
                    }
                    Play p=new Play(filepath);
                }
            }
            else if(a==2)
            {
                playlist_podcast(con,user_id);
            }
            else
            {
                System.out.println("Invalid Inout");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(UnsupportedAudioFileException e){
            e.printStackTrace();
        } catch(LineUnavailableException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

    }
    @Override
    public void playlist_podcast(Connection con,int user_id ) throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException
    {


        Scanner sc = new Scanner(System.in);
        //displaying user
        System.out.println("y : to make new podcast ; n : to display your Podcast");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("y"))
        {

            //displaying podcast  ******************************************
            Random rm = new Random();
            int pod_id = rm.nextInt(500);
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM podcast ";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                System.out.println("Podcast's Id :  " + rs.getInt(1) + "      " + "Podcast Name  : " + rs.getString(2));
            }


            //making playlist Using Podcast.Podcast ********************************************

            System.out.println("GIVE A PLAYLIST NAME :  ");
            String play_name = sc.next();
            System.out.println("Which Podcast you want to add to yourplaylist enter it's id : ");
            int podcast_id = sc.nextInt();


            String q2 = "insert into playlist values(?,?,?,?);";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setInt(1, pod_id);
            ps2.setString(2, play_name);
            ps2.setInt(3, user_id);
            ps2.setInt(4, podcast_id);
            int res = ps2.executeUpdate();
            System.out.println("Podcast is added successfully  into your playlist  :" + play_name);


            //Making Another Playlist using Podcast.Podcast **************************************
            boolean zoom=true;

            while(zoom)
            {
                System.out.println("Want to add more Podcast into the playlist  :  "+play_name);
                System.out.println("y : YES ; n : Opt Out");
                String addmore=sc.next();
                if(addmore.equalsIgnoreCase("y"))
                {

                    System.out.println("Enter the Podcast's id which you want to add into playlist :  " + play_name);
                    int vid = sc.nextInt();

                    String query6 = "insert into playlist values(?,?,?,?);";
                    PreparedStatement ps3 = con.prepareStatement(query6);

                    ps3.setInt(1, pod_id);
                    ps3.setString(2, play_name);
                    ps3.setInt(3, user_id);
                    ps3.setInt(4, vid);

                    int res32 = ps3.executeUpdate();
                    System.out.println(" Another Podcast is added successfully  into your playlist  :" + play_name);
                }
                else
                {
                    zoom=false;
                }
            }
        }
        else if (choice.equalsIgnoreCase("n"))
        {
            System.out.println("here is your play lists");
            System.out.println();

            Statement stmt4 = con.createStatement();
            String query4 = "SELECT * FROM playlist join podcast on playlist.song_id=podcast.podcast_id where playlist.user_id="+user_id+" ;";
            ResultSet rs4 = stmt4.executeQuery(query4);
            System.out.println("*****************************************************************************");
            while (rs4.next())
            {
                System.out.println("PlayList's Id :  " + rs4.getInt(1) + "    " + " Playlist Name  : " + rs4.getString(2) +
                        "    Podcast Id :  " + rs4.getInt(4) + "    " + " Podcast Name  : " + rs4.getString(6) );
            }
            System.out.println("*****************************************************************************");
            System.out.println("Which Podcast you want to play from playlist Enter it's id :  ");

            int podcast_id = sc.nextInt();

            String q2 = "Select * from podcast where podcast_id=?;";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setInt(1, podcast_id);
            ResultSet res3 = ps2.executeQuery();
            //System.out.println(res);
            String filepath="";
            while(res3.next())
            {
                filepath = res3.getString(4);
            }

            Play p=new Play(filepath);

        }
        else
        {
            System.out.println("Invalid Input");
        }
    }

}

