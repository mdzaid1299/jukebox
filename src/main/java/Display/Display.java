package Display;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Display {
    public void displaysongs(Connection con, int user_id) throws SQLException {
        try {
            Scanner sc = new Scanner(System.in);
            //displaying song

            Statement stmt = con.createStatement();
            String query = "SELECT * FROM songs;";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("*******************************************************************************************************");

            while (rs.next()) {
                System.out.println("Song's Id :  " + rs.getInt(1) + "    " + "Song Name  : " + rs.getString(2) + "    " +
                        "Song  Artist  : " + rs.getString(3) + "   Song Duration  :  " + rs.getString(4));
            }
            System.out.println("********************************************************************************************************");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void displaypodcasts(Connection con, int user_id)
    {
        Scanner sc = new Scanner(System.in);

        try {

            Statement stmt = con.createStatement();
            String query = "SELECT * FROM podcast";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("*****************************************************");
            while (rs.next()) {
                System.out.println("Podcast's Id :  " + rs.getInt(1) + "    " + "Podcast Name  : " + rs.getString(2));
            }
            System.out.println("******************************************************");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
