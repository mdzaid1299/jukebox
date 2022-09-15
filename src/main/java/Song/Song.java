package Song;

import Player.Play;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;
import java.sql.*;
import java.util.Scanner;

public class Song implements songInterface
{
    @Override
    public void playsong(Connection con , int user_id)
    {
        try
        {
            Scanner sc=new Scanner(System.in);
            //displaying song

            Statement stmt = con.createStatement();
            String query = "SELECT * FROM songs;";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("*******************************************************************************************************");

            while (rs.next())
            {
                System.out.println("Song's Id :  "+rs.getInt(1)+"    "+"Song Name  : "+rs.getString(2) +"    "+
                        "Song  Artist  : "+rs.getString(3)+"   Song Duration  :  " +rs.getString(4));
            }
            System.out.println("********************************************************************************************************");

            //playing song
            System.out.println();
            System.out.println("Which Song you want to play  Enter it's id :  ");
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
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch(NullPointerException ne)
        {
            ne.printStackTrace();
        }
        catch (UnsupportedAudioFileException e)
        {
            e.printStackTrace();
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
