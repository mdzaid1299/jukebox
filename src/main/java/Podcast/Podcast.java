package Podcast;
import Player.Play;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Podcast implements podcastInterface
{
    @Override
    public void playpodcast(Connection con,int user_id)
    {
        Scanner sc=new Scanner(System.in);

        try {

            Statement stmt = con.createStatement();
            String query = "SELECT * FROM podcast ";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("*****************************************************");
            while (rs.next())
            {
                System.out.println("Podcast's Id :  "+rs.getInt(1)+"    "+"Podcast Name  : "+rs.getString(2));
            }
            System.out.println("******************************************************");
            System.out.println();
            System.out.println("Which Podcast you want to play  Enter it's id :  ");

            int podcast_id = sc.nextInt();

            String q2 = "Select * from podcast where podcast_id=?";
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

        } catch (SQLException e)
        {System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(" Error with MySQL Connection");
            System.out.println();
            e.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }catch (FileNotFoundException a)
        {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(" File not found");
            System.out.println();
            a.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        catch (UnsupportedAudioFileException e)
        {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(" Error with audioFile");
            System.out.println();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        } catch (LineUnavailableException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
