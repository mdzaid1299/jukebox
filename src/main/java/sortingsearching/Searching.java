package sortingsearching;

import Player.Play;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Searching implements searchingInterface
{
    @Override
    public void searchingSong(Connection con,int user_id )
    {
        //for searching song
        Scanner sc=new Scanner(System.in);
        try
        {
            System.out.println("Enter the song Name ");
            String song_name = sc.nextLine();

            String q2 = "SELECT * FROM songs where song_name=? ";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setString(1, song_name);
            ResultSet res = ps2.executeQuery();
            while(res.next())
            {

                System.out.println("Song's Id :  "+res.getInt(1)+"    "+"Song Name  : "+res.getString(2) +"    "+
                        "Song  Artist  : "+res.getString(3)+"   Song Duration  :  " +res.getString(4));
            }

            //Asking for playing

            System.out.println("want to play this song  y : Yes , n : NO ");
            String ans=sc.next();
            if(ans.equalsIgnoreCase("y"))
            {

                String q3 = "select * from songs where song_name='" + song_name + "';";
                Statement stmt = con.createStatement();
                ResultSet rs1 = stmt.executeQuery(q3);
                String filepath = null;
                while (rs1.next())
                {
                    filepath = rs1.getString(7);
                }

                Play pl = new Play(filepath);
            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e)
        {
            e.printStackTrace();
        } catch (LineUnavailableException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void searchingPodcast(Connection con ,int user_id)
    {
        //searching for podcast
        Scanner sc=new Scanner(System.in);
        try
        {
            System.out.println("Enter the Podcast Name ");
            String podcast_name = sc.nextLine();

            String q4 = "SELECT * FROM podcast where podcast_name=? ;";
            PreparedStatement ps2 = con.prepareStatement(q4);
            ps2.setString(1, podcast_name);
            ResultSet res = ps2.executeQuery();
            while(res.next())
            {

                System.out.println("Podcast's Id :  "+res.getInt(1)+"     "+"podcast Name  : "+res.getString(2));
            }
               //Asking for play
            System.out.println("want to play this podcast  y for YES n for NO ");
            String ans=sc.next();
            if(ans.equalsIgnoreCase("y"))
            {
                String q3 = "select * from podcast where podcast_name='" + podcast_name + "'";
                Statement stmt = con.createStatement();
                ResultSet rs1 = stmt.executeQuery(q3);
                String filepath = null;
                while (rs1.next())
                {
                    filepath = rs1.getString(4);
                }

                Play pl = new Play(filepath);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

